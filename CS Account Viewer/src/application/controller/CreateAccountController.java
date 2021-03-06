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

import application.Account;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CreateAccountController
{
    	  private AnchorPane currentPane;

    @FXML private TextField 	firstNameField, lastNameField, emailField, userNameField;
    @FXML private PasswordField passField, passVerifyField;
    @FXML private TextArea 		descriptionField;
    @FXML private Button		cancelButton, createButton;
    @FXML private CheckBox      isAdminBox;

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
            System.out.println("Error loading CreateAccount.fxml!");
            event.printStackTrace();
        }
    }

    public CreateAccountController(Account account)
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

        firstNameField.setText(account.getFirstName());
        lastNameField.setText(account.getLastName());
        emailField.setText(account.getEmail());
        userNameField.setText(account.getUsername());
        passField.setText(account.getPassword());
        passVerifyField.setText(account.getPassword());
        descriptionField.setText(account.getDescription().replace("`", ","));
    }

    @FXML
    private void handleCancel(MouseEvent event)
    {
        LoginScreenController.getMainController().setAdminPane();
    }


    @FXML
    private void backButtonIsClicked()
    {
    	//Set button color to navy Blue when clicked on
    	cancelButton.setStyle("-fx-background-color: #273e51;");
    }

    @FXML
    private void backButtonIsReleased()
    {
    	//Set button back to original color (Red) when click is released
    	cancelButton.setStyle("-fx-background-color: #e53030;");
    }

    @FXML
    private void createButtonIsClicked()
    {
    	//Set button color to navy Blue when clicked on
    	createButton.setStyle("-fx-background-color: #273e51;");
    }

    @FXML
    private void createButtonIsReleased()
    {
    	//Set button back to original color (Red) when click is released
    	createButton.setStyle("-fx-background-color: #e53030;");
    }


    @FXML
    private void handleCreateAccount(MouseEvent event)
    {

    	if (!checkParam())
    	{
    		return;
    	}
        //Create account if no text fields are empty
    	else
    	{
            Account account = new Account(firstNameField.getText(), lastNameField.getText(), userNameField.getText(), passField.getText());
            account.setEmail(emailField.getText());
            account.setAdmin(isAdminBox.isSelected());
            account.setBalance(0.0);
            account.setDescription(descriptionField.getText().replace(",", "`"));

            currentPane.getChildren().clear();
            currentPane.getChildren().addAll(new SecurityQuestionsController(account).getPane());
    	}
    }

    private boolean checkParam()
    {
    	boolean pass = true;

        //Make sure no text fields are empty
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || userNameField.getText().isEmpty() || emailField.getText().isEmpty() || passField.getText().isEmpty() || passVerifyField.getText().isEmpty())
        {
        	pass = false;
            //Set text field background to red if empty:
            if (firstNameField.getText().isEmpty())
            {
                firstNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
            }
            else
            {
                firstNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
            }
            if (lastNameField.getText().isEmpty())
            {
                lastNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
            }
            else
            {
                lastNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
            }
            if (userNameField.getText().isEmpty())
            {
                userNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
            }
            else
            {
                userNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
            }
            if (emailField.getText().isEmpty())
            {
                emailField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
            }
            else
            {
                emailField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
            }
            if (passField.getText().isEmpty())
            {
                passField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
            }
            else
            {
                passField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
            }
            if (passVerifyField.getText().isEmpty())
            {
                passVerifyField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
            }
            else
            {
                passVerifyField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
            }
        }
        else
        {
            firstNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000");
            lastNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000");
            userNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000");
            emailField.setStyle("-fx-background-color: white; -fx-border-color: #000000");
            passField.setStyle("-fx-background-color: white; -fx-border-color: #000000");
            passVerifyField.setStyle("-fx-background-color: white; -fx-border-color: #000000");
        }
        
    	if (passField.getText().contains(","))
    	{
        	passVerifyField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		passField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
        
        //If passwords do not match
        if (passField.getText().equals(passVerifyField.getText()) == false)
        {
        	passVerifyField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
        	passField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
        	pass = false;
        }

        //If password is too short
        if (passField.getText().length() < 5)
        {
        	passVerifyField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
        	passField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
        	pass = false;
        }
        
    	if (emailField.getText().contains(","))
    	{
    		emailField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	
    	if (firstNameField.getText().contains(","))
    	{
    		firstNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	
    	if (lastNameField.getText().contains(","))
    	{
    		lastNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}

    	if (userNameField.getText().contains(","))
    	{
    		userNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
        
        
        //If user-name is already taken:
    	for (String username : LoginScreenController.getMainController().getAccountDB().getUsernames())
    	{
    		if (userNameField.getText().toLowerCase().equals(username.toLowerCase()))
			{
    			userNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    			pass = false;
			}
    	}

    	return pass;
    }

    public AnchorPane getPane()
    {
        return currentPane;
    }

    public void setPane(AnchorPane currentPane)
    {
        this.currentPane = currentPane;
    }

}
