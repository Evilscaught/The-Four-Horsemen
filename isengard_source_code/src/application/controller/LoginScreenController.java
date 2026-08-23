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
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScreenController implements Initializable 
{
	      private double             xOffset = 0.0;
	      private double             yOffset = 0.0;
	
   static private MainMenuController mainMenuController;
   static private LoginSetupController loginSetupController;
	      private UserAuthenticator     userController;
          private IOTransactions	 ioTransactions;
		  private IOAccounts 		 ioAccounts;
		  private IOCodes			 ioCodes;
		  private IOFees 			 ioFees;
	      private Account 			 requestedAccount;
	      private Stage				 stage;
	      
    @FXML private AnchorPane         masterPane;
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
    
    public LoginScreenController(Stage stage)
    {
    	this.stage = stage;
    }
    
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
    	
    	userController = new UserAuthenticator(ioAccounts);
    	
    	//Checks to see if there are any existing accounts upon start-up, if not, prompts user to create an account.
    	try 
    	{
			noUsers();
		} 
    	catch (IOException ioException) 
    	{
			ioException.printStackTrace();
		}
    	
        //This sets the state of the button to 'hover' so that it changes color (#button:hover in style.css)
    	masterPane.setOnKeyPressed(event -> 
        {
            if (event.getCode() == KeyCode.ENTER) 
            {
                loginButton.pseudoClassStateChanged(PseudoClass.getPseudoClass("hover"), true);
                try 
                {
                    handleLogin(event);
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
        });	
    	masterPane.setOnKeyReleased(event -> 
        {
            if (event.getCode() == KeyCode.ENTER) 
            {
                loginButton.pseudoClassStateChanged(PseudoClass.getPseudoClass("hover"), false);
            }
        });
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
    
    //Checks user-name and password
	private boolean checkCredentials()
    {
		//If viewable password is currently being used, get password text from that TextField and check credentials:
		if (viewablePassword.isVisible())
		{
			return (userController.verifyUser(username.getText(), viewablePassword.getText()));
		}
		//Otherwise... get password text from regular PasswordField, password.
		else
		{
			return (userController.verifyUser(username.getText(), password.getText()));
		}  
    }
	
	//Returns true if there are no accounts.
	private void noUsers() throws IOException
	{	
		if (ioAccounts.getAccounts().isEmpty())
		{
			Platform.runLater(() -> 
			{
				stage.hide();
			});
			
		    mainMenuController = new MainMenuController();
			loginSetupController = new LoginSetupController();
			
			Stage stage = new Stage();
			stage.setScene(loginSetupController.loadScene(stage, ioAccounts, ioTransactions, ioCodes, ioFees));
			stage.show();
			
			//Get the x and y coordinates of the login-screen if clicked on.
			stage.getScene().setOnMousePressed(new EventHandler<MouseEvent>()
	        {
	            @Override
	            public void handle(MouseEvent event) 
	            {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
	            }
	        });
			
	        //Update the x and y coordinates if dragged.
	        stage.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() 
	        {
	            @Override
	            public void handle(MouseEvent event) 
	            {
	                stage.setX(event.getScreenX() - xOffset);
	                stage.setY(event.getScreenY() - yOffset);
	            }
	        });
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

    public UserAuthenticator getUserController() 
    {
        return userController;
    }
}