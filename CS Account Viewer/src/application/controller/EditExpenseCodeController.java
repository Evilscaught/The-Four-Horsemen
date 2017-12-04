package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EditExpenseCodeController 
{	
	private Pane 		currentPane;
	
	@FXML private TextField codeField;
	@FXML private TextField descriptionField;
	@FXML private Button 	saveButton;
	@FXML private Button 	cancelButton;
	@FXML private Pane		createdNotification;
	@FXML private Text		successMessage;
	@FXML private ChoiceBox<String> codeBox;
	@FXML private TextField codeField2;
	@FXML private TextField descriptionField2;
	@FXML private Button saveButton2;
	
    @FXML
    private void initialize() 
    {
    	return;
    }
    
	public EditExpenseCodeController() 
	{
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/EditTransactionCodes.fxml"));
        loader.setController(this);

        try 
        {
            currentPane = loader.load();
        } 
        catch (IOException event) 
        {
            event.printStackTrace();
        }
		
        /*codeBox.getItems().clear();
        
    	ArrayList<String> codes = LoginScreenController.getMainController().getCodesDB().getCodes();
    	
    	for (String code : codes)
    	{
    		codeBox.getItems().add(code);
    	}*/
    	
        
	}
	
    @FXML
    void saveButtonClicked(MouseEvent event) 
    {
  		if (!checkParam())
  		{
  			return;
  		}
  		else
  		{
  			LoginScreenController.getMainController().getCodesDB().addCode( descriptionField.getText(), Integer.parseInt( codeField.getText() ) );
  			
  			//Reset fields
  			descriptionField.setText("");
  			codeField.setText("");
  		}
  		
        LoginScreenController.getMainController().handleAddTransaction(event);;
    }
    
    private boolean checkParam()
    {
    	boolean pass = true;
    	
    	//Verify that the user has entered a description
    	if (descriptionField.getText().isEmpty()) 
    	{
    		//Set description field to RED
    		descriptionField.setStyle("-fx-background-color: #f26d6d");
    		
    		//Display failure message: 
      		createdNotification.setStyle("-fx-background-color: #f26d6d");
      		successMessage.setText("Invalid Arguments");
      		successMessage.setVisible(true);
    		pass = false;
    	}
    	else
    	{
    		descriptionField.setStyle("-fx-background-color: #white");
    	}
    	
    	//Verify that user has entered a number in codeField
    	try
    	{
    		Integer.parseInt(codeField.getText());
    		codeField.setStyle("-fx-background-color: #white");
    	}
    	catch(NumberFormatException nfe)
    	{
    		//Set code field to RED
    		codeField.setStyle("-fx-background-color: #f26d6d");
    		
    		//Display failure message:
      		createdNotification.setStyle("-fx-background-color: #f26d6d");
      		successMessage.setText("Invalid Arguments");
      		successMessage.setVisible(true);
    		pass = false;
    	}
    	
    	//If statements above were not satisfied, cancel the operation.
    	if (pass == false)
    	{
    		return pass;
    	}
    	
    	//Display notification that transaction code was successfully created:
    	createdNotification.setStyle("-fx-background-color: #00bc2f");  //Set createdNotication to GREEN
    	successMessage.setText("Transaction Code Successfully Created!");
    	successMessage.setVisible(true);

    	//Reset any red text fields:
  		descriptionField.setStyle("-fx-background-color: #white");
  		
  		return pass;
    }
	
    @FXML
    void cancelButtonClicked(MouseEvent event) 
    {
    	LoginScreenController.getMainController().handleAddTransaction(event);
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