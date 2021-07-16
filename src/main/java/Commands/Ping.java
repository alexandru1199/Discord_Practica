package commands;

import commands.functions.Get_Prefix;
import commands.functions.Word_Finder;
import commands.interfaces.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter implements ICommand {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        // splits the message in an array word by word
        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");
        // if the bot writes  the command nothing happens
        if (event.getMember().getUser().isBot()) {
            return;
        }
        //checks if the command ping has something before it so we dont execute a querry for the prefix if it doesnt
       if(Word_Finder.ContainsWord(messageSent[0], "ping"))
           //uses the getprefix to get the prefix from the database , event having the method getGuild().getId() to get the id of the guild
           //all guilds have a unique id
           if(messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "ping"))
           {
            //gets the channel where the message is sent and sends the message pong
            event.getChannel().sendMessage("pong").queue();
        }
    }
    @Override
    //will be used for help command
    public String getInfo() {
       return  "Command: prefix+ping\n" +
                "Usage:Used for a easy test to see if the bot works(will send a message pong if it does)\n" +
                "-----------------------------\n";
    }
}