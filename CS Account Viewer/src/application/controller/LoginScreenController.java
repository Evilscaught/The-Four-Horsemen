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
import application.IOTransactions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginScreenController implements Initializable 
{
   static private MainMenuController mainMenuController;
	
	      private IOTransactions	 ioTransactions;
		  private IOAccounts 		 ioAccounts;
    
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
    private void setPasswordVisible()
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
    	ioAccounts =  new IOAccounts("src/Accounts.txt");
    	
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
		} 
    	catch (FileNotFoundException event) 
    	{
			event.printStackTrace();
		}
    }   
    
    @FXML
    private void handleLogin(MouseEvent event) throws IOException
    {
    	//If entered credentials (user-name & password) are valid:
    	if (checkCredentials())
    	{
    		mainMenuController = new MainMenuController();
    		Stage stage;
	    
    		stage = new Stage();
    		stage.setScene(mainMenuController.loadScene(stage, ioAccounts, ioTransactions));
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
    
	private boolean checkCredentials()
    {
		//TODO: Implement code to check if user-name and password is correct.
    	return true;
    }
    
    public static MainMenuController getMainController()
    {
        return mainMenuController;
    }
}