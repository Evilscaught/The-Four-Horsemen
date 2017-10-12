package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu{
	
	

	public Scene getScene() {
		
		GridPane grid = new GridPane();
		grid.setMinSize(400, 400);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.CENTER);
		
		
		Text test = new Text("Testing");
		
		grid.add(test, 0, 0);
		
		Scene scene = new Scene(grid);
		return scene;
		
	}
	

}
