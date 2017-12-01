/******************************************************************************
 *  Compilation:  javac AccountOverviewController.java
 *  Execution:    java  AccountOverviewController
 *  Dependencies: AccountOverview.fxml
 *
 *  @author(s)      Scott McKay
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Thursday, November 30th, 2017 @6:30 p.m. MST
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
 *  NOTE: TODO: Make sure that when non-administrator is logged in, TextFields are instead populated by the logged in user rather than the user-list.
 *
 *  % java AccountOverviewController
 *
 ******************************************************************************/

package application.controller;

import java.io.IOException;

import application.Account;
import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AccountOverviewController 
{
	//Button opacity, cannot be less than 0.0 or greater than 1.0
    final private double 			MIN_OPACITY = 0.3;
    final private double			MAX_OPACITY = 1.0;
	
		  private Pane 				currentPane;
		  private ListView<String>  userList;
	
	@FXML private Button 			saveButton;
	@FXML private Button			changePasswordButton;
	@FXML private Text				lastLogin;
	@FXML private Text				accountBalance;
	@FXML private Text				permissions;
	@FXML private TextArea  		descriptionField;
	@FXML private TextField 		firstNameField;
	@FXML private TextField 		lastNameField;
	@FXML private TextField 		usernameField;
	@FXML private TextField 		emailField;

	
    @FXML
    private void initialize() 
    {
    	saveButton.setOpacity(MIN_OPACITY);
    	return;
    }
    
    public AccountOverviewController(ListView<String> userList) 
    {
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AccountOverview.fxml"));
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
        
        //Populates all the TextFields with selected account
        populateParam();
    }
    
    //Show save button if any changes to the fields are made.
    @FXML
    private void keyPressed(KeyEvent event)
    {
    	saveButton.setOpacity(MAX_OPACITY);
    }
    
    @FXML
    private void handleSave(MouseEvent event)
    {
    	//Only save if any of the fields have been modified
    	if (saveButton.getOpacity() == MAX_OPACITY)
    	{
    		saveButton.setOpacity(MIN_OPACITY);
    		if (checkParam())
    		{
    			//TODO: Update account
    		}
    	}
    }
    
    @FXML
    private void saveButtonClicked()
    {
    	//Set button color to navy blue when clicked on (only if save button is available)
    	if (saveButton.getOpacity() == MAX_OPACITY)
    	{
    		saveButton.setStyle("-fx-background-color: #273e51;");
    	}
    }
    
    @FXML
    private void saveButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	saveButton.setStyle("-fx-background-color: #e53030;");
    }
    
    //Prompt password change
    @FXML
    private void handleChangePassword()
    {
    	//TODO: Implement me
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
    	//TODO: Check parameters and update fields if correct or incorrect
    	return true;
    }
    
    private void populateParam()
    {
    	//TODO: Make sure that when non-administrator is logged in, TextFields are instead populated by the logged in user rather than the user-list.
        userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 			
        {
            //NOTICE: Few modifications occurred here, only removed the while statement that checks for null Accounts. @Scott McKay
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                if (newValue != null) 
                {
                    // TODO: Implement a GetAccount in the Accounts class
                    for (Account acct : LoginScreenController.getMainController().getAccountDB().getAccounts()) 
                    {
                        // TODO: add an equals method in the Accounts
                        if (acct.getName().equals(newValue)) 
                        {
                            firstNameField.setText(acct.getFirstName());
                            lastNameField.setText(acct.getLastName());
                            emailField.setText(acct.getEmail());
                            usernameField.setText(acct.getUsername()); // TODO Fix Getter String Argument
                            descriptionField.setText(acct.getDescription());
                            break;
                        }
                    }
                } 
            }
        });
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
