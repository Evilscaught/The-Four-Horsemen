/******************************************************************************
 *  Compilation:  javac CreateTransactionController.java
 *  Execution:    java  CreateTransactionController
 *  Dependencies:
 *
 *  @author(s)      Dan Bailey, Jake Wolfe, Jack Cummings, Scott McKay
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Sometime between October and November
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
 *  % java CreateTransactionController
 *
 ******************************************************************************/

package application.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CreateTransactionController
{
    	  private Pane 				currentPane;

    @FXML private TextField 		customerNameField;
    @FXML private TextArea 			descriptionField;
    @FXML private DatePicker 		dateField;
    @FXML private TextField 		dollarsField;
    @FXML private TextField 		centsField;
    @FXML private ImageView			editCodesButton;
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

    	String cents;

    	if (centsField.getText() == null || centsField.getText().equals("")) {
    		cents = "00";
    	}

    	else {
    		cents = centsField.getText().substring(0, 2);
    	}

    	String str = dollarsField.getText() + "." + cents;
    	double amount = Double.parseDouble(str);
        String code = transactionCodes.getValue();

    	//Get date from DatePicker
    	String date    = ( (TextField)dateField.getEditor() ).getText();
    	String account = accountBox.getValue();

        if (transactionType.getValue() == "Credit Card Deposit")
        {
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), date, amount, descriptionField.getText(), "Credit Card", code);
        }
        else if (transactionType.getValue() == "Check Deposit")
        {
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), date, amount, descriptionField.getText(), "Check", code);
        }
        else if (transactionType.getValue() == "Expense")
        {
        	amount = amount * -1;
        	LoginScreenController.getMainController().getTransactionDB().createTransaction(account, customerNameField.getText(), date, amount, descriptionField.getText(), "Expense", code);
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

    	/*try
    	{
    		Double.parseDouble(centsField.getText());
    		centsField.setStyle("-fx-background-color: white");
    	}
    	catch(NumberFormatException nfe)
    	{
    		centsField.setStyle("-fx-background-color: #f26d6d");
    		pass = false;
    	}*/

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

    	if (transactionCodes.getValue().equals("Select Transaction Code"))
    	{
    		transactionCodes.setStyle("-fx-border-color: #f26d6d");
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

      Map <String, String> prevData = LoginScreenController.getMainController().getPrevData();

        if (prevData != null && prevData.get("scene").equals("createTransaction")) {
            this.accountBox.setValue(prevData.get("account"));
            this.customerNameField.setText(prevData.get("customer"));
            this.dateField.getEditor().setText(prevData.get("date"));
            this.dollarsField.setText(prevData.get("dollars"));
            this.centsField.setText(prevData.get("cents"));
            this.transactionType.setValue(prevData.get("type"));
            this.descriptionField.setText(prevData.get("description"));
            this.transactionCodes.setValue(prevData.get("code"));
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
    	transactionCodes.getItems().add("None");
    	transactionCodes.getSelectionModel().selectFirst();

    	for (String code : LoginScreenController.getMainController().getCodesDB().getSTCodes().keys())
    	{
          transactionCodes.getItems().add(code + ": " + LoginScreenController.getMainController().getCodesDB().getSTCodes().get(code));
    	}

    }

    @FXML
    void editCodesClicked(MouseEvent event)
    {
        LoginScreenController.getMainController().setPrevData(this.getData());
        currentPane.getChildren().clear();
        currentPane.getChildren().addAll(new EditExpenseCodeController().getPane());
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

    public Map<String, String> getData() {
        Map <String, String> temp = new HashMap<String, String>();

        temp.put("scene", "createTransaction");

        temp.put("dollars", dollarsField.getText());
        temp.put("cents", centsField.getText());
        temp.put("code", transactionCodes.getValue());
        temp.put("type", transactionType.getValue());
        //Get date from DatePicker
        temp.put("date", ( (TextField)dateField.getEditor() ).getText());

        temp.put("account", accountBox.getValue());
        temp.put("customer", customerNameField.getText());
        temp.put("description", descriptionField.getText());

        return temp;
    }

}
