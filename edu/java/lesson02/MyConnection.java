package lesson02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MyConnection {
    static Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:dbjava");
        } catch (SQLException e) {
            throw new RuntimeException("Драйвер не зарегистрирован");
        }
        return conn;
    }
}
