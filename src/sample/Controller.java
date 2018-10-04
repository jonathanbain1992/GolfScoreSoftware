package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

// Event handlers and members go here
public class Controller {
    public TextField forenameField;
    @FXML
    public TextField surnameField;
    @FXML
    public TextField ageField;
    @FXML
    public TextField handicapField;
    @FXML
    public Button savePlayerButton;
    @FXML
    public DatePicker matchTimeField;
    @FXML
    private ComboBox<String> playerOneWidget;

    @FXML
    private ComboBox<String> playerTwoWidget;

    @FXML
    public Button confirmMatchButton;



    public void initialize() {
        ObservableList<String> a = FXCollections.observableArrayList("NKN", "JKJK", "OUIOJ");
        playerOneWidget.setItems(a);
        playerOneWidget.getSelectionModel().selectFirst();


        playerTwoWidget.setItems(a);
        playerTwoWidget.getSelectionModel().selectFirst();
    }
}
