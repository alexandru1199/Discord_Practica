import net.dv8tion.jda.api.JDABuilder;

import java.sql.ResultSet;
import java.sql.Statement;

public class Bot_Main {
    public static void main(String[] args) throws  java.sql.SQLException
            {
                Statement statement=Database.initialize_database();
                statement.executeUpdate("DELETE FROM `discord`.`discord_users` WHERE NAME='Viorel'");
                statement.executeUpdate("DELETE FROM `discord`.`discord_users` WHERE NAME='Giorel'");
                statement.executeUpdate("INSERT INTO `discord`.`discord_users`VALUES('2','Viorel','lis',2000);");
                statement.executeUpdate("INSERT INTO `discord`.`discord_users`VALUES('3','Giorel','lis',2000);");
                ResultSet resultSet = statement.executeQuery("select * from discord_users");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("Name"));
                }
        try {

            JDABuilder builder = Building.Build(args[0]);
            builder.addEventListeners(new Events());
            builder.build();
        }
        catch (Exception e){
        e.printStackTrace();
        }

    }
}
