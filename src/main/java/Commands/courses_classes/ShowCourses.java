package commands.courses_classes;

import commands.functions.Get_Prefix;
import commands.functions.Word_Finder;
import commands.interfaces.ICommand;
import database.Database;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.ResultSet;
import java.sql.Statement;

public class ShowCourses extends ListenerAdapter implements  ICommand {


    @Override
    public String getInfo() {
        return "Command: prefix+showcourses\n" +
                "Usage:shows the available courses\n" +
                "-----------------------------\n";
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        try {
            if (Word_Finder.ContainsWord(messageSent[0], "showcourses"))
                if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "showcourses")) {
                    Statement statement = Database.initialize_database();
                    System.out.println("da");
                    ResultSet resultSet = statement.executeQuery("select * from `discord`.`courses`");
                    while(resultSet.next()){
                        event.getChannel().sendMessage("Course number "+  resultSet.getString("index_course")+" course name: "
                                +resultSet.getString("name_course")+" subject: "+resultSet.getString("subject")).queue();
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}