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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginScreenController implements Initializable 
{
	@FXML private Button loginButton;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Hyperlink createAccount;
	
    @FXML private Label label;
    
    	  private IOTransactions ioTransactions = new IOTransactions("src/Transactions.txt");
    	  private IOAccounts ioAccounts =  new IOAccounts("src/Accounts.txt");
    
    @FXML
    private void handleClose(MouseEvent event) 
    {
    	//This method will close the program
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    	try 
    	{
			ioTransactions.readTransactions();
		} 
    	catch (FileNotFoundException event) 
    	{
			event.printStackTrace();
		}
    	
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
    	//TODO: Check login credentials
    	
    	MainMenuController mainController = new MainMenuController();
	    Stage  stage;
	    
		stage = new Stage();
		stage.setScene(mainController.loadScene(stage, ioAccounts, ioTransactions));
		stage.show();
		
		//This will hide the login screen.
		( (Node)event.getSource() ).getScene().getWindow().hide();
    }
}