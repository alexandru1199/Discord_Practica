package commands.functions;

import database.Database;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.sql.ResultSet;
import java.sql.Statement;

public class Get_Prefix {
     public static String get_prefix(GuildMessageReceivedEvent event) {
        try {
            Statement statement = Database.initialize_database();
            //each server has an id and a prefix, later a command we will change it but if the prefix isnt changed it will be the default :"!"
            // we use a querry to get the prefix if its changed
            ResultSet resultSet = statement.executeQuery("select Prefix from `discord`.`prefix` where GUILD_ID=" + event.getGuild().getId());
            if(resultSet.next()){
                return resultSet.getString("Prefix");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    return "!";}
}
