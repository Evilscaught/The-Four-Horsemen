/******************************************************************************
 *  Compilation:  javac MainMenuController.java
 *  Execution:    java  MainMenuController
 *  Dependencies:
 *
 *  @author(s)      Jack Cummings, Dan Bailey, Jake Wolfe, Scott McKay
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Sometime between October and November 2017
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
 *  % java MainMenuController
 *
 ******************************************************************************/

package application.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.Account;
import application.IOAccounts;
import application.IOCodes;
import application.IOTransactions;
import application.Main;
import application.Transaction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MainMenuController 
{
    	  private Stage 			primaryStage;
    	  private String 			curUser;
    	  private IOTransactions 	ioTransactions;
    	  private IOAccounts 		ioAccounts;
    	  private IOCodes			ioCodes;

    @FXML private AnchorPane 		sidePane;
    @FXML private AnchorPane 		adminPane;
    @FXML private AnchorPane 		transactionPane;
    @FXML private AnchorPane		accountOverviewPane;
    @FXML private AnchorPane		feesPane;
    @FXML private Button			printButton;
    @FXML private Button            viewTransactionButton;
    @FXML private Button			addTransactionButton;
    @FXML private Button			editTransactionButton;
    @FXML private Button 			createAccountButton;
    @FXML private Button			deleteAccountButton;
    @FXML private Button 			hideUserListButton;
    @FXML private ImageView 		logoutMainButton;
    @FXML private ListView<String> 	userList;
    @FXML private MenuBar 			menuPane;
    @FXML private ResourceBundle 	resources;
    @FXML private TabPane 			mainTabPane;
    @FXML private Tab               adminPaneTab;
    @FXML private Tab				feesPaneTab;
	@FXML private TableView 		transactionText;
	@FXML private TableColumn <Map, String> accountCol, customerCol, dateCol, typeCol, amountCol;
    @FXML private TextArea 			descriptionField;
    @FXML private SplitPane 		splitMain;
    @FXML private URL 				location;


    public String[] getUserListFirstLast() 
    {	
        String [] userListStr = new String [ioAccounts.getAccounts().size()];

        //For every account in ioAccounts, get the first and last names, and add it to userListStr[index]
        for (int index = 0; index < ioAccounts.getAccounts().size(); index++)
        {
            userListStr[index] = ioAccounts.getAccounts().get(index).getFirstName() + " " + ioAccounts.getAccounts().get(index).getLastName();
        }
        return userListStr;
    }

    public void hideUserList() 
    {
        int menuIndex = splitMain.getItems().indexOf(sidePane);
        Node temp = splitMain.getItems().get(menuIndex);

        splitMain.getItems().remove(temp);
//        mainTabPane.setPrefWidth(primaryStage.getWidth());
        hideUserListButton.setLayoutX(-8);
        hideUserListButton.setText("»");
    }

    public void addUserList() 
    {        
        splitMain.getItems().add(0, sidePane);
        splitMain.setDividerPosition(0, 0.17);
        hideUserListButton.setLayoutX(80); 
        hideUserListButton.setText("«");
    }

    public void refreshUserList() 
    {
        userList.getItems().clear();

        for (int index = 0; index < ioAccounts.getAccounts().size(); index++)
        {
            userList.getItems().add(ioAccounts.getAccounts().get(index).getFirstName() + " " + ioAccounts.getAccounts().get(index).getLastName());
        }     
    }


    @FXML
    public void initialize() 
    {
        this.refreshUserList();    
    }


    public Scene loadScene(Stage stage, IOAccounts ioAccounts, IOTransactions ioTransactions, IOCodes ioCodes, UserController userController) 
    {
        BorderPane rootLayout = new BorderPane();

        this.ioCodes   	    = ioCodes;
        this.ioAccounts     = ioAccounts;
        this.ioTransactions = ioTransactions;
        this.curUser = userController.getCurUser();
        
        primaryStage = stage;
        primaryStage.setTitle("Isengard");
        primaryStage.getIcons().add(new Image("application/view/images/program-icon.png"));

        try  
        {
            // Load root layout from FXML file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
            loader.setController(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(loader.load());
            
            //Loads the (tabs) panes into the program
            this.setAdminPane();
            this.setTransactionPane();
            this.setAccountOverviewPane();

            hideUserListButton.setPadding(Insets.EMPTY);
            hideUserListButton.setText("«");
            mainTabPane.prefWidthProperty().bind(primaryStage.widthProperty());
            menuPane.prefWidthProperty().bind(primaryStage.widthProperty());
            
            //Keeps logout button in correct position if frame is resized
            scene.widthProperty().addListener(new ChangeListener<Number>() 
            {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) 
                {
                    logoutMainButton.setLayoutX(newSceneWidth.doubleValue() - 30);
                }
            });
            
            //Checks if the user is csadmin, otherwise hide Administrator pane and user-list side panel TODO: Remove hard-coded methods
            if (!this.curUser.equals("csadmin")) 
            {
                this.hideUserList();
                this.hideUserListButton.setVisible(false);
                this.adminPaneTab.setDisable(true);
                this.feesPaneTab.setDisable(true);
            }
            return scene;
        } 
        catch (IOException event) 
        {
            event.printStackTrace();
        }

        return new Scene(rootLayout);
    }

    //Choose a Transaction to Edit Dialog
    @SuppressWarnings({ "resource", "static-access" })
    public int delTransactionDialog() 
    {
        List<String> choices = new ArrayList<>();

        int counter = 0;

        //Show list of transactions to edit.
        for(Transaction transaction : ioTransactions.getTransactions()) 
        {
            choices.add(counter + " "  + transaction.viewInfo());
            counter++;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Choose Transaction");
        dialog.setHeaderText("Choose Transaction");
        dialog.setContentText("Choose a Transaction:");

        Optional<String> result = dialog.showAndWait();

        //If user presses 'ok' without choosing a transaction:  THIS DOESN'T WORK
        /*if (result.empty() != null)
        {
            return -1;
        }*/
        //If user selects a transaction to edit:
        if (result.isPresent())
        {
            String value = result.get();
            Scanner scan = new Scanner(value).useDelimiter(" ");

            //Return the counter of the selected transaction.
            return scan.nextInt();
        }
        //If user clicks cancel, return -1.
        return -1;
    }
    
    //Choose an Account to Delete Dialog
    public String delAccountDialog () 
    {
        List<String> choices = new ArrayList<>();

        for(String acctFirstLast : getUserListFirstLast()) 
        {
            if (acctFirstLast != null)
            {
                choices.add(acctFirstLast);
            }
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Delete Account");
        dialog.setHeaderText("Account Deletion");
        dialog.setContentText("Choose account to delete:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
        {
            String choice = result.get();
            System.out.println("Your choice: " + choice);

            return choice;
        }

        return "";
        // The Java 8 way to get the response value (with lambda expression).
        // result.ifPresent(letter -> System.out.println("Your choice: " + letter));
    }
    
    @FXML
    void handleViewTransaction(MouseEvent event) {
    	int arraynum = delTransactionDialog();
    	transactionPane.getChildren().clear();
    	transactionPane.getChildren().addAll(new ViewTransactionController(arraynum).getPane());
    	
    }
    
    //---------------------------------------------------------------------------------//
    //                                GUI Listener Handlers                        	   //
    //---------------------------------------------------------------------------------//

    @FXML
    public void hideUserList(MouseEvent event) 
    {
        int menuIndex = splitMain.getItems().indexOf(sidePane);
        if(menuIndex != -1) 
        {
            this.hideUserList();
        }
        else 
        {
            this.addUserList();
        }
    }

    @FXML
    public
    void handleLogout(MouseEvent event) throws Exception 
    {
        //Close the current window.
        ( (Node)event.getSource() ).getScene().getWindow().hide();

        Stage stage = new Stage();
        Main  main  = new Main();

        main.start(stage);
    }
    
    @FXML
    public void logoutClicked()
    {
    	//TODO: Change color of lock to Blue when mouse entered
    }
    
    @FXML
    public void logoutReleased()
    {
    	//TODO: Change color of lock back to Red when mouse exited
    }
    
    //---------------------------------------------------------------------------------//
    //                                Account Overview Pane                        	   //
    //---------------------------------------------------------------------------------//
    
    private void setAccountOverviewPane()
    {
        accountOverviewPane.getChildren().clear();
        accountOverviewPane.getChildren().addAll(new AccountOverviewController(userList).getPane());
    }

    //---------------------------------------------------------------------------------//
    //                                Administrator Pane                               //
    //---------------------------------------------------------------------------------//
    
    public void setAdminPane() 
    {
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Admin.fxml"));
        loader.setController(this);

        try 
        {
            adminPane.getChildren().clear();
            adminPane.getChildren().add(loader.load());
        } 
        catch (IOException event) 
        {
            event.printStackTrace();
        }
    }
    
    @FXML
    public void handleCreateAccount(MouseEvent event) 
    {
        adminPane.getChildren().clear();
        adminPane.getChildren().addAll(new CreateAccountController().getPane());
    }
    
    @FXML
    public void createAccountClicked()
    {
    	//Set button color to navy blue when clicked on
    	createAccountButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    public void createAccountReleased()
    {
    	//Set button back to original color (Red) when click is released
    	createAccountButton.setStyle("-fx-background-color: #e53030;");
    }

    @FXML
    public void handleDeleteAccount(MouseEvent event) 
    {
        String selection = delAccountDialog();

        for (int index = 0; index < ioAccounts.getAccounts().size(); index++) 
        {
            if ((ioAccounts.getAccounts().get(index).getFirstName() + " " + ioAccounts.getAccounts().get(index).getLastName()).equals(selection))
            {
                ioAccounts.deleteAccount(index);
            }
        }
        this.refreshUserList();
    }
    
    @FXML
    public void deleteAccountClicked()
    {
    	//Set button color to navy blue when clicked on
    	deleteAccountButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    public void deleteAccountReleased()
    {
    	//Set button back to original color (Red) when click is released
    	deleteAccountButton.setStyle("-fx-background-color: #e53030;");
    }

    @FXML
    public void handleEditTransaction(MouseEvent event) 
    {
        int arrayNum = delTransactionDialog();

        //If user did not make a choice, or cancelled.
        /*if (arrayNum == -1)
        {
            return;
        }*/

        transactionPane.getChildren().clear();
        transactionPane.getChildren().addAll(new EditTransactionController(arrayNum).getPane());
    }
    
    public AnchorPane getAdminPane()
    {
        return adminPane;
    }

    //---------------------------------------------------------------------------------//
    //                                 Getters & Setters	                           //
    //---------------------------------------------------------------------------------//

    public Stage getPrimaryStage() 
    {
        return primaryStage;
    }
    
    //---------------------------------------------------------------------------------//
    //                                Transaction Pane                                 //
    //---------------------------------------------------------------------------------//

	public void setTransactionPane() 
    {
        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Transactions.fxml"));
        loader.setController(this);

        transactionPane.getChildren().clear();

        try 
        {
            transactionPane.getChildren().add(loader.load());
        } 
        catch (IOException event) 
        {
            event.printStackTrace();
        }

        //Get transactions and display them on main menu screen in transactions section.
        accountCol.setCellValueFactory(new MapValueFactory("account"));
        //accountCol.setMinWidth(130);
        customerCol.setCellValueFactory(new MapValueFactory("customer"));
        dateCol.setCellValueFactory(new MapValueFactory("date"));
        //customerCol.setMinWidth(130);
        typeCol.setCellValueFactory(new MapValueFactory("type"));
        amountCol.setCellValueFactory(new MapValueFactory("amount"));


        ObservableList<Map> allData = FXCollections.observableArrayList();
        
        String recipAct = "";

        for (Account tmp : ioAccounts.getAccounts()) 
        {
            if (tmp.getUsername().equals(this.curUser)) 
            {
                recipAct = tmp.getName();
            }
        }

        
        if (ioTransactions.getTransactions().size() != 0) 
        {
            for (int i=0; i < ioTransactions.getTransactions().size(); i++)
            {
                if (ioTransactions.getTransactions().get(i) != null) 
                {
                    Map<String, String> dataRow = new HashMap<>();
                    Transaction temp = ioTransactions.getTransactions().get(i);
                    
                    if (temp.getRecipientAcct().equals(recipAct) || this.curUser.equals("csadmin")) {
                        dataRow.put("account", temp.getRecipientAcct());
                        dataRow.put("customer", temp.getCustomer());
                        dataRow.put("date", temp.getDate());
                        dataRow.put("type", temp.getType());
                        dataRow.put("amount", "$" + new DecimalFormat("0.00").format((temp.getAmount())));
                    }
                    allData.add(dataRow);
                }
            }
            transactionText.setItems(allData);
        }
    }
    
    @FXML
    public void printButtonClicked()
    {
    	//Set button color to navy blue when clicked on
    	printButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    public void printButtonReleased()
    {
    	//Set button back to original color (Red) when click is released
    	printButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    void handleAddTransaction(MouseEvent event) 
    {
        transactionPane.getChildren().clear();
        transactionPane.getChildren().addAll(new CreateTransactionController().getPane());
    }
    
    @FXML
    public void addTransactionClicked()
    {
    	//Set button color to navy blue when clicked on
    	addTransactionButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    public void addTransactionReleased()
    {
    	//Set button back to original color (Red) when click is released
    	addTransactionButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    public void editTransactionClicked()
    {
    	//Set button color to navy blue when clicked on
    	editTransactionButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    public void editTransactionReleased()
    {
    	//Set button back to original color (Red) when click is released
    	editTransactionButton.setStyle("-fx-background-color: #e53030;");
    }
    
    @FXML
    public void viewTransactionClicked()
    {
    	//Set button color to navy blue when clicked on
    	viewTransactionButton.setStyle("-fx-background-color: #273e51;");
    }
    
    @FXML
    public void viewTransactionReleased()
    {
    	//Set button back to original color (Red) when click is released
    	viewTransactionButton.setStyle("-fx-background-color: #e53030;");
    }
    
    
    //---------------------------------------------------------------------------------//
    //                                    Data Bases                                   //
    //---------------------------------------------------------------------------------//

    public IOTransactions getTransactionDB()
    {
        return ioTransactions;
    }

    public IOAccounts getAccountDB()
    {
        return ioAccounts;
    }
    
    public IOCodes getCodesDB()
    {
    	return ioCodes;
    }
}