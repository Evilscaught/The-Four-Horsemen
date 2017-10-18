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
import javafx.stage.Stage;

public class ViewAccount {
    public Scene getScene(Stage stage) {
        
        
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

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                Scene scene = new Menu().getScene(stage);
                stage.setScene(scene);      
            }
        
    });
        
        Button applyButton = new Button();
        applyButton.setText(" Apply Changes ");
        
    
        Button exitButton = new Button();
        exitButton.setText(" Exit ");
        
        Button logoutButton = new Button();
        logoutButton.setText("Logout");

        
        grid.add(firstNameHB, 0, 0);
        grid.add(lastNameHB, 1, 0);
        grid.add(descriptionHB, 0, 1, 2, 4);
        grid.add(applyButton, 0, 5);
        grid.setHalignment(applyButton, HPos.RIGHT);
        grid.add(backButton, 1, 5);
        grid.setHalignment(backButton, HPos.LEFT);
        
//        grid.add(logoutButton, 58, 80);
//        grid.add(exitButton, 60, 80);


        Scene scene = new Scene(grid);
        return scene;
        
    }

}
