package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu2{
	

	public Scene getScene(Stage stage) {
		
		
		GridPane grid = new GridPane();
		grid.setMinSize(400, 400);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.TOP_LEFT);
		
		
		Button addAcctButton = new Button();
		addAcctButton.setText("Add an Account");
		
		Button viewAcctButton = new Button();
		viewAcctButton.setText("View Account");
		
	
		Button exitButton = new Button();
		exitButton.setText(" Exit ");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.close();				
			}	
		});
		
		Button logoutButton = new Button();
		logoutButton.setText("Logout");

		logoutButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main main = new Main();
				main.start(stage);
				
			}	
		});
		
		
		grid.add(viewAcctButton, 0, 0);
		grid.add(addAcctButton, 0, 1);
		grid.add(logoutButton, 55, 80);
		grid.add(exitButton, 60, 80);


		Scene scene = new Scene(grid);
		return scene;
		
	}
	

}