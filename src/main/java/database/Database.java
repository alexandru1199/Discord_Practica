package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database  {

    public static Statement initialize_database() throws  java.sql.SQLException {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discord", "root", "FHAL4Gen");
            Statement statement = connection.createStatement();

            return  statement;


    }
    public  static String getPrefix(String ID) {

        try {
            Statement statement = initialize_database();
            ResultSet resultSet=statement.executeQuery("select Prefix  from `discord`.`prefix` where Guild_ID="+ID);
            if(resultSet.next())
            {
                return resultSet.getString("prefix");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "!";

    }
}
