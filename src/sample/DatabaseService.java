package sample;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseService {
    public Connection connection = null;
    private Statement statement;

    public DatabaseService() throws SQLException
    {
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        statement = connection.createStatement();
        statement.setQueryTimeout(30);
    }


    private void populate() throws SQLException
    {
        initPlayerTable();
        initMatchesTable();
    }



    public int initPlayerTable() throws SQLException
    {
        return statement.executeUpdate(
                "create table if not exists person (id integer, firstName varchar, secondName VARCHAR , address varchar, age int, handicap int, isActive int)"
        );
    }

    public int initMatchesTable() throws SQLException
    {
        return statement.executeUpdate(
                "create table if not exists matches (" +
                        "id integer primary key, " +
                        "playerOne varchar not null, " +
                        "playerTwo varchar not null, " +
                        "location varchar not null, " +
                        "matchDate date not null, " +
                        "matchTime time not null," +
                        "   foreign key (playerOne) references person(id)," +
                        "   foreign key (playerTwo) references person(id))"
        );
    }


    public void insertPlayer(Player p) throws SQLException
    {
        statement.executeUpdate("insert into person values" + p);  // Auto-commit enabled
    }


    public void addMatch(Match m) throws SQLException
    {
    }


    public ResultSet queryPlayers(String... attributes) throws SQLException
    {
        return statement.executeQuery(
                "select "+ Arrays.toString(attributes).replaceAll("\\[|\\]", "") + " from person"
        );
    }



    public String[] getPlayerNames() throws SQLException
    {

        ResultSet names = statement.executeQuery("select firstName, secondName from person");
        ArrayList<String> firstnames = new ArrayList<>();
        ArrayList<String> surnames = new ArrayList<>();


        while (names.next()) {
            firstnames.add(names.getString("firstName"));
            surnames.add(names.getString("secondName"));
        }

        String[] fullNames = new String[firstnames.size()];
        for (int i = 0; i < fullNames.length; i++){
            fullNames[i] = firstnames.get(i) +" "+surnames.get(i);
        }

        return fullNames;
    }


    public int getPersonIdByName(String fullName) throws SQLException
    {
        ResultSet personId = statement.executeQuery(
                String.format("select id from person where firstName='%s' and secondName='%s'",
                        fullName.split(" ")[0], fullName.split(" ")[1]) // TODO Handle double-barrel surnames (will never match as is)
        );
        return personId.getInt(1);
    }
}
