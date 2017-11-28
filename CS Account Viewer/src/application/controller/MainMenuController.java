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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenuController 
{
    	  private Stage 			primaryStage;
    	  private IOTransactions 	ioTransactions;
    	  private IOAccounts 		ioAccounts;
    	  private String 			curUser;

    @FXML private SplitPane 		splitMain;
    @FXML private AnchorPane 		sidePane;
    @FXML private TabPane 			mainTabPane;
    @FXML private AnchorPane 		adminPane;
    @FXML private AnchorPane 		transactionPane;
    @FXML private ListView<String> 	userList;
    @FXML private TextField 		firstNameField, lastNameField, emailField, userNameField;
    @FXML private TextArea 			descriptionField;
    @FXML private ResourceBundle 	resources;
    @FXML private URL 				location;
    @FXML private Button 			createAccountButton;
    @FXML private Button 			hideUserListButton;
    @FXML private Label 			logoutMainButton;
    @FXML private MenuBar 			menuPane;

    @FXML private TableView 		transactionText;
    @FXML private TableColumn <Map, String> accountCol, customerCol, typeCol, amountCol;

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
        mainTabPane.setPrefWidth(primaryStage.getWidth());
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
    void initialize() 
    {
        this.refreshUserList();    
    }


    public Scene loadScene(Stage stage, IOAccounts ioAccounts, IOTransactions ioTransactions, UserController userController) 
    {
        BorderPane rootLayout = new BorderPane();

        this.ioAccounts     = ioAccounts;
        this.ioTransactions = ioTransactions;
        primaryStage = stage;
        stage.setTitle("Isengard");
        stage.getIcons().add(new Image("application/view/images/program-icon.png"));

        try 
        {
            // Load root layout from FXML file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
            loader.setController(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(loader.load());

            userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() 			
            {
                //NOTICE: Few modifications occurred here, only removed the while statement that checks for null Accounts. @Scott McKay
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
                {
                    if (newValue != null) 
                    {
                        // TODO: Implement a GetAccount in the Accounts class
                        for (Account acct : ioAccounts.getAccounts()) 
                        {
                            // TODO: add an equals method in the Accounts
                            if (acct.getName().equals(newValue)) 
                            {
                                firstNameField.setText(acct.getFirstName());
                                lastNameField.setText(acct.getLastName());
                                emailField.setText(acct.getEmail());
                                userNameField.setText(acct.getUsername()); // TODO Fix Getter String Argument
                                break;
                            }
                        }
                    } 
                }
            });

            this.setAdminPane();
            this.setTransactionPane();

            hideUserListButton.setPadding(Insets.EMPTY);
            hideUserListButton.setText("«");
            mainTabPane.prefWidthProperty().bind(primaryStage.widthProperty());
            menuPane.prefWidthProperty().bind(primaryStage.widthProperty());
//            logoutMainButton.layoutXProperty().bind(primaryStage.widthProperty());
            
            scene.widthProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                    logoutMainButton.setLayoutX(newSceneWidth.doubleValue() - 75);
                }
            });
            
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

        int counter = 1;

        //Show list of transactions to edit.
        for(Transaction transaction : ioTransactions.getTransactions()) 
        {
            choices.add(counter + ".] "  + transaction.viewInfo());
            counter++;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Edit Transaction");
        dialog.setHeaderText("Edit Transaction");
        dialog.setContentText("Choose Transaction to Edit:");

        Optional<String> result = dialog.showAndWait();

        //If user presses 'ok' without choosing a transaction:
        if (result.empty() != null)
        {
            return -1;
        }
        //If user selects a transaction to edit:
        else if (result.isPresent())
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

    /***********************************************************
     * GUI Listener Handlers
     *
     **********************************************************/

    @FXML
    void hideUserList(MouseEvent event) 
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
    void LogoutClick(MouseEvent event) throws Exception 
    {
        //Close the current window.
        ( (Node)event.getSource() ).getScene().getWindow().hide();

        Stage stage = new Stage();
        Main  main  = new Main();

        main.start(stage);
    }

    @FXML
    void createAccountClick(MouseEvent event) 
    {
        adminPane.getChildren().clear();
        adminPane.getChildren().addAll(new CreateAccountController().getPane());
    }

    @FXML
    void addTransactionButtonClick(MouseEvent event) 
    {
        transactionPane.getChildren().clear();
        transactionPane.getChildren().addAll(new CreateTransactionController().getPane());
    }

    //Note to Self: This method has been successfully converted @Scott McKay
    @FXML
    void deleteAccountClick(MouseEvent event) 
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
    void editTransClicked(MouseEvent event) 
    {
        int arrayNum = delTransactionDialog();

        //If user did not make a choice, or cancelled.
        if (arrayNum == -1)
        {
            return;
        }

        transactionPane.getChildren().clear();
        transactionPane.getChildren().addAll(new EditTransactionController(arrayNum).getPane());
    }

    /***********************************************************
     * Getters/Setters
     *
     **********************************************************/

    public Stage getPrimaryStage() 
    {
        return primaryStage;
    }

    public AnchorPane getAdminPane()
    {
        return adminPane;
    }

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
        //this.adminPane = adminPane;
    }

    public void setTransactionPane() 
    {

        // Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Transactions.fxml"));
        loader.setController(this);

        //        try {
        //            transactionPane.getChildren().clear();
        //            transactionPane.getChildren().add(new CreateTransactionController().getPane());
        transactionPane.getChildren().clear();

        try 
        {
            transactionPane.getChildren().add(loader.load());
        } 
        catch (IOException event) 
        {
            event.printStackTrace();
        }
        //       } catch (IOException e) {
        // TODO Auto-generated catch block
        //           e.printStackTrace();
        //       }
        //        this.adminPane = adminPane;


        //Get transactions and display them on main menu screen in transactions section.
        accountCol.setCellValueFactory(new MapValueFactory("account"));
        //accountCol.setMinWidth(130);
        customerCol.setCellValueFactory(new MapValueFactory("customer"));
        //customerCol.setMinWidth(130);
        typeCol.setCellValueFactory(new MapValueFactory("type"));
        amountCol.setCellValueFactory(new MapValueFactory("amount"));


        ObservableList<Map> allData = FXCollections.observableArrayList();

        if (ioTransactions.getTransactions().size() != 0) 
        {
            for (int i=0; i < ioTransactions.getTransactions().size(); i++)
            {
                if (ioTransactions.getTransactions().get(i) != null) 
                {
                    Map<String, String> dataRow = new HashMap<>();
                    Transaction temp = ioTransactions.getTransactions().get(i);
                    
                    dataRow.put("account", temp.getRecipientAcct());
                    dataRow.put("customer", temp.getCustomer());
                    dataRow.put("type", temp.getType());
                    dataRow.put("amount", "$" + new DecimalFormat("0.00").format((temp.getAmount())));
                    
                    allData.add(dataRow);

                }
            }
            transactionText.setItems(allData);
        }
        //      transactionText.setText(output);
    }

    public IOTransactions getTransactionDB()
    {
        return ioTransactions;
    }

    public IOAccounts getAccountDB()
    {
        return ioAccounts;
    }
}
