package lesson02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyBdApp {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        new ConsoleCommands(repository);
    }
}
