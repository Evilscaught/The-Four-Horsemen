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

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

import application.Main;
import application.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ViewTransactionController
{
    private boolean copied = false;
    private Pane currentPane;
    
    @FXML private Button	backButton;
    @FXML private Button	copyButton;
    @FXML private TextField customerText;
    @FXML private TextField accountText;
    @FXML private TextField dateText;
    @FXML private TextField typeText;
    @FXML private TextField amountText;
    @FXML private TextField expensecodeText;
    @FXML private TextArea descriptionText;

    private int arraynum;

    @FXML
    private void initialize()
    {
    	return;
    }

    @FXML
    private void handleBack(MouseEvent event)
    {
    	LoginScreenController.getMainController().setTransactionPane();
    }
    
    @FXML
    private void backButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	backButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void backButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	backButton.setStyle("-fx-background-color: #e53030;");
    }

    public ViewTransactionController(int arraynum)
    {

    	this.arraynum = arraynum;

        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/ViewTransaction.fxml"));
        loader.setController(this);

        try
        {
            currentPane = loader.load();
        }
        catch (IOException event)
        {
            event.printStackTrace();
        }

        Transaction current = LoginScreenController.getMainController().getTransactionDB().getTransactions().get(this.arraynum);
        currentPane.setPrefWidth(2000);
        customerText.setText(current.getCustomer());
        accountText.setText(current.getRecipientAcct());
        dateText.setText(current.getDate());
        typeText.setText(current.getType());
        expensecodeText.setText(current.getCode());
        amountText.setText("" + current.getAmount());
        descriptionText.setText(current.getDescription());

        if (dateText.getText().isEmpty())
        {
        	dateText.setText("Not Set");
        }
    }

    //This method gathers the transaction details and copies them to your clip-board
    @FXML
    private void handleCopy()
    {
    	String toString = "Customer: \"" + customerText.getText() + "\", Account: \""+ accountText.getText() + "\", Date: \"" + dateText.getText() + "\", Transaction Type: \"" + typeText.getText() + "\", Expense Code: \"" + expensecodeText.getText() + "\", Amount: \"" + amountText.getText() + "\", Description: \"" + descriptionText.getText() + "\"";
    	StringSelection stringSelection = new StringSelection(toString);
    	java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	clipboard.setContents(stringSelection, null);
    	copyButton.setText("Copied to Clipboard!");
    	copyButton.setStyle("-fx-background-color: #009905;");
    	copied = true;
    }
    
    @FXML
    private void copyButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	copyButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void copyButtonReleased()
    {
    	if (!copied)
    	{
    		//Set button back to original color (Red) when click is released
    		copyButton.setStyle("-fx-background-color: #e53030;");
    	}
    	else
    	{
        	copyButton.setStyle("-fx-background-color: #009905;");
    	}
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
