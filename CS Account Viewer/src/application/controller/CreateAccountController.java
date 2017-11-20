package application.controller;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    @FXML
    void back(MouseEvent event) 
    {
        LoginScreenController.getMainController().setAdminPane();
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
            }
            if (lastNameField.getText().isEmpty())
            {
                lastNameField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                lastNameField.setStyle("-fx-background-color: white;");
            }
            if (userNameField.getText().isEmpty())
            {
                userNameField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                userNameField.setStyle("-fx-background-color: white;");
            }
            if (emailField.getText().isEmpty())
            {
                emailField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                emailField.setStyle("-fx-background-color: white;");
            }
            if (passField.getText().isEmpty())
            {
                passField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                passField.setStyle("-fx-background-color: white;");
            }
            if (passVerifyField.getText().isEmpty() || !passVerifyField.getText().equals(passField.getText()))
            {
                passVerifyField.setStyle("-fx-background-color: #f26d6d;");
            }
            else
            {
                passVerifyField.setStyle("-fx-background-color: white;");
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
            lastNameField.setStyle("-fx-background-color: white;");
            userNameField.setStyle("-fx-background-color: white;");
            emailField.setStyle("-fx-background-color: white;");
            passField.setStyle("-fx-background-color: white;");
            passVerifyField.setStyle("-fx-background-color: white;");
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
