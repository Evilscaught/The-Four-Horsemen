package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddAccount 
{
	public Scene getScene(Stage stage, InputOutput IO) 
	{
		
		
		GridPane grid = new GridPane();
		grid.setMinSize(150, 200);
		grid.setPadding(new Insets(15,15,15,15));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.TOP_LEFT);
		
		Text firstnameText = new Text("First name:"), lastnameText = new Text("Last name");
		Text emailText = new Text("Email Address:"),  userText =  new Text("Username:");
		Text passText = new Text ("Password");
		
		TextField firstnameField = new TextField(), lastnameField = new TextField();
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
				if (firstnameField.getText().isEmpty() || lastnameField.getText().isEmpty() || userField.getText().isEmpty() || emailField.getText().isEmpty() || passField.getText().isEmpty())
				{
					//Set text field background to red if empty:
					if (firstnameField.getText().isEmpty())
					{
						firstnameField.setStyle("-fx-background-color: red;");
					}
					if (lastnameField.getText().isEmpty())
					{
						lastnameField.setStyle("-fx-background-color: red;");
					}
					if (userField.getText().isEmpty())
					{
						userField.setStyle("-fx-background-color: red;");
					}
					if (emailField.getText().isEmpty())
					{
						emailField.setStyle("-fx-background-color: red;");
					}
					if (passField.getText().isEmpty())
					{
						passField.setStyle("-fx-background-color: red;");
					}
				}
				//Create account if no text fields are empty
				else
				{
					IO.createAccount(firstnameField.getText(), lastnameField.getText(), userField.getText(), emailField.getText(), passField.getText());	
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
		
		
		
		
		
		grid.add(firstnameText, 0, 2);
		grid.add(firstnameField, 2, 2);
		grid.add(lastnameText, 0, 3);
		grid.add(lastnameField, 2, 3);
		grid.add(emailText, 0, 5);
		grid.add(emailField, 2, 5);
		grid.add(userText, 0, 7);
		grid.add(userField, 2, 7);
		grid.add(passText, 0, 9);
		grid.add(passField, 2, 9);
		grid.add(saveButton, 2, 11);
		grid.add(backButton,3 , 11);
		grid.add(exitButton,4, 11);
		

		Scene scene = new Scene(grid);
		return scene;	
	}
}