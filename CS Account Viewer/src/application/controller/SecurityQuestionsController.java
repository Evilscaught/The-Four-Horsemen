/******************************************************************************
 *  Compilation:  javac SecurityQuestionsController.java
 *  Execution:    java  SecurityQuestionsController
 *  Dependencies: Comparable.java
 *
 *  @author(s)		Scott McKay
 *  @version   		0.0.0
 *  @group			The Four Horsemen
 *  @copyright   	None
 *  @date_created   Friday, December 1st, 2017 @1:33 p.m. MST
 *
 *  Blueprint for account data-type.
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
 *  % java SecurityQuestionsController
 *
 ******************************************************************************/

package application.controller;

import java.io.IOException;

import application.Account;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SecurityQuestionsController 
{
		  private Pane 		currentPane;
		  private Account 	account;
	
	@FXML private Button 	cancelButton;
	@FXML private Button 	backButton;
	@FXML private Button	createButton;
	
	//secField: abbreviation for securityQuestionField
	@FXML private TextField secField1;
	@FXML private TextField secField2;
	@FXML private TextField secField3;
	
	//Constructor takes in account that will have security questions edited.
    public SecurityQuestionsController(Account account) 
    {
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/SecurityQuestions.fxml"));
        loader.setController(this);

        try 
        {
            currentPane = loader.load();
        } 
        catch (IOException event) 
        {
            event.printStackTrace();
        }
        
        this.account = account;
    }
    
    @FXML
    private void handleCancel()
    {
    	//Goes back to Administrator tab, discards all changes
    	LoginScreenController.getMainController().setAdminPane();
    }
    
    @FXML
    private void cancelButtonClicked()
    {
    	//Set button color to navy Blue when clicked on
    	cancelButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void cancelButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	cancelButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleBackButton()
    {
    	//Goes back to previous pane, account is sent through constructor to populate fields again.
        currentPane.getChildren().clear();
        currentPane.getChildren().addAll(new CreateAccountController(account).getPane());
    }
    
    @FXML
    private void backButtonClicked()
    {
    	//Set button color to navy Blue when clicked on
    	backButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void backButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	backButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleCreateButton()
    {
    	if (!checkParam())
    	{
    		return;
    	}
    	else
    	{
    		//Save the answers to the security questions in the accounts object:
    		account.setSecurityQuestion1(secField1.getText());
    		account.setSecurityQuestion2(secField2.getText());
    		account.setSecurityQuestion3(secField3.getText());
    		
    		//Add new account to data-base and refresh list of accounts in GUI:
    		LoginScreenController.getMainController().getAccountDB().createAccount(account);
    		LoginScreenController.getMainController().refreshUserList();
    		
    		//Save new account to Accounts.txt
            try 
            {
            	LoginScreenController.getMainController().getAccountDB().saveAccounts();
            } 
            catch (IOException event1) 
            {
                event1.printStackTrace();
            }
            
            //Return to administrator pane
            LoginScreenController.getMainController().setAdminPane();
    	}
    }
    
    @FXML
    private void createButtonClicked()
    {
    	//Set button color to navy Blue when clicked on
    	createButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void createButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	createButton.setStyle("-fx-background-color: #e53030;");
    }
    
    private boolean checkParam()
    {
    	boolean pass = true;
    	
    	if (secField1.getText().isEmpty())
    	{	//Set background color to Red
    		secField1.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{	
    		secField1.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	
    	if (secField2.getText().isEmpty())
    	{   //Set background color to Red
    		secField2.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		secField2.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	if (secField3.getText().isEmpty())
    	{   //Set background color to Red
    		secField3.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		secField3.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	
    	return pass;
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
