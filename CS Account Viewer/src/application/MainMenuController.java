package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenuController {
    Stage primaryStage;
    InputOutput db;

    @FXML private AnchorPane adminPane;
    @FXML private ListView<String> userList;
    @FXML private TextField firstNameField, lastNameField, emailField, userNameField;
    @FXML private TextArea descriptionField;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button createAccountButton;

    public void refreshUserList() {
        Account[] temp = db.getAccountArr();

        userList.getItems().clear();

        for (Account act : temp) {
            if (act != null)
                userList.getItems().add(act.getFirstName() + " " + act.getLastName());
        }        
    }

    @FXML
    void initialize() {
        this.refreshUserList();
    }

    public Scene loadScene(Stage stage, InputOutput db) {
        BorderPane rootLayout = new BorderPane();

        this.db = db;
        primaryStage = stage;

        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
            loader.setController(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(loader.load());

            userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue != null) {
                        Account[] temp = db.getAccountArr();

                        // TODO: Implement a GetAccount in the Accounts class
                        for (Account act : temp) {
                            if (act != null && act.getFirstName().equals(newValue.split(" ")[0])) {
                                firstNameField.setText(act.getFirstName());
                                lastNameField.setText(act.getLastName());
                                emailField.setText(act.getEmail());
                                userNameField.setText(act.getUsername("")); // TODO Fix Getter String Argument
                                break;
                            }

                        }
                    }
                }
            });

            this.setAdminPane();

            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Scene(rootLayout);
    }
 
    /***********************************************************
     * GUI Listener Handlers
     *
     **********************************************************/
    
    @FXML
    void LogoutClick(MouseEvent event) {
        Main main = new Main();
        main.start(primaryStage);
    }

    @FXML
    void createAccountClick(MouseEvent event) {
        adminPane.getChildren().clear();
        adminPane.getChildren().addAll(new CreateAccountController().getPane());
    }

   
    /***********************************************************
     * Getters/Setters 
     *
     **********************************************************/

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public AnchorPane getAdminPane() {
        return adminPane;
    }

    public void setAdminPane() {

        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Admin.fxml"));
        loader.setController(this);

        try {
            adminPane.getChildren().clear();
            adminPane.getChildren().add(loader.load());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        //        this.adminPane = adminPane;
    }

    public InputOutput getDb() {
        return db;
    }


}

