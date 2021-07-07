package commands;

import commands.functions.Get_Prefix;
import commands.functions.Word_Finder;
import database.Database;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.ResultSet;
import java.sql.Statement;

public class Show_Projects extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        if (Word_Finder.ContainsWord(messageSent[0], "showproject")) {
            if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "showproject"))
                try {
                    Statement statement = Database.initialize_database();

                    ResultSet resultSet=statement.executeQuery("select *  from `discord`.`projects` where idproject>1");
                    while(resultSet.next()) {
                        if (resultSet.getString("taken").equalsIgnoreCase("1"))
                            event.getChannel().sendMessage("Project name: " + resultSet.getString("NameProject") + ", taken, description: " +
                                    resultSet.getString("descriptionn")).queue();
else if(resultSet.getString("taken").equalsIgnoreCase("0"))
                            event.getChannel().sendMessage("Project name: " + resultSet.getString("NameProject") + ", not taken, description: " +
                                    resultSet.getString("descriptionn")).queue();




                    }




                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

}
