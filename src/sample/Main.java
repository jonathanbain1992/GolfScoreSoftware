package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application
{

    private static final String UI = "fxml/ui.fxml";
    private Stage primaryStage;

    public Stage loginStage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
//        Parent loginRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));



        FXMLLoader rootLoader = new FXMLLoader();
        rootLoader.setController(new Controller());


        this.primaryStage = primaryStage;
        Scene scene = new Scene(root, 600, 300);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Birdie");
        this.primaryStage.setResizable(false);
        scene.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());

        primaryStage.setResizable(false);

        root.setId("pane");

        this.primaryStage.show();

    }


    public static void main(String[] args)
            //TODO: find out how golf works lol
            //TODO: create method wired to gui that creates players, matches, retrieves players and match list
    {

        launch(args);
    }
}
