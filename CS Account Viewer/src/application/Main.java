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

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.input.MouseEvent;


public class Main extends Application
{
	private double xOffset = 0.0;
	private double yOffset = 0.0;

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
        stage.setTitle("Isengard");
        stage.show();       
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
