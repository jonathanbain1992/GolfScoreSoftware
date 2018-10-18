package sample;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Used to manage a connection to the database
 */
public class DatabaseService implements AutoCloseable
{
    private Connection connection;  // This class encapsulates the database connection.

    // For use when there's not network firewall blocking our ports!
    private static final String DB_URI =
            "jdbc:mysql://46.101.88.88:3306/golf?user=admin" +
                    "&password=password" +
                    "&useSSL=false";


    /*
       For use when tunnelling to the database through SSH: ssh -L 3307:localhost:3306 root@46.101.88.88
       in which case, 46.101.88.88:3306 is mapped to by localhost:3307.
       Start tunnelling first and keep the tunnel running while working.
    */
    private static final String DB_URI_TUNNEL =
            "jdbc:mysql://localhost:3307/golf?user=admin" +
                    "&password=d55f7bb36ff78181d7a6598ac2b5c06f0f7f2667b384710a" +
                    "&useSSL=false";



    public DatabaseService() throws SQLException
    {
        connection = DriverManager.getConnection(DB_URI);
    }


    @Override
    public void close() throws SQLException
    {
        if (connection != null)
            connection.close();
    }


    public Connection getConnection()
    {
        return connection;
    }

}
