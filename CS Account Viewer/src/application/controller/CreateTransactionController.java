package application.controller;

import java.io.IOException;
import java.util.Arrays;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CreateTransactionController 
{
    	  private Pane currentPane;

    @FXML private TextField customerNameField;
    @FXML private TextArea descriptionField;
    @FXML private DatePicker dateField;
    @FXML private TextField dollarsField;
    @FXML private TextField centsField;
    @FXML private ChoiceBox<String> transactionType;
    @FXML private ChoiceBox<String> accountBox;
    @FXML private ChoiceBox<String> transactionCodes;

    @FXML
    private void initialize() 
    {
    	return;
    }
    
    @FXML
    void saveButtonClicked(MouseEvent event) 
    {
    	//Check that all parameters have been correctly filled by user
    	if (!checkParam())
    	{
    		return;
    	}
    	
    	String str = dollarsField.getText() + "." + centsField.getText().substring(0, 2);
    	double amount = Double.parseDouble(str);
    	System.out.println(amount);
    	
    	//Get date from DatePicker
    	String date    = ( (TextField)dateField.getEditor() ).getText();
    	String account = accountBox.getValue();

        if (transactionType.getValue() == "Credit Card Deposit") 
        {
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), date, amount, descriptionField.getText(), "Credit Card");
        }
        else if (transactionType.getValue() == "Check Deposit") 
        {
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), date, amount, descriptionField.getText(), "Check");
        }
        else if (transactionType.getValue() == "Expense") 
        {
        	amount = amount * -1;
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), date, amount, descriptionField.getText(), "Expense");
        }

        LoginScreenController.getMainController().setTransactionPane();
    }
    
    public boolean checkParam()
    {
    	boolean pass = true;
    	//Verify that user has entered a valid currency amount:
    	try
    	{
    		Double.parseDouble(dollarsField.getText());
    		dollarsField.setStyle("-fx-background-color: white");
    	}
    	catch(NumberFormatException nfe)
    	{
    		dollarsField.setStyle("-fx-background-color: #f26d6d");
    		pass = false;
    	}
    	
    	try
    	{
    		Double.parseDouble(centsField.getText());
    		centsField.setStyle("-fx-background-color: white");
    	}
    	catch(NumberFormatException nfe)
    	{
    		centsField.setStyle("-fx-background-color: #f26d6d");
    		pass = false;
    	}

    	//Ensure that user has chosen a transaction type:
    	if (transactionType.getValue() == "Select Transaction Type")
    	{
    		//Set border to Red
    		transactionType.setStyle("-fx-border-color: #f26d6d");
    		pass = false;
    	}
    	
    	//Ensure that user has chosen an account:
    	if (accountBox.getValue() == "Choose Account")
    	{
    		//Set border to Red
    		accountBox.setStyle("-fx-border-color: #f26d6d");
    		pass = false;
    	}
    	
    	//Ensure that user enters a name, and that the name does not begin with a space.
    	if ( (customerNameField.getText().isEmpty()))
    	{
    		customerNameField.setStyle("-fx-background-color: #f26d6d");
    		pass = false;
    	}
    	else
    	{
    		customerNameField.setStyle("-fx-background-color: white");
    	}
    	return pass;
    }
    
    public void centTyped(KeyEvent event)
    {
    	if (centsField.getText().length() > 1)
    	{
    		event.consume();
    	}
    }

    @FXML
    void handleCancel(MouseEvent event) 
    {
    	LoginScreenController.getMainController().setTransactionPane();
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

        createTypeBox();
        createAcctBox();
        createCodeBox();
    }
    
    //Add transaction types in drop down choice box
    public void createTypeBox()
    {
        transactionType.getItems().clear();
        transactionType.getItems().add("Select Transaction Type");
        transactionType.getItems().add("Credit Card Deposit");
        transactionType.getItems().add("Check Deposit");
        transactionType.getItems().add("Expense");
        transactionType.getSelectionModel().selectFirst();
    }

    //Add transaction codes in drop down choice box.
    public void createCodeBox()
    {
    	transactionCodes.getItems().clear();
    	transactionCodes.getItems().add("Select Transaction Code");
    	transactionCodes.getSelectionModel().selectFirst();
    }
    
    //Add accounts in drop down choice box.
    public void createAcctBox()
    {
    	String[] accounts = LoginScreenController.getMainController().getUserListFirstLast();
        Arrays.sort(accounts);
        
        accountBox.getItems().clear();
        accountBox.getItems().add("Choose Account");
        
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