package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {


	public Menu menu;


	@Override
	public void start(Stage primaryStage) {
		try {


			Group root = new Group();

			Scene scene = new Scene(root, 300,200);

			Text loginText = new Text("Login");
			loginText.setX(130);
			loginText.setY(40);
			loginText.setFont(new Font(20));

			Text userText = new Text("Username:");
			userText.setX(20);
			userText.setY(80);
			userText.setFont(new Font(14));

			Text passText = new Text("Password:");
			passText.setX(20);
			passText.setY(120);
			passText.setFont(new Font(14));


			TextField userField = new TextField();
			userField.setLayoutX(100);
			userField.setLayoutY(60);

			PasswordField passField = new PasswordField();
			passField.setLayoutX(100);
			passField.setLayoutY(100);

			Text errorText = new Text("");
			errorText.setLayoutX(40);
			errorText.setLayoutY(190);
			errorText.setFill(Color.RED);



			Button enterButton = new Button();
			enterButton.setText("Enter");
			enterButton.setLayoutX(130);
			enterButton.setLayoutY(140);

			enterButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {


					if((userField.getText().toLowerCase().equals("csadmin")) && (passField.getText().toLowerCase().equals("csci323"))) {
						Scene scene2 = new Menu2().getScene(primaryStage);
						try
						{
							scene2 = new Menu2().getScene(primaryStage);
						}
						catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						primaryStage.setScene(scene2);
					}
					else {
						errorText.setText("Incorrect username and password.");

					}


			}

			});




			ObservableList list = root.getChildren();

			list.add(loginText);
			list.add(userText);
			list.add(passText);
			list.add(userField);
			list.add(passField);
			list.add(enterButton);
			list.add(errorText);


			scene.setFill(Color.WHITE);

			primaryStage.setTitle("CS Account Viewer");

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);

			primaryStage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}


}
