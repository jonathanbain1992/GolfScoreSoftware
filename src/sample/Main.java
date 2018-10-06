package sample;

import com.sun.xml.internal.fastinfoset.util.DuplicateAttributeVerifier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application
{

    private static final String UI = "ui.fxml";
    private Stage primaryStage;
    private AnchorPane anchorLayout;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource(UI));
       // Scene scene = new Scene(root, 300, 250);


        FXMLLoader loader = new FXMLLoader();
        loader.setController(new Controller());


        this.primaryStage = primaryStage;
        Scene scene = new Scene(root, 600, 500);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Birdie");
        scene.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());

        primaryStage.setResizable(false);




        root.setId("pane");
        this.primaryStage.show();

    }


    public static void main(String[] args)
            //TODO: find out how golf works lol
            //TODO: create method wired to gui that creates players, matches, retreives players and match list
    {

        launch(args);
    }
}
