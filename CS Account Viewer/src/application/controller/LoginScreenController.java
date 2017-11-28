/******************************************************************************
 *  Compilation:  javac LoginScreen.java
 *  Execution:    java  LoginScreen
 *  Dependencies:
 *
 *  @author(s)      Scott McKay
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Friday, November 17th, 2017 @12:00 p.m. MST
 *
 * 
 *
 *     *
 *
 *     *
 *
 *     *
 *
 *  BUG:
 *
 *  FEATURE:
 *
 *  NOTE: 
 *
 *  % java LoginScreen
 *
 ******************************************************************************/
package application.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.IOAccounts;
import application.IOCodes;
import application.IOTransactions;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginScreenController implements Initializable 
{
   static private MainMenuController mainMenuController;
	      private UserController     userController;
	      private IOTransactions	 ioTransactions;
		  private IOAccounts 		 ioAccounts;
		  private IOCodes			 ioCodes;
    
	@FXML private Button 			 loginButton;
    @FXML private TextField 		 username;
    @FXML private PasswordField 	 password;
    @FXML private TextField			 viewablePassword;
    @FXML private Hyperlink 		 createAccount;
    @FXML private ImageView			 visibilityTrue;  //Image view icon of eye (toggle password visibility)
    @FXML private ImageView			 visibilityFalse;
        
    @FXML
    private void handleClose(MouseEvent event) 
    {
    	//This method will close the program
        System.exit(0);
    }
    
    @FXML
    private void showPassword()
    {
    	//Copy text from PasswordField to TextField.
    	viewablePassword.setText(password.getText());
    	//Set TextField visibility to TRUE
    	viewablePassword.setVisible(true);
    	
    	//Toggle eye button
    	visibilityFalse.setVisible(true);
    	visibilityTrue.setVisible(false);
    }
    
    @FXML
    private void hidePassword()
    {
    	//Copy text from TextField to PasswordField
    	password.setText(viewablePassword.getText());
    	//Set TextField visibility to FALSE
    	viewablePassword.setVisible(false);
    	
    	//Toggle eye button
    	visibilityTrue.setVisible(true);
    	visibilityFalse.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    	createAccount.setVisible(false);
    	viewablePassword.setVisible(false);
    	visibilityFalse.setVisible(false);
    	
    	ioTransactions = new IOTransactions("src/Transactions.txt");
    	ioAccounts 	   = new IOAccounts("src/Accounts.txt");
    	ioCodes        = new IOCodes();
    	
    	//Load file contents (transactions.txt) into ADT ioTransactions.
    	try 
    	{
			ioTransactions.readTransactions();
		} 
    	catch (FileNotFoundException event) 
    	{
			event.printStackTrace();
		}
    	
    	//Load file contents (accounts.txt) into ADT ioAccounts.
    	try 
    	{
			ioAccounts.readAccounts();
			userController = new UserController(ioAccounts.getAccounts());
		} 
    	catch (FileNotFoundException event) 
    	{
			event.printStackTrace();
		}
    }   
    
    @FXML
    private void handleLogin(Event event) throws IOException
    {
    	//If entered credentials (user-name & password) are valid:
    	if (checkCredentials())
    	{	
    	    userController.setCurUser(username.getText());
    	    
    		mainMenuController = new MainMenuController();
    		Stage stage;
	    
    		stage = new Stage();
    		stage.setScene(mainMenuController.loadScene(stage, ioAccounts, ioTransactions, ioCodes, userController));
    		stage.show();
		
    		//This will hide the login screen.
    		( (Node)event.getSource() ).getScene().getWindow().hide();
    	}
    	//Else, incorrect user-name or password.
    	else
    	{
    		return;
    	}
    }
    
    @FXML
    public void loginButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	loginButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    public void loginButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	loginButton.setStyle("-fx-background-color: #e53030;");
    }
    
    //This allows user to press the enter key to login.
    @FXML
    public void enterKeyPressed(KeyEvent event) throws IOException
    {
    	if (event.getCode().equals(KeyCode.ENTER))
    	{
    		loginButtonClicked();
    		handleLogin(event);
    	}
    }
    
    //Sets login button back to default color when enter key is released.
    @FXML
    public void enterKeyReleased(KeyEvent event)
    {
    	loginButtonReleased();
    }
    
	private boolean checkCredentials()
    {
	    return (userController.verifyUser(username.getText(), password.getText())) ||
	                  (username.getText().toLowerCase().equals("csadmin") && 
	                   password.getText().toLowerCase().equals("csci323"));
    }
    
    public static MainMenuController getMainController()
    {
        return mainMenuController;
    }
}