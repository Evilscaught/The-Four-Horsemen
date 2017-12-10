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

import application.Main;
import application.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ViewTransactionController
{
    private Pane currentPane;
    @FXML private Label customerText;
    @FXML private Label accountText;
    @FXML private Label dateText;
    @FXML private Label typeText;
    @FXML private Label amountText;
    @FXML private Label expensecodeText;
    @FXML private Label descriptionText;

    private int arraynum;

    @FXML
    private void initialize()
    {
    	return;
    }



    @FXML
    void handleCancel(MouseEvent event)
    {
    	LoginScreenController.getMainController().setTransactionPane();
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

        customerText.setText(current.getCustomer());
        accountText.setText(current.getRecipientAcct());
        dateText.setText(current.getDate());
        typeText.setText(current.getType());
        expensecodeText.setText(current.getCode());
        amountText.setText("" + current.getAmount());
        descriptionText.setText(current.getDescription());


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
