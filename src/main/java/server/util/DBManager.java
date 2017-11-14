package server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for establishing, delegating and closing the connection to the database.
 *
 * This class relies on enviroment variables to connect to the desired database, please set these up
 * before using the DBManager.
 */
public class DBManager {

    // Holds the connection to the database
    private static Connection connection;


    // Establishes the conncetion to the database

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"
                            + Config.getDatabaseHost() + ":"
                            + Config.getDatabasePort() + "/"
                            + Config.getDatabaseName() + "?useSSL=false&serverTimezone=GMT",
                    Config.getDatabaseUser(),
                    Config.getDatabasePassword());
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Returns a Connection object, that can be used to make queries to the database
     */
    public static Connection getConnection() {

        return connection;
    }

    /**
     * Closes the connection to the database
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
