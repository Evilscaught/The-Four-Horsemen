package application;

import java.io.InputStream;

import application.controller.MainMenuController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    private static InputOutput inout = new InputOutput();

    private static MainMenuController mainController;

    private String curUser;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void start(Stage primaryStage) {
        try {
            
//           System.out.println(path);
        	inout.readAccounts("src/Accounts.txt");
        	inout.readTransactions("src/Transactions.txt");

//            inout.readAccounts(path);
            Group root = new Group();

            Scene scene = new Scene(root, 300,220);

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

            Text compName = new Text("Created By: The Four Horse Men");
            compName.setLayoutX(140);
            compName.setLayoutY(210);
            compName.setFont(new Font(10));

            Button exitButton = new Button();
            exitButton.setText(" Exit ");
            exitButton.setLayoutX(200);
            exitButton.setLayoutY(140);
            exitButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    primaryStage.close();
                }
            });

            Button enterButton = new Button();
            enterButton.setText("Enter");
            enterButton.setLayoutX(130);
            enterButton.setLayoutY(140);

            enterButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    if((userField.getText().toLowerCase().equals("csadmin")) && (passField.getText().toLowerCase().equals("csci323"))) {
                        mainController = new MainMenuController();
                        primaryStage.setScene(mainController.loadScene(primaryStage, inout, "admin");
                    }
                    else {
                        errorText.setText("Incorrect username and password.");
                        primaryStage.setScene(mainController.loadScene(primaryStage, inout, userField.getText()));

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
            list.add(exitButton);
            list.add(compName);

            scene.setFill(Color.WHITE);

            primaryStage.setTitle("Cacheacct");

            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(scene);

            primaryStage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /***********************************************************
     * Getters/Setters 
     *
     **********************************************************/
    public static MainMenuController getMainController() {
        return mainController;
    }

    public String getCurUser() {
        return curUser;
    }

    public void setCurUser(String curUser) {
        this.curUser = curUser;
    }


    /***********************************************************
     * Main function
     * @param args - No argument support provided at the moment
     **********************************************************/
    public static void main(String[] args) {
        launch(args);
    }


}