package sample;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

// Event handlers and members go here
public class Controller implements Initializable {

    private Stage loginStage;
    public boolean newPlayerInserted = false;
    public Integer age;


    @FXML
    private Label testLabel;

    @FXML
    private Button helloBtn;

    @FXML
    private TextField forenameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField handicapField;
    @FXML
    private Button savePlayerButton;
    @FXML
    private DatePicker matchDateField;
    @FXML
    private ComboBox<String> playerOneWidget;

    @FXML
    private ComboBox<String> playerTwoWidget;

    @FXML
    private Button confirmMatchButton;

    @FXML
    private TextField addressField0;

    @FXML
    private TextField addressField1;

    @FXML
    private TextField addressField2;

    @FXML
    private TextField postcodeField;

    @FXML
    private CheckBox activeField;

    @FXML
    private ChoiceBox<LocalTime> matchTimeField;


    @FXML
    private Button addNewPlayer;


    @FXML
    private ComboBox<String> playerSelect;


    public void openLogin()
    {

    }


    public void handleSavePlayerButton()
    {
        DatabaseService db = null;

        System.out.println("jk");
        try {
            db = new DatabaseService();
            //db.populate();



            if (!ageInputIsInt())
                throw new SQLException();

            Integer age = Integer.parseInt(ageField.getText());

            Integer handicap = Integer.parseInt(handicapField.getText());
            String postcode = postcodeField.getText();
            Integer isActive = activeField.isSelected()? 1 : 0;

            Player player = new Player(
                    forenameField.getText(), surnameField.getText(),
                    addressField0.getText(), addressField1.getText(),
                    addressField2.getText(), postcode,
                    age, handicap, isActive
            );
            System.out.println("after okayer but");

            //System.out.println("Attempting record insertion for "+forenameField.getText()+" "+surnameField.getText());
            //db.insertPlayer(player);
            //newPlayerInserted = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (db != null && db.connection != null)
                    db.connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
    }


    public void handleSaveMatchButton()
    {
        DatabaseService db = null;
        try {
            db = new DatabaseService();
            String playerOneFullName = playerOneWidget.getValue();
            String playerTwoFullName = playerTwoWidget.getValue();
/*
            if (!playerOneFullName.equals(playerTwoFullName)){
                Integer id0 = db.getPersonIdByName(playerOneFullName);
                Integer id1 = db.getPersonIdByName(playerTwoFullName);
                LocalDate matchDate = matchDateField.getValue();

            }
*/


        } catch (SQLException ex) {

        } finally {
            try {
                if (db != null && db.connection != null)
                    db.connection.close();
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }


    public void handleOpenCreateGameTab() {
        // Check first that we've added a new player before operating on the database.
        if (newPlayerInserted) {
            updatePlayerNameChoices();
        }
    }


    private void updatePlayerNameChoices()
    {
        DatabaseService db = null;
        ObservableList<String> a = null;

/*
        try {
            db = new DatabaseService();
            db.populate();

            a = FXCollections.observableArrayList(
                    db.getPlayerNames()
            );

            playerOneWidget.setItems(a);
            playerOneWidget.getSelectionModel().selectFirst();


            playerTwoWidget.setItems(a);
            playerTwoWidget.getSelectionModel().selectFirst();

            newPlayerInserted = false;

            // TODO populate 'add game' tab widgets with values, add to/create event handler for 'confirm match' button
            // TODO add game scoring logic

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (db != null && db.connection != null)
                    db.connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        */
    }


    public boolean ageInputIsInt()
    {
        String input = ageField.getText();
        int age;
        try {
            age = Integer.parseInt(input);
            if (age < 0 || age > 150)
                throw new NumberFormatException();
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Value entered was not a valid integer!");
            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
