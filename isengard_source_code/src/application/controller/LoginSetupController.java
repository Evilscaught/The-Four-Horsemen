/******************************************************************************
 *  Compilation:  javac LoginSetupController.java
 *  Execution:    java  LoginSetupController
 *  Dependencies:
 *
 *  @author(s)      Scott McKay
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Tuesday, June 19th, 2018
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
 *  % java LoginSetupController
 *
 ******************************************************************************/

package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Account;
import application.IOAccounts;
import application.IOCodes;
import application.IOFees;
import application.IOTransactions;
import application.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginSetupController 
{
    @FXML private AnchorPane securityQuestionsPane;
    
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField username;
    @FXML private TextField vPassword;
    @FXML private TextField vPasswordConfirm;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordConfirm;
    
    @FXML private ImageView visibilityTrue;
    @FXML private ImageView visibilityFalse;
    
    @FXML private Text firstNameEmpty;
    @FXML private Text lastNameEmpty;
    @FXML private Text usernameEmpty;
    
    @FXML private Text passwordRequired;
    @FXML private Text passwordShort;
    @FXML private Text passwordNoMatch;
    @FXML private Text passwordNoComma;
    @FXML private Text firstNameNoComma;
    @FXML private Text lastNameNoComma;
    @FXML private Text emailNoComma;
    @FXML private Text usernameNoComma;
    @FXML private Text noComma;
    @FXML private Text noEmpty;
    
    @FXML private Button next;
    @FXML private Button back;
    @FXML private Button login;
    
    @FXML private TextField secField1;
    @FXML private TextField secField2;
    @FXML private TextField secField3;
   
    private IOAccounts ioAccounts;
    private IOTransactions ioTransactions;
    private IOCodes ioCodes;
    private IOFees ioFees;
    
	private Stage primaryStage;
	private String finalPassword;
	private String finalEmail;

	public void initialize(URL url, ResourceBundle rb) 
    {		
      
    }   
	
    public Scene loadScene(Stage stage, IOAccounts ioAccounts, IOTransactions ioTransactions, IOCodes ioCodes, IOFees ioFees) throws IOException
    {
        this.ioAccounts = ioAccounts;
        this.ioCodes = ioCodes;
        this.ioFees = ioFees;
        this.ioTransactions = ioTransactions;
          
    	BorderPane rootLayout = new BorderPane();
        
    	primaryStage = stage;
    	primaryStage.setTitle("Isengard");
    	primaryStage.getIcons().add(new Image("application/view/images/program-icon.png"));
    	stage.initStyle(StageStyle.UNDECORATED);
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("view/LoginCreateAccount.fxml"));
    	loader.setController(this);
    	
    	try
    	{
    		Scene scene = new Scene(loader.load());
    		return scene;
    	}
    	catch (IOException ioException)
    	{
    		ioException.printStackTrace();
    	}
    	return new Scene(rootLayout);
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
        vPassword.setText(password.getText());
        //Set TextField visibility to TRUE
        vPassword.setVisible(true);
        
        vPasswordConfirm.setText(passwordConfirm.getText());
        vPasswordConfirm.setVisible(true);
        
        
        //Toggle eye button
        visibilityFalse.setVisible(true);
        visibilityTrue.setVisible(false);
    }
    
    @FXML
    private void hidePassword()
    {
        //Copy text from TextField to PasswordField
        password.setText(vPassword.getText());
        //Set TextField visibility to FALSE
        vPassword.setVisible(false);
        
        passwordConfirm.setText(vPasswordConfirm.getText());
        vPasswordConfirm.setVisible(false);
        
        //Toggle eye button
        visibilityTrue.setVisible(true);
        visibilityFalse.setVisible(false);
    }
    
    //---------------------------------------------------------------------------------//
    //                                GUI Listener Handlers                            //
    //---------------------------------------------------------------------------------//
    
    //This function updates the login button if mouse is hovered over it
    @FXML
    private void nextButtonClicked()
    {
        //Set button color to navy blue when clicked on
        next.setStyle("-fx-background-color: #273e51;");
    }
    
    //This function updates the login button if mouse is hovered over it
    @FXML
    private void nextButtonReleased()
    {
        //Set button back to original color (Red) when click is released
        next.setStyle("-fx-background-color: #e53030;");
    }
    
    
    @FXML
    private void handleNext()
    {
        if (checkParam() == true)
        {
            securityQuestionsPane.setVisible(true);
        }
        else
        {
            return;
        }
    }
    
    //This function updates the login button if mouse is hovered over it
    @FXML
    private void backButtonClicked()
    {
        //Set button color to navy blue when clicked on
        back.setStyle("-fx-background-color: #273e51;");
    }
    
    //This function updates the login button if mouse is hovered over it
    @FXML
    private void backButtonReleased()
    {
        //Set button back to original color (Red) when click is released
        back.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleBack()
    {
        securityQuestionsPane.setVisible(false);
    }
    
    @FXML
    private void loginButtonClicked()
    {
        //Set button color to navy blue when clicked on
        login.setStyle("-fx-background-color: #273e51;");
    }
    
    //This function updates the login button if mouse is hovered over it
    @FXML
    private void loginButtonReleased()
    {
        //Set button back to original color (Red) when click is released
        login.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleLogin(Event event) throws IOException
    {
        if (checkAnswers() == true)
        {
            Account newAccount = new Account(firstName.getText(), lastName.getText(), username.getText(), finalPassword);
            newAccount.setEmail(finalEmail);
            newAccount.setSecurityQuestion1(secField1.getText());
            newAccount.setSecurityQuestion2(secField2.getText());
            newAccount.setSecurityQuestion3(secField3.getText());
            newAccount.setAdmin(true);
            newAccount.setDescription("n/a");
            
            ioAccounts.createAccount(newAccount);
            ioAccounts.saveAccounts();
            
            UserAuthenticator userController = new UserAuthenticator(ioAccounts);
            userController.setCurUser(username.getText());
            
            Stage stage;
        
            stage = new Stage();
            stage.setScene(LoginScreenController.getMainController().loadScene(stage, ioAccounts, ioTransactions, ioCodes, ioFees, userController));
            stage.show();
        
            //This will hide the login screen.
            ( (Node)event.getSource() ).getScene().getWindow().hide();
        }
        else
        {
            return;
        }
    }
    
    //---------------------------------------------------------------------------------//
    //                             Parameter Checking Functions                        //
    //---------------------------------------------------------------------------------//
    
    private void resetMessages()
    {
        firstNameEmpty.setVisible(false);
        lastNameEmpty.setVisible(false);
        usernameEmpty.setVisible(false);
        passwordRequired.setVisible(false);
        passwordNoMatch.setVisible(false);
        passwordShort.setVisible(false);
        passwordNoComma.setVisible(false);
        firstNameNoComma.setVisible(false);
        lastNameNoComma.setVisible(false);
        emailNoComma.setVisible(false);
        usernameNoComma.setVisible(false);
    }
    
    //Checks answers for security question fields
    private boolean checkAnswers() 
    {
        boolean pass = true;
        
        noEmpty.setVisible(false);
        noComma.setVisible(false);
        
        if (secField1.getText().isEmpty())
        {
            pass = false;
            noEmpty.setVisible(true);
        }
        if (secField1.getText().contains(","))
        {
            pass = false;
            noComma.setVisible(true);
        }
        
        if (secField2.getText().isEmpty())
        {
            pass = false;
            noEmpty.setVisible(true);
            
        }
        if (secField2.getText().contains(","))
        {
            pass = false;
            noComma.setVisible(true);
        }
        
        if (secField3.getText().isEmpty())
        {
            pass = false;
            noEmpty.setVisible(true);
        }
        if (secField3.getText().contains(","))
        {
            pass = false;
            noComma.setVisible(true);
        }
        
        return pass;
    }
    
    //Checks account details
    private boolean checkParam()
    {
        boolean pass = true;
        String password;
        String passwordConfirm;

        resetMessages();
        
        //Retrieve the user-entered password
        if (vPassword.isVisible() == true)
        {
            password = vPassword.getText();
            passwordConfirm = vPasswordConfirm.getText();
        }
        else
        {
            password = this.password.getText();
            passwordConfirm = this.passwordConfirm.getText();
        }
        
        
        //Makes sure none of the other fields (First Name, Last Name, User-name) are empty or contain commas:
        if (firstName.getText().isEmpty())
        {
            pass = false;
            firstNameEmpty.setVisible(true);
        }
        if (firstName.getText().contains(","))
        {
            pass = false;
            firstNameNoComma.setVisible(true);
        }
        
        
        if (lastName.getText().isEmpty())
        {
            pass = false;
            lastNameEmpty.setVisible(true);
        }
        if (lastName.getText().contains(","))
        {
            pass = false;
            lastNameNoComma.setVisible(true);
        }
        
        if (email.getText().isEmpty())
        {
           finalEmail = "n/a";
        }
        if (email.getText().contains(","))
        {
            pass = false;
            emailNoComma.setVisible(true);
        }
        else
        {
            finalEmail = email.getText();
        }
        
        if (username.getText().isEmpty())
        {
            pass = false;
            usernameEmpty.setVisible(true);
        }
        if (username.getText().contains(","))
        {
            pass = false;
            usernameNoComma.setVisible(true);
        }
        
        
        //Checks password
        if (password.compareTo(passwordConfirm) == 0)
        {   
            if (password.length() == 0)
            {
                passwordRequired.setVisible(true);
                return false;
            }
            
            if (password.length() < 5)
            {
                passwordShort.setVisible(true);
                return false;
            }
            
            if (password.contains(","))
            {
                passwordNoComma.setVisible(true);
                return false;
            }
        }
        else
        {
            passwordNoMatch.setVisible(true);
            return false;
        }
        
        if (pass == true) 
        {
            finalPassword = password;
        }
        
        return pass;
    }
}