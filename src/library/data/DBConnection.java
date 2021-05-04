package library.data;

import java.sql.*;

public class DBConnection {
    private static final  String HOST = "127.0.0.1";
    private static final  int PORT = 3306;
    private static final String DB_NAME = "library_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection()  {
        try {
            connection = DriverManager
                    .getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
