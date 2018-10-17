package sample;


import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;



public class DatabaseService {

    Connection connection;

    // For use when there's not network firewall blocking our ports!
    public static final String DB_URI = "jdbc:mysql://46.101.88.88:3306/golf?user=admin&password=password&useSSL=false";


    // For use when tunnelling to the database through SSH: ssh -L 3307:localhost:3306 root@46.101.88.88
    // in which case, 46.101.88.88:3306 is mapped to by localhost:3307.
    // Start tunnelling first and keep the tunnel running while working.
    public static final String DB_URI_TUNNEL = "jdbc:mysql://localhost:3307/golf?user=admin&password=d55f7bb36ff78181d7a6598ac2b5c06f0f7f2667b384710a&useSSL=false";


    public DatabaseService() throws SQLException
    {
        connection = null;


        try {
            connection = DriverManager.getConnection(DB_URI);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


    public void insertPlayer(Player p) throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO user_golf VALUES %s",
                    p.toString()
                ));
    }

/*
    public void addMatch(Match m) throws SQLException
    {
    }

*/

    // Tries to use the connection to the database to get a result set of a player with a given forename and surname.
    // If something goes wrong, the result is null.
    public ResultSet queryPlayers(String forename, String surname)
    {
        ResultSet result = null;
        String query = "SELECT * FROM user_golf WHERE firstName='"+ forename +"' AND secondName='"+ surname +"';";
        System.out.println(query);
        try {
            result = connection.createStatement().executeQuery(query);

            // Just check if the set is empty. Move cursor back to start afterwards.
            if (!result.next()){
                throw new SQLException("Result set is empty for a user details query");
            }
            result.previous();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }


    public String[] getPlayerNames() throws SQLException
    {
        Statement statement = connection.createStatement();

        ResultSet names = statement.executeQuery("select firstName, secondName from user_golf");
        ArrayList<String> firstnames = new ArrayList<>();
        ArrayList<String> surnames = new ArrayList<>();


        while (names.next()) {
            firstnames.add(names.getString("firstName"));
            surnames.add(names.getString("secondName"));
        }

        String[] fullNames = new String[firstnames.size()];
        for (int i = 0; i < fullNames.length; i++){
            fullNames[i] = surnames.get(i) +", "+firstnames.get(i);
        }

        return fullNames;
    }
    
}
