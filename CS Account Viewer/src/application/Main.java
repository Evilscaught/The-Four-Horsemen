/******************************************************************************
 *  Compilation:  javac Main.java
 *  Execution:    java  Main
 *  Dependencies:
 *
 *  @author(s)      Jack Cummings, Dan Bailey, Jake Wolfe, Scott McKay
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Sunday, November 19th, 2017 @9:31 a.m. MST
 *
 *  
 *
 *     *
 *
 *     *
 *
 *     *
 *
 *  BUG:
 *
 *  FEATURE:
 *
 *  NOTE: Use this class to start program. 
 *
 *  % java Main
 *
 ******************************************************************************/

package application;

import application.controller.MainMenuController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Scanner;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;


public class Main extends Application
{
	private double xOffset = 0.0;
	private double yOffset = 0.0;
	
    private static IOTransactions ioTransactions = new IOTransactions("src/Transactions.txt");
    private static IOAccounts ioAccounts =  new IOAccounts("src/Accounts.txt");

    private static MainMenuController mainController;

    private String curUser;

    /*
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginScreen.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        
        //Get the x and y coordinates of the login-screen if clicked on.
        root.setOnMousePressed(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        
        //Update the x and y coordinates if dragged.
        root.setOnMouseDragged(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();       
    }
    */
    
    
    ///*
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
        	ioAccounts.readAccounts();
        	ioTransactions.readTransactions();

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

            Text compName = new Text("Created By: The Four Horsemen");
            compName.setLayoutX(140);
            compName.setLayoutY(210);
            compName.setFont(new Font(10));

            Button exitButton = new Button();
            exitButton.setText(" Exit ");
            exitButton.setLayoutX(200);
            exitButton.setLayoutY(140);

            exitButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent arg0)
                {
                    primaryStage.close();
                }
            });

            Button enterButton = new Button();
            enterButton.setText("Enter");
            enterButton.setLayoutX(130);
            enterButton.setLayoutY(140);

            enterButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent arg0)
                {
                    if((userField.getText().toLowerCase().equals("csadmin")) && (passField.getText().toLowerCase().equals("csci323")))
                    {
                        mainController = new MainMenuController();
                        primaryStage.setScene(mainController.loadScene(primaryStage, ioAccounts, ioTransactions));

                    }
                    else if ((!userField.getText().toLowerCase().equals("csadmin")))
                    {
                     boolean temp = false;
                     String tempUser = userField.getText().toLowerCase();
                     String tempPass = passField.getText().toLowerCase();
                        temp = verify(tempUser,tempPass);
                        if (temp == true)
                        {
                            // add user controller
                            System.out.println("works");
                            
                        }
                        else
                        {
                            System.out.println("you suck");
                        }
                    }
                    else
                    {
                        errorText.setText("Incorrect username and password.");
                        primaryStage.setScene(mainController.loadScene(primaryStage, ioAccounts, ioTransactions));
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


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //*/
    
    /***********************************************************
     * Getters/Setters
     *
     **********************************************************/
    public static MainMenuController getMainController()
    {
        return mainController;
    }

    public String getCurUser()
    {
        return curUser;
    }

    public void setCurUser(String curUser)
    {
        this.curUser = curUser;
    }

    public boolean verify(String userName,String passWord)
    {
        ArrayList<Account> tmp = ioAccounts.getAccounts();
        boolean temp = false;

        for (Account act : tmp) {
            if (act.getUsername().toLowerCase().equals(userName) && act.getPassword().toLowerCase().equals(passWord))
            {
            setCurUser(userName.toLowerCase());
            return true;

            }


        }
        return false;

    }


    /***********************************************************
     * Main function
     * @param args - No argument support provided at the moment
     **********************************************************/
    public static void main(String[] args)
    {
        launch(args);
    }
}
