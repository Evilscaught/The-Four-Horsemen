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

public class CreateTransactionController 
{
    private Pane currentPane;

    @FXML private TextField customerNameField;
    @FXML private TextArea descriptionField;
    @FXML private TextField dateField;
    @FXML private TextField amountField;
    @FXML private ChoiceBox<String> transactionType;
    @FXML private ChoiceBox<String> accountBox;

    @FXML
    void saveButtonClicked(MouseEvent event) 
    {

    	double amount = Double.parseDouble(amountField.getText());
    	
    	String account = accountBox.getValue();

        if (transactionType.getValue() == "Credit Card Deposit") 
        {
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), dateField.getText(), amount, descriptionField.getText(), "Credit Card");
        }

        else if (transactionType.getValue() == "Check Deposit") 
        {
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), dateField.getText(), amount, descriptionField.getText(), "Check");
        }

        else if (transactionType.getValue() == "Expense") 
        {
        	amount = amount * -1;
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), dateField.getText(), amount, descriptionField.getText(), "Expense");
        }

        LoginScreenController.getMainController().setTransactionPane();
    }

    @FXML
    void handleCancel(MouseEvent event) 
    {
    	LoginScreenController.getMainController().setTransactionPane();
    }

    @FXML
    private void initialize () 
    {
    	return;
    }

    public CreateTransactionController() 
    {
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/CreateTransaction.fxml"));
        loader.setController(this);

        try 
        {
            currentPane = loader.load();
        } 
        catch (IOException event) 
        {
            event.printStackTrace();
        }

        transactionType.getItems().clear();
        transactionType.getItems().add("Credit Card Deposit");
        transactionType.getItems().add("Check Deposit");
        transactionType.getItems().add("Expense");
        transactionType.getSelectionModel().selectFirst();
        
        String[] accounts = LoginScreenController.getMainController().getUserListFirstLast();
        
        accountBox.getItems().clear();
        
        for (String item : accounts) 
        {
        	if (item != null) 
        	{
        		accountBox.getItems().add(item);
        	}
        }
        
        accountBox.getSelectionModel().selectFirst();
    }

    public Pane getPane() 
    {
        return currentPane;
    }

    public void setPane(Pane currentPane) 
    {
        this.currentPane = currentPane;
    }
}