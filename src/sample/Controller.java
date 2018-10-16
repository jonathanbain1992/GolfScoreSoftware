package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalTime;

import static sample.DatabaseService.DB_URI;

// Event handlers and members go here
public class Controller {

    private Stage loginStage;
    public boolean playerJustInserted = false;
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

    private DatabaseService db = null;

    public void handleSavePlayerButton()
    {
        DatabaseService db = null;

        System.out.println("jk");
        try {
            db = new DatabaseService();




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
            db.insertPlayer(player);
            //newPlayerInserted = true;
            System.out.println("Inserted a player");

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


    private void clearAllPlayerFields()
    {
        forenameField.clear();
        surnameField.clear();
        activeField.setSelected(false);
        ageField.clear();
        postcodeField.clear();
        addressField0.clear();
        addressField1.clear();
        addressField2.clear();
        handicapField.clear();
    }


    private void setPlayerFieldsByResults(ResultSet results) throws SQLException
    {
        while (results.next()) {
            forenameField.setText(results.getString("firstName"));
            surnameField.setText(results.getString("secondName"));
            ageField.setText(results.getString("age"));
            addressField0.setText(results.getString("addressLine1"));
            addressField1.setText(results.getString("addressLine2"));
            addressField2.setText(results.getString("addressLine3"));
            //postcodeField.setText(results.getString("postcode")); TODO modify db schema to include this attribute
            handicapField.setText(results.getString("handicap"));
            activeField.setSelected(results.getBoolean("isActive"));
        }
    }


    public void handlePlayerSelect()
    {
        String selection = playerSelect.valueProperty().get();
        if (selection.equals("(Insert new player)")){
            // We want to create a player from scratch so empty all of the fields.
            clearAllPlayerFields();
        } else {
            // Get the first name and last name from the combobox's values: in form of [surname],[forename]
            String last = selection.split(",")[0].trim();
            String first = selection.split(",")[1].replace(",", "").trim();

            System.out.println(String.format("first: %s; last: %s", first, last));

            // TODO Come up with more elegant solution to playerDetails being null. Right now, we just ignore it. :(
            try {
                clearAllPlayerFields();
                setPlayerFieldsByResults(db.queryPlayers(first, last));
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Something broke");
            }
        }
    }


    public void handleSaveMatchButton()
    {
        //DatabaseService db = null;
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
        if (playerJustInserted) {
            updatePlayerNameChoices();
            playerJustInserted = false;
        }
    }


    private void updatePlayerNameChoices()
    {
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

            playerJustInserted = false;

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


    public void initialize() {
        try {
            this.db = new DatabaseService();

            ObservableList<String> playerNames = FXCollections.observableArrayList(
                    db.getPlayerNames()
            );

            playerNames.add("(Insert new player)");

            playerSelect.setItems(playerNames);
            playerSelect.getSelectionModel().selectLast();



        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
