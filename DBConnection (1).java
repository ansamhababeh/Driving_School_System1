import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DBConnection {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/drivingschoolP";
    private static final String USER = "root";
    private static final String PASSWORD = "entesar1234";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return conn;
    }
}
