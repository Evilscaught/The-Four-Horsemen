package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu2{


	public Scene getScene(Stage stage, InputOutput inout) {


		GridPane grid = new GridPane();
		grid.setMinSize(400, 400);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.TOP_LEFT);

		/*GridPane titleGrid = new GridPane();
		titleGrid.setMinSize(400, 400);
		titleGrid.setPadding(new Insets(10,10,10,10));
		titleGrid.setVgap(5);
		titleGrid.setHgap(5);
		titleGrid.setAlignment(Pos.TOP_LEFT);*/




		Button addAcctButton = new Button();
		addAcctButton.setText("Add an Account");
		addAcctButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				 Scene scene3 = new AddAccount().getScene(stage, inout);
				 stage.setScene(scene3);


			}// handle
		});// ActionEvent


		Button viewAcctButton = new Button();
		viewAcctButton.setText("View Account");
		viewAcctButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Scene scene2 = new ViewAccount().getScene(stage, inout);
				stage.setScene(scene2);
			}



		});


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

		Text compName = new Text("Created by The Four Horse Men");
		compName.setFont(new Font(10));

		Text softName = new Text("Welome to Cacheacct");
		softName.setFont(new Font(30));

		//titleGrid.add(softName,0,0);

		grid.add(viewAcctButton, 0, 1);
		grid.add(addAcctButton, 1, 1);
		grid.add(logoutButton, 2, 1);
		grid.add(exitButton,3,1);
		grid.add(compName,3,80);


		Scene scene = new Scene(grid);
		return scene;

	}


}
