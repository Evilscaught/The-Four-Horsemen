package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu{
	
	



    public Scene getScene(Stage stage) {



        GridPane grid = new GridPane();
        grid.setMinSize(400, 400);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.TOP_LEFT);

        Button addAcctButton = new Button();
        addAcctButton.setText("Add an Account");
        //add account

        Button viewAcctButton = new Button();
        viewAcctButton.setText("View Account");
        viewAcctButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                Scene scene = new ViewAccount().getScene(stage);
                stage.setScene(scene);
            }

    });

        Button logOutButton = new Button();
        logOutButton.setText("Log out");
        logOutButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                Main main = new Main();
                main.start(stage);

            }

    });


        grid.add(viewAcctButton, 0, 0);
        grid.add(addAcctButton, 1, 0);
        grid.add(logOutButton,2,0);


        Scene scene = new Scene(grid);
        scene.setFill(Color.AQUA);
        return scene;

}


}
