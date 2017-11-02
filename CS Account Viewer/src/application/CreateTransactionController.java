package application;

import java.io.IOException;

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
    @FXML private TextField dateField;
    @FXML private TextField amountField;
    @FXML private ChoiceBox<String> transactionType;
    
    @FXML
    void saveButtonClicked(MouseEvent event) {
    	
    	double amount = Double.parseDouble(amountField.getText());
    	
        if (transactionType.getValue() == "Credit Card Deposit") {
        	Main.getMainController().getDb().createCCTransaction(customerNameField.getText(), dateField.getText(), amount, descriptionField.getText());
        }
        
        else if (transactionType.getValue() == "Check Deposit") {
        	Main.getMainController().getDb().createCheckTransaction(customerNameField.getText(), dateField.getText(), amount, descriptionField.getText());
        }
        
        else if (transactionType.getValue() == "Expense") {
        	amount = amount * -1;
        	Main.getMainController().getDb().createExpense(customerNameField.getText(), dateField.getText(), amount, descriptionField.getText());
        }
        	
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
        transactionType.getItems().add("Check Deposit");
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
