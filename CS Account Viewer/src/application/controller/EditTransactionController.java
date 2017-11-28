/******************************************************************************
 *  Compilation:  javac EditTransactionController.java
 *  Execution:    java  EditTransactionController
 *  Dependencies:
 *
 *  @author(s)      Dan Bailey, Jack Cummings, Jake Wolfe, Scott McKay
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Unknown
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
 *  NOTE: 
 *
 *  % java EditTransactionController
 *
 ******************************************************************************/

package application.controller;

import java.io.IOException;

import application.Main;
import application.Transaction;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EditTransactionController 
{
    private Pane currentPane;

    @FXML private TextField customerNameField;
    @FXML private TextArea  descriptionField;
    @FXML private TextField dateField;
    @FXML private TextField amountField;
    @FXML private ChoiceBox<String> transactionType;
    @FXML private ChoiceBox<String> accountBox;
    private int arraynum;

    //TODO: FIXME: Add codes!
    @FXML
    void saveButtonClicked(MouseEvent event) 
    {
    	
    	LoginScreenController.getMainController().getTransactionDB().deleteTransaction(arraynum);

    	double amount = Double.parseDouble(amountField.getText());
    	
    	String account = accountBox.getValue();

        if (transactionType.getValue() == "Credit Card Deposit") 
        {
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), dateField.getText(), amount, descriptionField.getText(), "Credit Card", "");
        }

        else if (transactionType.getValue() == "Check Deposit") 
        {
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), dateField.getText(), amount, descriptionField.getText(), "Check", "");
        }

        else if (transactionType.getValue() == "Expense") 
        {
        	amount = amount * -1;
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), dateField.getText(), amount, descriptionField.getText(), "Expense", "");
        }
        
        LoginScreenController.getMainController().setTransactionPane();

    }
    
    @FXML
    void deleteTransClicked(MouseEvent event) 
    {
    	LoginScreenController.getMainController().getTransactionDB().deleteTransaction(arraynum);
    	LoginScreenController.getMainController().setTransactionPane();
    }
    
    @FXML
    void backButtonClicked(MouseEvent event) 
    {
    	LoginScreenController.getMainController().setTransactionPane();
    }

    @FXML
    private void initialize() 
    {
    	return;
    }

    public EditTransactionController(int arraynum) 
    {
        this.arraynum = arraynum;
        
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/EditTransaction.fxml"));
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
        
        accountBox.getItems().clear();
        
        for (String item : LoginScreenController.getMainController().getUserListFirstLast()) 
        {
        	if (item != null) 
        	{
        		accountBox.getItems().add(item);
        	}
        }	
        
        Transaction current = LoginScreenController.getMainController().getTransactionDB().getTransactions().get(this.arraynum);
        customerNameField.setText(current.getCustomer());
        descriptionField.setText(current.getDescription());
        dateField.setText(current.getDate());
        
        double amountvalue;
        
        if (current.getAmount() < 0) 
        {
        	amountvalue = current.getAmount() * -1;
        }
        else 
        {
        	amountvalue = current.getAmount();
        }
        amountField.setText("" + amountvalue);
        
        if (current.getType() == "Credit Card") 
        {
        	transactionType.getSelectionModel().select(0);
        }
        else if (current.getType() == "Check") 
        {
        	transactionType.getSelectionModel().select(1);
        }
        else if (current.getType() ==  "Expense") 
        {
        	transactionType.getSelectionModel().select(2);
        }
        else 
        {
        	transactionType.getSelectionModel().selectFirst();
        }
        
        ObservableList<String> array = accountBox.getItems();
        
        int counter = 0;
        int index = 0;
        
        for (String item : array) 
        {
        	if (current.getRecipientAcct() == item) 
        	{
        		index = counter;
        	}
        	counter++;
        }
        
        accountBox.getSelectionModel().select(index);

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