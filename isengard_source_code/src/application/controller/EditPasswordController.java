/******************************************************************************
 *  Compilation:  javac EditPasswordController.java
 *  Execution:    java  EditPasswordController
 *  Dependencies: 
 *
 *  @author(s)		Scott McKay
 *  @version   		0.0.0
 *  @group			The Four Horsemen
 *  @copyright   	None
 *  @date_created   Saturday, December 2nd, 2017 5:00 p.m.
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
 *  % java EditPasswordController
 *
 ******************************************************************************/

package application.controller;

import java.io.IOException;

import application.Account;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EditPasswordController 
{
		  private Pane currentPane;
		  private Account account;
		  private ListView<String> userList;
	
	@FXML private Button 		cancelButton;
	@FXML private Button 		changePasswordButton;
	@FXML private PasswordField passwordField1;
	@FXML private PasswordField passwordField2;
	@FXML private Text   		msgTooShort;
	@FXML private Text   		msgNoMatch;
	@FXML private Text   		msgCannotBeEmpty;
	
	
    public EditPasswordController(ListView<String> userList, Account account) 
    {
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/EditPassword.fxml"));
        loader.setController(this);

        try 
        {
            currentPane = loader.load();
        } 
        catch (IOException event) 
        {
            event.printStackTrace();
        }
        
        this.userList = userList;
        this.account  = account;
    }
    
    @FXML
    private void handleCancel()
    {
        currentPane.getChildren().clear();
        currentPane.getChildren().addAll(new AccountOverviewController(userList, account).getPane());
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
    private void handleChangePassword()
    {
    	if (checkParam())
    	{
    		//Update account
    		account.setPassword(passwordField1.getText());
    		try
    		{
				LoginScreenController.getMainController().getAccountDB().saveAccounts();
			} 
    		catch (IOException exception) 
    		{
    			exception.printStackTrace();
			}
    		
            currentPane.getChildren().clear();
            
            //Go back to Account Overview pane.
            currentPane.getChildren().addAll(new AccountOverviewController(userList, account).getPane());
    	}
    	else
    	{
    		return;
    	}
    }
    
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
    
    private boolean checkParam()
    {
    	hideAllMessages();
    	
    	//Return false if password field is empty
    	if (passwordField1.getText().isEmpty())
    	{
    		passwordField1.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		passwordField2.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		msgCannotBeEmpty.setVisible(true);
    		
    		return false;
    	}
    	
    	//Return false if passwords do not match
    	if (passwordField1.getText().equals(passwordField2.getText()) == false)
    	{
    		passwordField1.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		passwordField2.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		msgNoMatch.setVisible(true);
    		return false;
    	}
    	
    	//Return false if password is too short
    	if (passwordField1.getText().length() < 5)
    	{
    		passwordField1.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		passwordField2.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		msgTooShort.setVisible(true);
    		
    		return false;
    	}
    	
    	//If everything is correct, set password fields back to white.
		passwordField1.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
		passwordField2.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	
    	return true;
    }
    
    private void hideAllMessages()
    {
    	msgTooShort.setVisible(false);
    	msgNoMatch.setVisible(false);
        msgCannotBeEmpty.setVisible(false);
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
