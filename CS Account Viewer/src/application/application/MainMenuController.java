package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
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
    @FXML private AnchorPane transactionPane;
    @FXML private ListView<String> userList;
    @FXML private TextField firstNameField, lastNameField, emailField, userNameField;
    @FXML private TextArea descriptionField;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button createAccountButton;

    public String[] getUserListFirstLast() {
        Account[] temp = db.getAccountArr();
        String [] userListStr = new String [temp.length];
        
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null)
                userListStr[i] = temp[i].getFirstName() + " " + temp[i].getLastName();
        }        
        
        return userListStr;
    }
    
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
                            // TODO: add an equals method in the Accounts
                            if (act != null && act.getName().equals(newValue)) {
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
            this.setTransactionPane();

            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Scene(rootLayout);
    }
    
    public String deleteDialog () {
        List<String> choices = new ArrayList<>();
        
        for(String tmp : getUserListFirstLast()) {
            if (tmp != null)
                choices.add(tmp);
        }
 
        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Delete Account");
        dialog.setHeaderText("Account Deletion");
        dialog.setContentText("Choose account to delete:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String choice = result.get();
            System.out.println("Your choice: " + choice);
            
            return choice;
            
        }
        
        return "";
        // The Java 8 way to get the response value (with lambda expression).
        // result.ifPresent(letter -> System.out.println("Your choice: " + letter));
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

    @FXML
    void addTransactionButtonClick(MouseEvent event) {
        transactionPane.getChildren().clear();
        transactionPane.getChildren().addAll(new CreateTransactionController().getPane());
    }

    @FXML
    void deleteAccountClick(MouseEvent event) {
        String selection = deleteDialog();
        
        Account[] temp = db.getAccountArr();

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null && (temp[i].getFirstName() + " " + temp[i].getLastName()).equals(selection))
                db.deleteAccount(i);
        }        

        this.refreshUserList();
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

    public void setTransactionPane() {

        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Transactions.fxml"));
        loader.setController(this);

//        try {
//            transactionPane.getChildren().clear();
//            transactionPane.getChildren().add(new CreateTransactionController().getPane());
      transactionPane.getChildren().clear();
      
      try {
        transactionPane.getChildren().add(loader.load());
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
 //       } catch (IOException e) {
            // TODO Auto-generated catch block
 //           e.printStackTrace();
 //       } 
        //        this.adminPane = adminPane;
    }

    public InputOutput getDb() {
        return db;
    }


}
