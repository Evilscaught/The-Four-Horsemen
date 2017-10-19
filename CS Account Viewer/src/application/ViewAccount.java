package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class ViewAccount {
    @SuppressWarnings("static-access")
	public Scene getScene(Stage stage, InputOutput inout) {


        GridPane grid = new GridPane();
        grid.setMinSize(400, 250);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.TOP_LEFT);


        Label firstNameLabel = new Label("First Name: ");
        Label lastNameLabel = new Label("Last Name:");
        Label descriptionLabel = new Label("Description:");

        HBox firstNameHB = new HBox();
        TextField firstNameField = new TextField ();
        firstNameHB.getChildren().addAll(firstNameLabel, firstNameField);
        firstNameHB.setSpacing(10);

        HBox lastNameHB = new HBox();
        TextField lastNameField = new TextField ();
        lastNameHB.getChildren().addAll(lastNameLabel, lastNameField);
        lastNameHB.setSpacing(10);

        HBox descriptionHB = new HBox();
        TextArea descriptionField = new TextArea ();
        descriptionField.setPrefHeight(150);
        descriptionField.setPrefWidth(370);
        descriptionHB.getChildren().addAll(descriptionLabel, descriptionField);
        descriptionHB.setSpacing(10);

        Account[] temp = inout.getAccountArr();
        String str = "";
        int cntr = 0;

        for (Account act : temp) {
        	if (act != null)
        		str += "" + (cntr + 1) + ".] " + act.toString() + "\n"; //Scott: added 1 to center so list doesn't start at zero.
        	cntr++;
        }

        descriptionField.setText(str);

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                Scene scene = new Menu2().getScene(stage, inout);
                stage.setScene(scene);
            }

    });

        Text selectText = new Text("Select an account (by #):");

        TextField selectField = new TextField();

        Button deleteButton = new Button();
        deleteButton.setText("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>()
        {
			@Override
			public void handle(ActionEvent arg0)
			{
				//Ensure no empty text field
				if (selectField.getText() != "")
				{
					//Adjust index to match array position
					int index = Integer.parseInt(selectField.getText()) - 1;

					//Make sure user isn't entering an index out of bounds
					if (index < 0 || index >= inout.getAccountArrPos())
					{
						selectField.setStyle("-fx-background-color: #f26d6d;");
					}
					else
					{
						selectField.setStyle("-fx-background-color: white;");
						inout.deleteAccount(index); //Scott: since center is incremented by one, user entry is decremented by one to match array.
					}
				}
			}


        });

        Button applyButton = new Button();
        applyButton.setText(" Apply Changes ");

        applyButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				  Account[] temp = inout.getAccountArr();
			        String str = "";
			        int cntr = 0;

			        for (Account act : temp) {
			        	if (act != null)
			        		str += "" + (cntr + 1) + ".] "+ act.toString() + "\n";
			        	cntr++;
			        }

			        descriptionField.setText(str);
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


        //grid.add(firstNameHB, 0, 0);
        //grid.add(lastNameHB, 1, 0);
        grid.add(descriptionHB, 0, 1, 2, 4);
        grid.add(selectText, 0, 5);
        grid.setHalignment(selectText, HPos.RIGHT);
        grid.add(selectField, 1, 5);
        grid.add(deleteButton, 2, 5);
        grid.add(applyButton, 3, 5);
        grid.setHalignment(applyButton, HPos.RIGHT);
        grid.add(backButton, 2, 6);
        grid.add(exitButton, 3, 6);
        grid.setHalignment(exitButton, HPos.RIGHT);

        grid.add(logoutButton, 3, 6);
        grid.setHalignment(logoutButton, HPos.LEFT);
//        grid.add(exitButton, 60, 80);

    Text compName = new Text("Created By: The Four Horse Men");
    compName.setFont(new Font(10));
    grid.add(compName,4,7);


        Scene scene = new Scene(grid);
        return scene;

    }

}
