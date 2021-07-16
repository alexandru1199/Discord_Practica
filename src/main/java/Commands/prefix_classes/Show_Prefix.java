package commands.prefix_classes;

import commands.functions.Get_Prefix;
import commands.interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Show_Prefix  extends ListenerAdapter implements ICommand {
    @Override
    //description of the command
    public String getInfo() {
        return
                "Command:showprefix \n" +
                        "Usage:shows the prefix of the server\n"+
                        "-----------------------------\n";

    }

    // event that runs when there is a message sent on the server
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // splits the message in an array word by word
        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");
        // if the bot writes  the command nothing happens
        if (event.getMember().getUser().isBot()) {
            return;
        }
        //checks if the command is showprefix
        if(messageSent[0].equalsIgnoreCase("showprefix")){
            //gets the channel where the message is sent and send a message with the prefix
            event.getChannel().sendMessage(Get_Prefix.get_prefix(event)).queue();
        }
    }
}