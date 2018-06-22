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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.text.DecimalFormat;

public class AccountOverviewController
{
	//Button opacity, cannot be less than 0.0 or greater than 1.0
    final private double 			MIN_OPACITY = 0.3;
    final private double			MAX_OPACITY = 1.0;

		  private Pane 				currentPane;
		  private ListView<String>  userList;
		  private Account			selectedAccount;

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

	@FXML private AnchorPane		messagesPane;
	@FXML private Text			    msgNoEmptyField;
	@FXML private Text				msgAlreadyTaken;
	@FXML private Text				msgChooseAccount;



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

    //This constructor is used if returning from the edit password pane.
    public AccountOverviewController(ListView<String> userList, Account account)
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

    	//Set selected account
    	selectedAccount = account;

    	//Populates account overview with account user just changed password
        firstNameField.setText(account.getFirstName());
        lastNameField.setText(account.getLastName());
        emailField.setText(account.getEmail());
        usernameField.setText(account.getUsername());
        descriptionField.setText(account.getDescription().replace("`", ","));
        populateParam();
    }

    //Show save button if any changes to the fields are made.
    @FXML
    private void keyPressed(KeyEvent event)
    {
    	//Do not enable save button if no account has been selected
    	if (selectedAccount == null)
    	{
    		return;
    	}

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
    			//Update all parameters in the account
    			selectedAccount.setFirstName(firstNameField.getText());
    			selectedAccount.setLastName(lastNameField.getText());
    			selectedAccount.setDescription(descriptionField.getText().replace(",", "`"));
    			selectedAccount.setEmail(emailField.getText());
    			selectedAccount.setUsername(usernameField.getText());

    			//Save changes to file
    			try
    			{
					LoginScreenController.getMainController().getAccountDB().saveAccounts();
				}
    			catch (IOException exception)
    			{
					exception.printStackTrace();
				}

    			//Update user-list (left pane)
    			LoginScreenController.getMainController().refreshUserList();
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
    	if (selectedAccount == null)
    	{
    		hideAllMessages();
    		msgChooseAccount.setVisible(true);
    		messagesPane.setVisible(true);
    		return;
    	}

        currentPane.getChildren().clear();
        currentPane.getChildren().addAll(new EditPasswordController(userList, selectedAccount).getPane());
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
    	boolean pass = true;
    	if (firstNameField.getText().isEmpty())
    	{
    		//Set background color to Red
    		firstNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		firstNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	if (lastNameField.getText().isEmpty())
    	{
    		//Set background color to Red
    		lastNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		lastNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	if (usernameField.getText().isEmpty())
    	{
    		//Set background color to Red
    		usernameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		usernameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	if (emailField.getText().isEmpty())
    	{
    		//Set background color to Red
    		emailField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		emailField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}

    	if (pass == false)
    	{
    		hideAllMessages();
    		msgNoEmptyField.setVisible(true);
    		messagesPane.setVisible(true);
    	}

    	if (emailField.getText().contains(","))
    	{
    		emailField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		emailField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}

    	if (firstNameField.getText().contains(","))
    	{
    		firstNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		firstNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}

    	if (lastNameField.getText().contains(","))
    	{
    		lastNameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		lastNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}

    	if (usernameField.getText().contains(","))
    	{
    		usernameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    	}
    	else
    	{
    		usernameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}

    	for (String username : LoginScreenController.getMainController().getAccountDB().getUsernames())
    	{
    		if (usernameField.getText().toLowerCase().equals(username.toLowerCase()) && !selectedAccount.getUsername().equals(usernameField.getText()))
			{
    			usernameField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");

    			//Show error message
    			hideAllMessages();
    			msgAlreadyTaken.setVisible(true);
    			messagesPane.setVisible(true);
				pass = false;
			}
    	}


    	return pass;
    }

    private void hideAllMessages()
    {
    	msgAlreadyTaken.setVisible(false);
    	msgNoEmptyField.setVisible(false);
    	msgChooseAccount.setVisible(false);
    }

    @FXML
    private void handleCloseMessagePane()
    {
    	hideAllMessages();
    	messagesPane.setVisible(false);
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
                        	//Set selected account
                        	selectedAccount = acct;
                        	//Populates account overview
                            firstNameField.setText(acct.getFirstName());
                            lastNameField.setText(acct.getLastName());
                            emailField.setText(acct.getEmail());
                            usernameField.setText(acct.getUsername()); // TODO Fix Getter String Argument
                            descriptionField.setText(acct.getDescription().replace("`", ","));
                            accountBalance.setText("$ " + new DecimalFormat("0.00").format(acct.getaccTotal()));


                            if (acct.isAdmin())
                            {
                            	permissions.setText("Adminstrator");
                            }
                            else
                            {
                            	permissions.setText("Regular User");
                            }

                            //Reset any previously red fields
                    		firstNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
                    		lastNameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
                    		emailField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
                    		usernameField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");


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
