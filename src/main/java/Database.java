import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    public static void initialize_database() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/discord", "root", "FHAL4Gen");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from discord_users");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("Name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
