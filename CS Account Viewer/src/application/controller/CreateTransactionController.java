package application.controller;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CreateTransactionController {
    private Pane currentPane;
    
    @FXML private TextField customerNameField;
    @FXML private TextArea descriptionField;
    @FXML private ChoiceBox<String> transactionType;
    
    @FXML
    void saveButtonClicked(MouseEvent event) {
        
    }
    
    @FXML
    void backButtonClicked(MouseEvent event) {
        Main.getMainController().setTransactionPane();
    }

    @FXML
    private void initialize () {
        
    }

    public CreateTransactionController() {
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/CreateTransaction.fxml"));
        loader.setController(this);

        try {
            currentPane = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        transactionType.getItems().clear();
        transactionType.getItems().add("Credit Card Deposit");
        transactionType.getItems().add("Cash Deposit");
        transactionType.getItems().add("Expense");
        transactionType.getSelectionModel().selectFirst();
   
    }

    
    public Pane getPane() {
        return currentPane;
    }

    public void setPane(Pane currentPane) {
        this.currentPane = currentPane;
    }


}
