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
controller

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource(UI));
       // Scene scene = new Scene(root, 300, 250);
        this.primaryStage = primaryStage;
        Scene scene = new Scene(root, 500, 400);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Birdie");
        scene.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());






        root.setId("pane");
        this.primaryStage.show();

    }


    public static void main(String[] args)
            //TODO: find out how golf works lol
            //TODO: create method wired to gui that creates players, matches, retreives players and match list
    {

        launch(args);


        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("create table person (id integer, name string);");
            statement.executeUpdate("insert into person values(1, 'henry')");
            //statement.executeUpdate("create table person (id integer, name string)");
            //statement.executeUpdate("insert into person values(1, 'leo')");
            //statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from person");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
