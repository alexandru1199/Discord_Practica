package commands.courses_classes;

import commands.functions.Get_Prefix;
import commands.interfaces.ICommand;
import commands.functions.Word_Finder;
import database.Database;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;

public class Courses extends ListenerAdapter implements ICommand {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        try {
            if (Word_Finder.ContainsWord(messageSent[0], "course"))
                if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "course") && messageSent.length == 3) {
                    Statement statement = Database.initialize_database();

                    ResultSet resultSet = statement.executeQuery("select subject,index_course,name_course,extension  from `discord`.`courses` where " +
                            "subject= '" + messageSent[1].toUpperCase() + "' and index_course=" + messageSent[2]);
                    if (resultSet.next()) {


                        event.getChannel().sendFile(new File("C:\\Discord_Practica\\src\\main\\java\\courses\\"
                                + resultSet.getString("name_course") + resultSet.getString("extension"))).queue();
                    } else {
                        event.getChannel().sendMessage("Not valid course").queue();
                    }

                }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getInfo() {
        return "Command:prefix+Course+subject_name+course_number\n" +
                "Usage:get courses\n" +
                "-----------------------------\n";
    }
}