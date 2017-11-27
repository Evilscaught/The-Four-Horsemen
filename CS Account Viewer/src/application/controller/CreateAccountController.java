/******************************************************************************
 *  Compilation:  javac CreateAccountController.java
 *  Execution:    java  CreateAccountController
 *  Dependencies:
 *
 *  @author(s)      Jake Wolfe, Dan Bailey, Scott McKay
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   N/A
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
 *  % java CreateAccountController
 *
 ******************************************************************************/

package application.controller;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CreateAccountController 
{
    	  private Pane 			currentPane;
    
    @FXML private TextField 	firstNameField, lastNameField, emailField, userNameField;
    @FXML private PasswordField passField, passVerifyField;
    @FXML private TextArea 		descriptionField;
    @FXML private Button		backButton, createButton;
    
    @FXML
    void back(MouseEvent event) 
    {
        LoginScreenController.getMainController().setAdminPane();
    }
    
    
    @FXML
    public void backButtonIsClicked()
    {
    	//Set button color to navy blue when clicked on
    	backButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    public void backButtonIsReleased()
    {
    	//Set button back to original color (Red) when click is released
    	backButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    public void createButtonIsClicked()
    {
    	//Set button color to navy blue when clicked on
    	createButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    public void createButtonIsReleased()
    {
    	//Set button back to original color (Red) when click is released
    	createButton.setStyle("-fx-background-color: #e53030;");
    }
    

    @FXML
    void createAccount(MouseEvent event) {
        //Make sure no text fields are empty
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || userNameField.getText().isEmpty() || emailField.getText().isEmpty() || passField.getText().isEmpty() || passVerifyField.getText().isEmpty())
        {
            //            confirmation.setVisible(false);
            //Set text field background to red if empty:
            if (firstNameField.getText().isEmpty())
            {
                firstNameField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                firstNameField.setStyle("-fx-background-color: white;");
                firstNameField.setStyle("-fx-border-color: #000000");
            }
            if (lastNameField.getText().isEmpty())
            {
                lastNameField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                lastNameField.setStyle("-fx-background-color: white;");
                lastNameField.setStyle("-fx-border-color: #000000");
            }
            if (userNameField.getText().isEmpty())
            {
                userNameField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                userNameField.setStyle("-fx-background-color: white;");
                userNameField.setStyle("-fx-border-color: #000000");
            }
            if (emailField.getText().isEmpty())
            {
                emailField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                emailField.setStyle("-fx-background-color: white;");
                emailField.setStyle("-fx-border-color: #000000");
            }
            if (passField.getText().isEmpty())
            {
                passField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                passField.setStyle("-fx-background-color: white;");
                passField.setStyle("-fx-border-color: #000000");
            }
            if (passVerifyField.getText().isEmpty() || !passVerifyField.getText().equals(passField.getText()))
            {
                passVerifyField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                passVerifyField.setStyle("-fx-background-color: white;");
                passVerifyField.setStyle("-fx-border-color: #000000");
            }
        }
        //Create account if no text fields are empty
        else
        {
            //Create Account
            LoginScreenController.getMainController().getAccountDB().createAccount(firstNameField.getText(), lastNameField.getText(), userNameField.getText(), emailField.getText(), passField.getText());

            try 
            {
            	LoginScreenController.getMainController().getAccountDB().saveAccounts();
            } 
            catch (IOException event1) 
            {
                event1.printStackTrace();
            }
            //Reset all text fields
            firstNameField.setStyle("-fx-background-color: white;");
            firstNameField.setStyle("-fx-border-color: #000000");
            
            lastNameField.setStyle("-fx-background-color: white;");
            lastNameField.setStyle("-fx-border-color: #000000");
            
            userNameField.setStyle("-fx-background-color: white;");
            userNameField.setStyle("-fx-border-color: #000000");
            
            emailField.setStyle("-fx-background-color: white;");
            emailField.setStyle("-fx-border-color: #000000");
            
            passField.setStyle("-fx-background-color: white;");
            passField.setStyle("-fx-border-color: #000000");
            
            passVerifyField.setStyle("-fx-background-color: white;");
            passVerifyField.setStyle("-fx-border-color: #000000");
            
            firstNameField.setText("");
            lastNameField.setText("");
            userNameField.setText("");
            emailField.setText("");
            passField.setText("");
            passVerifyField.setText("");

            LoginScreenController.getMainController().refreshUserList();
            // TODO: Dialog Confirmation
        }

    }

    public CreateAccountController() 
    {
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/CreateAccount.fxml"));
        loader.setController(this);

        try 
        {
            currentPane = loader.load();
        } 
        catch (IOException event) 
        {
            event.printStackTrace();
        }
    }

    
    public Pane getPane() 
    {
        return currentPane;
    }

    public void setPane(Pane currentPane) 
    {
        this.currentPane = currentPane;
    }

}
