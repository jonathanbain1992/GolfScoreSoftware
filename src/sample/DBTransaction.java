package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Handle the connection, queries and updates from this class. It's described as a
 * transaction as once it is over, the connection is closed.
 */
public class DBTransaction
{
    public static void insertPlayer(Player p)
    {
        try (DatabaseService service = new DatabaseService()) {
            service.getConnection()
                    .createStatement()
                    .executeUpdate(String.format("INSERT INTO user_golf VALUES %s", p.toString()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static String[] getPlayerNames()
    {
        String[] fullNames = null;

        try (DatabaseService service = new DatabaseService()){
            ResultSet names = service.getConnection()
                    .createStatement()
                    .executeQuery("select firstName, secondName from user_golf");

            ArrayList<String> firstnames = new ArrayList<>();
            ArrayList<String> surnames = new ArrayList<>();


            while (names.next()) {
                firstnames.add(names.getString("firstName"));
                surnames.add(names.getString("secondName"));
            }

            fullNames = new String[firstnames.size()];
            for (int i = 0; i < fullNames.length; i++){
                fullNames[i] = surnames.get(i) +", "+firstnames.get(i);
            }

            return fullNames;

        } catch (SQLException ex) {

        }
        return fullNames;
    }


    public static void updatePlayer(String firstName, String lastName, Player p)
    {
        try (DatabaseService service = new DatabaseService()) {
            String query = "UPDATE user_golf " +
                    "SET "+ p.attributes() +
                    "   WHERE firstName='"+ firstName + "' AND secondName='" + lastName + "';";
            service.getConnection().createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static void addMatch(Match m) throws SQLException
    {
    }


    // Tries to use the connection to the database to get a result set of a player with a given forename and surname.
    // If something goes wrong, the result is null.
    public static ResultSet queryPlayers(String forename, String surname)
    {
        ResultSet result = null;
        String query = "SELECT * FROM user_golf WHERE firstName='"+ forename +"' AND secondName='"+ surname +"';";
        System.out.println(query);

        try (DatabaseService service = new DatabaseService()) {
            result = service.getConnection().createStatement().executeQuery(query);

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


    public static Player getPlayerFromQuery(String forename, String surname)
    {
        Player p = null;
        try (DatabaseService service = new DatabaseService()) {
            String query = "SELECT * FROM user_golf WHERE firstName='"+ forename +"' AND secondName='"+ surname +"';";
            ResultSet result = service.getConnection().createStatement().executeQuery(query);

            while (result.next()) {
                p = new Player(
                        result.getString("firstName"), result.getString("secondName"),
                        result.getString("addressLine1"), result.getString("addressLine2"),
                        result.getString("addressLine3"), "",
                        result.getInt("age"), result.getInt("handicap"), result.getInt("isActive")
                );
            }
            // TODO Fix tables to have postcode as a column.

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return p;
    }

}