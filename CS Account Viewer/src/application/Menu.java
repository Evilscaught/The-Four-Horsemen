package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu{
	
	

	public Scene getScene() {
		
		
		
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
		
		grid.add(viewAcctButton, 0, 0);
		grid.add(addAcctButton, 0, 1);


		Scene scene = new Scene(grid);
		scene.setFill(Color.AQUA);
		return scene;
		
	}
	

}
