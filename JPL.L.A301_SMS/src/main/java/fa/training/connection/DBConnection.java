package fa.training.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public DBConnection() {

    }

    private static Connection connection = null;
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SMS;encrypt=false";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                openConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void openConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection().getCatalog());
    }

}
