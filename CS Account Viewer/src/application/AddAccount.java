/******************************************************************************
 *  Compilation:  javac AddAccount.java
 *  Execution:    java  AddAccount
 *  Dependencies: 
 *    
 *  @author(s)		Jake Wolfe, Jack Cummings, Scott McKay, Dan Wolfe
 *  @version   		0.0.1
 *  @group			The Four Horsemen
 *  @copyright   	None
 *  @date_created   Monday, October 16th, 2017 @6:49 p.m. MST
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
 *  UN-IMPLEMENTED FEATURES: Account creation is prevented if text fields are
 *  empty, however, using spaces as characters will not halt user.
 *
 *  NOTE: Window that allows user to create accounts
 *
 *  % java Account
 *
 ******************************************************************************/

package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddAccount 
{
	@SuppressWarnings("static-access")
	public Scene getScene(Stage stage, InputOutput IO) 
	{	
		GridPane grid = new GridPane();
		grid.setMinSize(150, 200);
		grid.setPadding(new Insets(15,15,15,15));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.TOP_LEFT);
		
		Text firstnameText = new Text("First name:"), lastnameText = new Text("Last name:");
		Text emailText = new Text("Email Address:"),  userText =  new Text("Username:");
		Text passText = new Text ("Password:");
		Text confirmation = new Text("Acct Added");
		
		TextField firstNameField = new TextField(), lastNameField = new TextField();
		TextField emailField = new TextField(), userField = new TextField();
		TextField passField = new TextField();
		
		
		Button exitButton = new Button();
		exitButton.setText(" Exit ");
		exitButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent arg0) 
			{
				stage.close();
			}
		});
		
		Button saveButton = new Button();
		saveButton.setText("    Save    ");
		saveButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent arg0) 
			{
				//Make sure no text fields are empty
				if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || userField.getText().isEmpty() || emailField.getText().isEmpty() || passField.getText().isEmpty())
				{
					confirmation.setVisible(false);
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
					if (userField.getText().isEmpty())
					{
						userField.setStyle("-fx-background-color: #f26d6d;");
					}
					else
					{
						userField.setStyle("-fx-background-color: white;");
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
				}
				//Create account if no text fields are empty
				else
				{
					//Create Account
					IO.createAccount(firstNameField.getText(), lastNameField.getText(), userField.getText(), emailField.getText(), passField.getText());	
					
					//Reset all text fields
					firstNameField.setStyle("-fx-background-color: white;");
					lastNameField.setStyle("-fx-background-color: white;");
					userField.setStyle("-fx-background-color: white;");
					emailField.setStyle("-fx-background-color: white;");
					passField.setStyle("-fx-background-color: white;");
					firstNameField.setText("");
					lastNameField.setText("");
					userField.setText("");
					emailField.setText("");
					passField.setText("");
					
					//Notify user that account has been created.
					confirmation.setVisible(true);
					grid.add(confirmation, 0, 11);
				}
			}
		});
		
		Button backButton = new Button();
		backButton.setText("  Back  ");
		backButton.setOnAction(new EventHandler<ActionEvent>() 
		{

			@Override
			public void handle(ActionEvent arg0) 
			{
				Scene scene2 = new Menu2().getScene(stage, IO);
				stage.setScene(scene2);
			}
		}
				);
		
        Button logoutButton = new Button();
        logoutButton.setText("Logout");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Main main = new Main();
                main.start(stage);

            }
        });
		
		
		
		
		grid.add(firstnameText, 0, 2);
		grid.add(firstNameField, 2, 2);
		grid.add(lastnameText, 0, 3);
		grid.add(lastNameField, 2, 3);
		grid.add(emailText, 0, 5);
		grid.add(emailField, 2, 5);
		grid.add(userText, 0, 7);
		grid.add(userField, 2, 7);
		grid.add(passText, 0, 9);
		grid.add(passField, 2, 9);
		grid.add(saveButton, 2, 11);
		grid.add(logoutButton, 2, 11);
		grid.setHalignment(logoutButton, HPos.RIGHT);
		grid.add(backButton,3 , 11);
		grid.add(exitButton,4, 11);
		

		Scene scene = new Scene(grid);
		return scene;	
	}
}