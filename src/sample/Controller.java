package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.xml.crypto.Data;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ResourceBundle;

// Event handlers and members go here
public class Controller {
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

    public void initialize() {
        DatabaseService db = null;
        ObservableList<String> a = null;


        try {
            db = new DatabaseService();

            a = FXCollections.observableArrayList(
                    db.getPlayerNames()
            );

            playerOneWidget.setItems(a);
            playerOneWidget.getSelectionModel().selectFirst();


            playerTwoWidget.setItems(a);
            playerTwoWidget.getSelectionModel().selectFirst();


            // TODO populate 'add game' tab widgets with values, add to/create event handler for 'confirm match' button
            // TODO add game scoring logic

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(-1);
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


    public void handleSavePlayerButton()
    {


        DatabaseService db = null;

        try {
            db = new DatabaseService();
            db.initPlayerTable();

            String[] name = {forenameField.getText(), surnameField.getText()};
            Integer age = Integer.parseInt(ageField.getText());
            Integer handicap = Integer.parseInt(handicapField.getText());
            String[] address = {addressField0.getText(), addressField1.getText(), addressField2.getText()};
            String postcode = postcodeField.getText();
            Integer isActive = activeField.isSelected()? 1 : 0;

            Player player = new Player(
                    name[0], name[1], address[0], address[1],
                    address[2], postcode, age, handicap, isActive
            );

            System.out.println("Attempting record insertion for "+name[0]+" "+name[1]);
            db.insertPlayer(player);


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Something went horribly wrong");
            System.exit(-1);
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


    public void handleSaveMatchButton(){
        DatabaseService db = null;
        try {
            db = new DatabaseService();
            String playerOneFullName = playerOneWidget.getValue();
            String playerTwoFullName = playerTwoWidget.getValue();

            if (!playerOneFullName.equals(playerTwoFullName)){
                Integer id0 = db.getPersonIdByName(playerOneFullName);
                Integer id1 = db.getPersonIdByName(playerTwoFullName);
                LocalDate matchDate = matchDateField.getValue();

            }



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
}
