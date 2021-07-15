package commands;

import commands.functions.Get_Prefix;
import commands.functions.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Show_Prefix  extends ListenerAdapter implements ICommand {
    @Override
    public String getInfo() {
        return
                "Command: showprefix \n" +
                        "Usage:Used to create a temporary voicechanel\n"+
                        "-----------------------------\n";

    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        if(messageSent[0].equalsIgnoreCase("showprefix")){
            event.getChannel().sendMessage(Get_Prefix.get_prefix(event)).queue();
        }
    }
}