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

import application.Account;
import application.IOAccounts;
import application.IOCodes;
import application.IOFees;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScreenController implements Initializable 
{
   static private MainMenuController mainMenuController;
	      private UserController     userController;
          private IOTransactions	 ioTransactions;
		  private IOAccounts 		 ioAccounts;
		  private IOCodes			 ioCodes;
		  private IOFees 			 ioFees;
	      private Account 			 requestedAccount;
    
    @FXML private AnchorPane		 loginPane;  //All clustered attributes below are part of this pane:
	@FXML private Button 			 loginButton;
    @FXML private TextField 		 username;
    @FXML private PasswordField 	 password;
    @FXML private TextField			 viewablePassword;
    @FXML private ImageView			 visibilityTrue;  //Image view icon of eye (toggle password visibility)
    @FXML private ImageView			 visibilityFalse;
    @FXML private Hyperlink			 forgotPassword;
    @FXML private Text				 msgLoginFail;
    
    //Password Recovery P1:		
    @FXML private AnchorPane		 recoveryPane;  //All clustered attributes below are part of this pane:
    @FXML private TextField			 usernameRec;
    @FXML private ImageView		     userIcon;
    @FXML private TextField			 secQ1Field;
    @FXML private TextField			 secQ2Field;
    @FXML private TextField			 secQ3Field;
    @FXML private Text				 secQ1;
    @FXML private Text				 secQ2;
    @FXML private Text				 secQ3;
    @FXML private Button			 nextButton;
    @FXML private Button			 cancelButton;
    @FXML private Button			 changePasswordButton;
    
    //Password Recovery P2:
    @FXML private AnchorPane		 passwordPane;  //All clustered attributes below are part of this pane:
    @FXML private TextField			 newPassword1;
    @FXML private TextField			 newPassword2;
    
    //Messages Pane
    @FXML private AnchorPane		 messagesPane;  //All clustered attributes below are part of this pane:
    @FXML private Text				 msgUsernameNotFound;
    @FXML private Text			     msgNoUsernameEntered;
    @FXML private Text				 msgIncorrectAnswer1;
    @FXML private Text				 msgIncorrectAnswer2;
    @FXML private Text			     msgPasswordNoMatch;
    @FXML private Text				 msgPasswordTooShort;
    @FXML private Text				 msgPasswordEmpty;
        
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
    	viewablePassword.setVisible(false);
    	visibilityFalse.setVisible(false);
    	
    	ioTransactions = new IOTransactions("Transactions.txt");
		ioAccounts = new IOAccounts("Accounts.txt");
    	ioCodes = new IOCodes("Codes.txt");
    	ioFees = new IOFees("Fees.txt");
    	
    	//Load file contents (transactions.txt) into ADT ioTransactions.
    	try 
    	{
			ioTransactions.readTransactions();
			ioAccounts.readAccounts();
			ioCodes.readCodes();
			ioFees.readFees();
		} 
    	catch (FileNotFoundException nfe) 
    	{
    		nfe.printStackTrace();
		}
    	
    	userController = new UserController(ioAccounts);
    	noUsers();
    }   
    
    //This function is called when login button is clicked on (or enter key pressed)
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
    		stage.setScene(mainMenuController.loadScene(stage, ioAccounts, ioTransactions, ioCodes, ioFees, userController));
    		stage.show();
		
    		//This will hide the login screen.
    		( (Node)event.getSource() ).getScene().getWindow().hide();
    	}
    	//Else, incorrect user-name or password.
    	else
    	{
    		msgLoginFail.setVisible(true);
    		return;
    	}
    }
    
    //This alternative function can only be called if password has been reset.
    private void handleLogin(Event event, Account account) throws IOException
    {
    	//If entered credentials (user-name & password) are valid:
    	userController.setCurUser(account.getUsername());
    	    
        mainMenuController = new MainMenuController();
        Stage stage;
	    
        stage = new Stage();
        stage.setScene(mainMenuController.loadScene(stage, ioAccounts, ioTransactions, ioCodes, ioFees, userController));
    	stage.show();
	
    	//This will hide the login screen.
    	( (Node)event.getSource() ).getScene().getWindow().hide();
    }
    
    //This function updates the login button if mouse is hovered over it
    @FXML
    private void loginButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	loginButton.setStyle("-fx-background-color: #273e51;");
    }
    
    //This function updates the login button if mouse is hovered over it
    @FXML
    private void loginButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	loginButton.setStyle("-fx-background-color: #e53030;");
    }
    
    //This allows user to press the enter key to login.
    @FXML
    private void enterKeyPressed(KeyEvent event) throws IOException
    {
    	if (event.getCode().equals(KeyCode.ENTER))
    	{
    		loginButtonClicked();
    		handleLogin(event);
    	}
    }
    
    //Sets login button back to default color when enter key is released.
    @FXML
    private void enterKeyReleased(KeyEvent event)
    {
    	loginButtonReleased();
    }
    
    //Checks user-name and password
	private boolean checkCredentials()
    {
		//If viewable password is currently being used, get password text from that TextField and check credentials:
		if (viewablePassword.isVisible())
		{
			return (userController.verifyUser(username.getText(), viewablePassword.getText())) || (username.getText().toLowerCase().equals("csadmin") && viewablePassword.getText().toLowerCase().equals("csci323"));
		}
		//Otherwise... get password text from regular PasswordField, password.
		else
		{
			return (userController.verifyUser(username.getText(), password.getText())) || (username.getText().toLowerCase().equals("csadmin") && password.getText().toLowerCase().equals("csci323"));
		}  
    }
	
	//Returns true if there are no accounts.
	private void noUsers()
	{
		//Create an admin account if no accounts exist.
		if (ioAccounts.getAccounts().isEmpty())
		{
			ioAccounts.createAccount("Admin", " ","n/a","csadmin", "csci323", "n/a", "n/a", "n/a", "n/a", true, 0.0);
			username.setText("csadmin");
			username.setEditable(false);
			password.setText("csci323");
			password.setEditable(false);
			viewablePassword.setText("csci323");
			viewablePassword.setEditable(false);
			loginButton.setText("Create New Account");
			password.setVisible(false);
			viewablePassword.setVisible(true);
			visibilityTrue.setVisible(true);
			visibilityFalse.setVisible(false);
			forgotPassword.setVisible(false);
			try 
			{
				ioAccounts.saveAccounts();
			} 
			catch (IOException ioException) 
			{
				ioException.printStackTrace();
			}
		}
	}
	
	//------------------------------------------------------------------------------------//
	//                               Password Recovery                                    //
	//------------------------------------------------------------------------------------//
	
    @FXML
    private void forgetPasswordClicked()
    {
    	//Set hyper-link color to navy Blue when clicked on
    	forgotPassword.setTextFill(Paint.valueOf("#273e51"));
    }
    
    @FXML
    private void forgetPasswordReleased()
    {
    	//Set hyper-link color back to original color when click is released
    	forgotPassword.setTextFill(Paint.valueOf("#ffaeae"));
    }
    
    @FXML
    private void handleForgotPassword()
    {
		msgLoginFail.setVisible(false);
    	loginPane.setVisible(false);
    	recoveryPane.setVisible(true);
    	
    	toggleP1andP2(true);
    }
    
    @FXML
    private void nextClicked()
    {
    	//Set button color to navy blue when clicked on
    	nextButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void nextReleased()
    {
    	//Set button back to original color (Red) when click is released
    	nextButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleNext()
    {
    	if (checkQuestions())
    	{
    		toggleP1andP2(false);
    		passwordPane.setVisible(true);
    	}
    	else
    	{
    		return;
    	}
    }
    
    @FXML
    private void cancelButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	cancelButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void cancelButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	cancelButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleCancel()
    {
    	loginPane.setVisible(true);
    	messagesPane.setVisible(false);
		passwordPane.setVisible(false);
    	recoveryPane.setVisible(false);
    }
    
    private boolean checkQuestions()
    {	
    	//Throw an error message if user hasn't typed user-name.
    	if (usernameRec.getText().isEmpty())
    	{
    		hideAllMessages();
    		msgNoUsernameEntered.setVisible(true);
    		messagesPane.setVisible(true);
    		return false;
    	}
    	
    	//Find the user account:
    	for (Account account : ioAccounts.getAccounts())
    	{
    		if (account.getUsername().toLowerCase().equals(usernameRec.getText().toLowerCase()))
    		{
    			requestedAccount = account;
    		}
    	}
    	
    	//Throw an error message if requested user-name hasn't been found and requested account is still null.
    	if (requestedAccount == null)
    	{
    		hideAllMessages();
    		msgUsernameNotFound.setVisible(true);
    		messagesPane.setVisible(true);
    		return false;
    	}
    	
    	//Throw an error message if requested user-name hasn't been found.
    	if (requestedAccount.getUsername().toLowerCase().equals(usernameRec.getText().toLowerCase()) == false)
    	{
    		hideAllMessages();
    		msgUsernameNotFound.setVisible(true);
    		messagesPane.setVisible(true);
    		return false;
    	}
    	
    	//Prepare messages pane should any of the answered security questions be wrong.
    	hideAllMessages();
		msgIncorrectAnswer1.setVisible(true);
		msgIncorrectAnswer2.setVisible(true);

    	//Check if answered security questions are correct
    	if (secQ1Field.getText().equals(requestedAccount.getSecurityQuestion1()) == false)
    	{
    		messagesPane.setVisible(true);
    		return false;
    	}
    	if (secQ2Field.getText().equals(requestedAccount.getSecurityQuestion2()) == false)
    	{
    		messagesPane.setVisible(true);
    		return false;
    	}
    	if (secQ3Field.getText().equals(requestedAccount.getSecurityQuestion3()) == false)
    	{
    		messagesPane.setVisible(true);
    		return false;
    	}
    	
    	messagesPane.setVisible(false);
    	return true;
    }
    
    //True enables part 1, False enables part 2.
    private void toggleP1andP2(boolean value)
    {
    	//Toggle visibility
		usernameRec.setVisible(value);
		secQ1.setVisible(value);
		secQ2.setVisible(value);
		secQ3.setVisible(value);
		secQ1Field.setVisible(value);
		secQ2Field.setVisible(value);
		secQ3Field.setVisible(value);
		userIcon.setVisible(value);
		nextButton.setVisible(value);
		changePasswordButton.setVisible(!value);

		
		//Reset text fields
		usernameRec.setText("");
		secQ1Field.setText("");
		secQ2Field.setText("");
		secQ3Field.setText("");
    }
    
	//------------------------------------------------------------------------------------//
	//                               Password Recovery Part 2                             //
	//------------------------------------------------------------------------------------//
    
    @FXML
    private void changePasswordClicked()
    {
    	//Set button color to navy blue when clicked on
    	changePasswordButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void changePasswordReleased()
    {
    	//Set button back to original color (Red) when click is released
    	changePasswordButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleChangePassword(MouseEvent event) throws IOException
    {
    	if (checkPassword() == true)
    	{
    		requestedAccount.setPassword(newPassword1.getText());
    		ioAccounts.saveAccounts();
    		
    		handleLogin(event, requestedAccount);	
    	}
    	else
    	{
    		return;
    	}
    }
    
    private boolean checkPassword()
    {
    	hideAllMessages();
    	
    	if (newPassword1.getText().isEmpty())
    	{
    		msgPasswordEmpty.setVisible(true);
    		messagesPane.setVisible(true);
    		return false;
    	}
    	if (newPassword1.getText().equals(newPassword2.getText()) == false)
    	{
    		msgPasswordNoMatch.setVisible(true);
    		messagesPane.setVisible(true);
    		return false;
    	}
    	if (newPassword1.getText().length() < 5)
    	{
    		msgPasswordTooShort.setVisible(true);
    		messagesPane.setVisible(true);
    		return false;
    	}
    	
    	messagesPane.setVisible(false);
    	hideAllMessages();
    	return true;
    }
    
    private void hideAllMessages()
    {
    	msgNoUsernameEntered.setVisible(false);
    	msgPasswordEmpty.setVisible(false);
    	msgPasswordTooShort.setVisible(false);
    	msgPasswordNoMatch.setVisible(false);
    	msgUsernameNotFound.setVisible(false);
		msgIncorrectAnswer1.setVisible(false);
		msgIncorrectAnswer2.setVisible(false);
    }
    
    
	//------------------------------------------------------------------------------------//
	//                                 Getters & Setters                                  //
	//------------------------------------------------------------------------------------//
    
    public static MainMenuController getMainController()
    {
        return mainMenuController;
    }

public UserController getUserController() {
        return userController;
    }



}