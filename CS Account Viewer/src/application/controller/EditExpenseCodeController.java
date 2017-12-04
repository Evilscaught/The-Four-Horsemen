/******************************************************************************
 *  Compilation:  javac EditExpenseCodeController.java
 *  Execution:    java  EditExpenseCodeController
 *  Dependencies: 
 *
 *  @author(s)      Scott McKay, Jake Wolfe
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Monday, November 27th, 2017 
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
 *  % java EditExpenseCodeController
 *
 ******************************************************************************/

package application.controller;

import java.io.IOException;
import java.util.Scanner;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EditExpenseCodeController 
{	
    final private double    MAX_OPACITY = 1.0;
    final private double    MIN_OPACITY = 0.3;
    
    	  private String    oldKey;
	 	  private Pane 		currentPane;
	
	@FXML private TextField codeField;
	@FXML private TextField descriptionField;
	@FXML private Button 	createCodeButton;
	@FXML private Button 	backButton;
	@FXML private Button	editButton;
	@FXML private Button	deleteButton;
	@FXML private ListView<String>  codesList;
	
	@FXML private AnchorPane editCodePane;
	@FXML private Button	 cancelButton;
	@FXML private Button     saveChangesButton;
	@FXML private TextField  newDescriptionField;
	@FXML private TextField  newCodeField;
	@FXML private Hyperlink	 oldTransactionCode;
	
	@FXML private AnchorPane msgPane;
	@FXML private Text		 msgHead;
	@FXML private Text	     msgDesc;
	@FXML private Text 		 closeMessagesPane;
	

	@FXML private Button 	saveButton;

	@FXML private Pane		createdNotification;
	@FXML private Text		successMessage;
	@FXML private ChoiceBox<String> codeBox;
	@FXML private TextField codeField2;
	@FXML private TextField descriptionField2;
	@FXML private Button saveButton2;

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
	}
	
    @FXML
    private void initialize() 
    {
    	refreshCodesList();
    	return;
    }

	//-----------------------------------------------------------------//
	//                         Button Listeners                        //
	//-----------------------------------------------------------------//
	
    @FXML
    private void handleCreateCode(MouseEvent event) 
    {
    	//Set button as unavailable until another code is selected
		deleteButton.setOpacity(MIN_OPACITY);
		editButton.setOpacity(MIN_OPACITY);
    	
  		if (!checkParam())
  		{
  			return;
  		}
  		else
  		{
  			LoginScreenController.getMainController().getCodesDB().addCode( descriptionField.getText(), Integer.parseInt( codeField.getText() ) );
  			
  			//TODO: Remove statement above and replace with statement below:
  			descriptionField.setText(descriptionField.getText().replaceAll(":", ""));
  			LoginScreenController.getMainController().getCodesDB().put(descriptionField.getText(), Integer.parseInt(codeField.getText()));
  			refreshCodesList();
  			
  			//Reset fields
  			descriptionField.setText("");
  			codeField.setText("");
  		}
  		
        LoginScreenController.getMainController().handleAddTransaction(event);;
    }
    
    
    @FXML
    private void createCodeClicked()
    {
    	//Set button color to navy blue when clicked on
    	createCodeButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void createCodeReleased()
    {
    	//Set button back to original color (Red) when click is released
    	createCodeButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleBack(MouseEvent event) 
    {
    	LoginScreenController.getMainController().handleAddTransaction(event);
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
    
    @FXML
    private void handleEdit()
    {
    	if (editButton.getOpacity() == MAX_OPACITY)
    	{
    		oldTransactionCode.setText(codesList.getSelectionModel().getSelectedItem());
    		editCodePane.setVisible(true);
    		
    		//Get the current key of the code that user wants to edit
    		Scanner getKey = new Scanner(codesList.getSelectionModel().getSelectedItem());
    		getKey.useDelimiter(":");
    		oldKey = getKey.next();
    		getKey.close();	
    	}
    }
    
    @FXML
    private void editButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	if (editButton.getOpacity() == MAX_OPACITY)
    	{
    		editButton.setStyle("-fx-background-color: #273e51;");
    	}
    }
    
    @FXML
    private void editButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	editButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleDelete() throws Exception
    { 	
    	if (deleteButton.getOpacity() == MAX_OPACITY)
    	{
	    	//Get the code from the codes list:
	    	Scanner getKey = new Scanner(codesList.getSelectionModel().getSelectedItem());   
	    	getKey.useDelimiter(":");
	    	
	    	//Parse the key and delete that key from ioCodes.
	    	LoginScreenController.getMainController().getCodesDB().getSTCodes().delete(getKey.next());
	    	
	    	//Save changes
	    	LoginScreenController.getMainController().getCodesDB().saveCodes();
	    	
	    	getKey.close();
	    	refreshCodesList();
	    	
	    	//Set button back to original color (Red) when click is released
	    	deleteButton.setStyle("-fx-background-color: #e53030;");
	    	
	    	//Set button as unavailable until another code is selected
			deleteButton.setOpacity(MIN_OPACITY);
			editButton.setOpacity(MIN_OPACITY);
    	}
    }
    
    @FXML
    private void deleteButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	if (deleteButton.getOpacity() == MAX_OPACITY)
    	{
    		deleteButton.setStyle("-fx-background-color: #273e51;");
    	}
    }
    
    @FXML
    private void deleteButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	deleteButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML 
    private void codesClicked()
    {
    	if (codesList.getSelectionModel().getSelectedItem() != null)
    	{
    		deleteButton.setOpacity(MAX_OPACITY);
    		editButton.setOpacity(MAX_OPACITY);
    	}
    }
    
	//-----------------------------------------------------------------//
	//           Button Listeners | Edit Existing Code Pane            //
	//-----------------------------------------------------------------//
    
    @FXML
    private void handleSaveChanges() throws Exception
    {
    	//Set button as unavailable until another code is selected
		deleteButton.setOpacity(MIN_OPACITY);
		editButton.setOpacity(MIN_OPACITY);
    	
    	
    	if (checkEditedParam())
    	{	
    		descriptionField.setText(descriptionField.getText().replaceAll(":", ""));
    		
    		LoginScreenController.getMainController().getCodesDB().delete(oldKey);
    		LoginScreenController.getMainController().getCodesDB().put(newDescriptionField.getText(), Integer.parseInt(newCodeField.getText()));
    		LoginScreenController.getMainController().getCodesDB().saveCodes();
    		
    		refreshCodesList();
    		editCodePane.setVisible(false);
    	}
    	else
    	{
    		return;
    	}
    }
    
    @FXML
    private void saveButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	saveChangesButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void saveButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	saveChangesButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    private void handleCancel()
    {
    	newDescriptionField.setText("");
    	newCodeField.setText("");
    	editCodePane.setVisible(false);
    }
    
    @FXML
    private void cancelButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	cancelButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    private void cancelButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	cancelButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML 
    private void populateParam()
    {
		//Get the current key of the code that user wants to edit
		Scanner getKey = new Scanner(oldTransactionCode.getText());
		getKey.useDelimiter(":");

		newDescriptionField.setText(getKey.next());
		newCodeField.setText(getKey.next());
		newCodeField.setText(newCodeField.getText().replaceAll(" ", ""));
		getKey.close();
    }
    
    
	//-----------------------------------------------------------------//
	//           Button Listeners | Messages Pane		               //
	//-----------------------------------------------------------------//
    
    @FXML
    private void closeMessagesPane() 
    {
    	msgPane.setVisible(false);
    }
    
	//-----------------------------------------------------------------//
	//                          Other Methods    		               //
	//-----------------------------------------------------------------//
    
    private boolean checkEditedParam()
    {
    	boolean pass = true;
    	//Verify that user has entered a description:
    	if (newDescriptionField.getText().isEmpty())
    	{
    		//Set description field to RED
    		newDescriptionField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		pass = false;
    		
    		//Display failure message: 
    	    msgHead.setText("WARNING!");
    	    msgDesc.setText("Fields Cannot be Empty!");
    	    msgPane.setVisible(true);
    	}
    	else
    	{
    		newDescriptionField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	
    	//Verify that user has entered a number in codeField
    	try
    	{
    		Integer.parseInt(newCodeField.getText());
    		newCodeField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	catch(NumberFormatException nfe)
    	{
    		//Set code field to RED
    		newCodeField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    	
    		if (pass == true)
    		{
    			//Display failure message:
    			msgHead.setText("WARNING!");
    			msgDesc.setText("Only numbers in Key Field!");
    			msgPane.setVisible(true);
    		}
    		
    		pass = false;
    	}
    	
    	
    	if (pass == true)
    	{
    	    msgHead.setText("Success!");
    	    msgDesc.setText("Transaction Code Edited");
    	    msgPane.setVisible(true);
    	}
    	
    	return pass;
    }
    
    private boolean checkParam()
    {
    	boolean pass = true;
    	
    	//Verify that the user has entered a descriptionnewDescriptionField
    	if (descriptionField.getText().isEmpty()) 
    	{
    		//Set description field to RED
    		descriptionField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		
    		//Display failure message: 
    	    msgHead.setText("WARNING!");
    	    msgDesc.setText("Fields Cannot be Empty!");
    	    msgPane.setVisible(true);
    		
    		pass = false;
    	}
    	else
    	{
    		descriptionField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	
    	//Verify that user has entered a number in codeField
    	try
    	{
    		Integer.parseInt(codeField.getText());
    		codeField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
    	}
    	catch(NumberFormatException nfe)
    	{
    		//Set code field to RED
    		codeField.setStyle("-fx-background-color: #f26d6d; -fx-border-color: #000000;");
    		
    		if (pass == true)
    		{
    			//Display failure message:
    			msgHead.setText("WARNING!");
    			msgDesc.setText("Only numbers in Key Field!");
    			msgPane.setVisible(true);
    		}
    		
    		pass = false;
    	}
    	
    	//If statements above were not satisfied, cancel the operation.
    	if (pass == false)
    	{
    		return pass;
    	}
    	
    	//Display notification that transaction code was successfully created:
    	if (pass == true)
    	{
    		msgHead.setText("Success!");
    		msgDesc.setText("Transaction Code Created");
    		msgPane.setVisible(true);
    	}

    	//Reset any red text fields:
  		descriptionField.setStyle("-fx-background-color: white; -fx-border-color: #000000;");
  		
  		return pass;
    }
    
    private void refreshCodesList()
    {
        codesList.getItems().clear();
        
        for (String code : LoginScreenController.getMainController().getCodesDB().getSTCodes().keys())
        {
          codesList.getItems().add(code + ": " + LoginScreenController.getMainController().getCodesDB().getSTCodes().get(code));
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