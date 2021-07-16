package commands.verify_classes;

import commands.functions.Get_Prefix;
import commands.interfaces.ICommand;
import commands.functions.Word_Finder;
import database.Database;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.ResultSet;
import java.sql.Statement;

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
                //check if the role is alerady inserted in the database
                ResultSet resultSet = statement.executeQuery("select idroles from `discord`.`roles` where idroles=" + messageSent[1]);
                if (resultSet.next()) {
                    event.getChannel().sendMessage("you can't use the same role twice").queue();
                   return;
                }
                     // we limit the user on using the command only with 2 words
                    if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "addroleverify") && messageSent.length == 2) {
                        //notifies the user that the role was added
                        event.getChannel().sendMessage("Role added "+event.getGuild().getRoleById(messageSent[1]).getAsMention()).queue();
                        // inserts the role in the database
                        statement.executeUpdate("insert into `discord`.`roles` (`roles`.`idroles`,`roles`.`Guild_Id`) values('"
                                +messageSent[1]+"','"+event.getGuild().getId()+"' )");
                    }
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
} }
