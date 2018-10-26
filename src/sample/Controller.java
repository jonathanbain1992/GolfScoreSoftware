package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.sqlite.core.DB;

import java.sql.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


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


    // Contains() of Set will only return true if the Player argument's hashCode equals() one of the hashCodes in the
    // set: which will depend on all of the values of the player record being the same.
    private HashSet<Integer> previousPlayerSubmissions;

    public void handleSavePlayerButton()
    {
        Player player = new Player(
                forenameField.getText(), surnameField.getText(),
                addressField0.getText(), addressField1.getText(),
                addressField2.getText(), postcodeField.getText(),
                Integer.parseInt(ageField.getText()), Integer.parseInt(handicapField.getText()),
                activeField.isSelected()? 1 : 0
        );

        // Only go ahead and try to update if the entry form for the player does not equal any in the list
        // (i.e., there has been a change to a player)
        if (!previousPlayerSubmissions.contains(player.hashCode())) {
            try {

                String currentPlayerSelection = playerSelect.valueProperty().get();

                if (currentPlayerSelection.equals("(Insert new player)")) {
                    DBTransaction.insertPlayer(player);
                } else {

                    // Get the first name and last name from the combobox's values: in form of [surname],[forename]
                    String last = currentPlayerSelection.split(",")[0].trim();
                    String first = currentPlayerSelection.split(",")[1].replace(",", "").trim();
                    savePlayerButton.setDisable(true);
                    DBTransaction.updatePlayer(first, last, player);
                    savePlayerButton.setDisable(false);
                }


                System.out.println("Trying to insert player "+player.toString());

                // Show the changed list of players to choose from in the UI's dropdown.
                updatePlayerNameSelection();
                playerSelect.valueProperty().setValue(player.getLastName()+", "+player.getFirstName());

                // Add submitted data to ledger.
                /* TODO | WARNING! AS THE INSTANCE OF THE SOFTWARE GOES THROUGH AN INCREASING NUMBER OF UPDATES THE
                   HASHSET OF PLAYERS WILL INCREASE IN SIZE */
                previousPlayerSubmissions.add(player.hashCode());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
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


    private void setPlayerFieldsByResults(Player p)
    {
        forenameField.setText(p.getFirstName());
        surnameField.setText(p.getLastName());
        ageField.setText(String.valueOf(p.getAge()));
        activeField.setSelected(p.getIsActive() == 1);
        addressField0.setText(p.getAddress().get(0));
        addressField1.setText(p.getAddress().get(1));
        addressField2.setText(p.getAddress().get(2));
        //postcodeField.setText(results.getString("postcode")); TODO modify db schema to include this attribute
        handicapField.setText(String.valueOf(p.getHandicap()));
    }


    public void handlePlayerSelect()
    {
        String selection = playerSelect.valueProperty().get();
        if (selection == null)
            selection = "(Insert new player)";
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
                setPlayerFieldsByResults(DBTransaction.getPlayerFromQuery(first,last));

                // As we've selected this player, we're likely interested in updating their record: store the initial
                // field data in the previousPlayerSubmissions ledger for future reference.
                previousPlayerSubmissions.add(
                        new Player(
                                forenameField.getText(), surnameField.getText(), addressField0.getText(),
                                addressField1.getText(), addressField2.getText(), ageField.getText(),
                                handicapField.getText(), activeField.isSelected()? "1":"0"
                        ).hashCode()
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Setting player fields failed!");
            }
        }
    }



    // TODO: implement handleSaveMatchButton event handler.

    public void handleSaveMatchButton()
    {
/*        try {
           db = new DatabaseService();
            String playerOneFullName = playerOneWidget.getValue();
            String playerTwoFullName = playerTwoWidget.getValue();

            if (!playerOneFullName.equals(playerTwoFullName)){
                Integer id0 = db.getPersonIdByName(playerOneFullName);
                Integer id1 = db.getPersonIdByName(playerTwoFullName);
                LocalDate matchDate = matchDateField.getValue();

            }
/


        } catch (SQLException ex) {

        } finally {
            try {
                if (db != null && db.connection != null)
                    db.connection.close();
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
*/            }


    public void handleOpenCreateGameTab() {
        if (playerJustInserted) {
            updateMatchPlayerChoices();
            playerJustInserted = false;
        }
    }


    private void updatePlayerNameSelection()
    {
        System.out.println("Updating player names from database");
        ObservableList<String> playerNames = FXCollections.observableArrayList(
                DBTransaction.getPlayerNames()
        );

        playerNames.add("(Insert new player)");

        playerSelect.setItems(playerNames);
        playerSelect.getSelectionModel().selectLast();
        System.out.println("Player names updated");

    }

    private void updateMatchPlayerChoices()
    {


        try {

            ObservableList<String> a = FXCollections.observableArrayList(
                    DBTransaction.getPlayerNames()
            );

            playerOneWidget.setItems(a);
            playerOneWidget.getSelectionModel().selectFirst();


            playerTwoWidget.setItems(a);
            playerTwoWidget.getSelectionModel().selectFirst();

            playerJustInserted = false;

            // TODO populate 'add game' tab widgets with values, add to/create event handler for 'confirm match' button
            // TODO add game scoring logic

        } catch (Exception ex) {
            ex.printStackTrace();
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


    public void initialize()
    {
        try {
            previousPlayerSubmissions = new HashSet<Integer>();
            updatePlayerNameSelection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
