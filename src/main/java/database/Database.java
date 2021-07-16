package database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;

public class Database {

    public static Statement initialize_database() throws java.sql.SQLException {
        // connects to my localhost
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discord", "root", "FHAL4Gen");
        //creating a statement to use querries and updates on our database
        Statement statement = connection.createStatement();

        return statement;


    }

}
