package commands.verify;

import commands.functions.Get_Prefix;
import commands.functions.ICommand;
import commands.functions.Word_Finder;
import database.Database;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class AddRoleVerify extends ListenerAdapter implements ICommand {
    @Override
    public String getInfo() {
        return "Command: prefix+addroleverify+roleid\n"+
                "Usage: when verified the bot gives the user the list of role(s)\n" +
                "-----------------------------\n";
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        try {

            if (Word_Finder.ContainsWord(messageSent[0], "addroleverify")) {

                Statement statement = Database.initialize_database();
                ResultSet resultSet = statement.executeQuery("select idroles from `discord`.`roles` where idroles=" + messageSent[1]);
                if (resultSet.next()) {
                    event.getChannel().sendMessage("you can't use the same role twice").queue();
                   return;
                }


                    if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "addroleverify") && messageSent.length == 2) {
                        event.getChannel().sendMessage("Role added "+event.getGuild().getRoleById(messageSent[1]).getAsMention()).queue();
                        statement.executeUpdate("insert into `discord`.`roles` (`roles`.`idroles`,`roles`.`Guild_Id`) values('"+messageSent[1]+"','"+event.getGuild().getId()+"' )");


                    }
                }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
} }
