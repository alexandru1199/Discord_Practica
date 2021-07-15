package commands;

import commands.functions.Get_Prefix;
import commands.functions.ICommand;
import commands.functions.Word_Finder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;



public class Generate_VoiceChannel extends ListenerAdapter implements ICommand {
    @Override
    public String getInfo() {
        return
                "Command: prefix+createvc **namevc** \n" +
                        "Usage:Used to create a temporary voicechanel\n"+
                "-----------------------------\n";

    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        if(Word_Finder.ContainsWord(messageSent[0],"createvc"))
        if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "createvc")) {


            event.getGuild().getCategoryById("860997635526754344").createVoiceChannel(messageSent[1]).queue();

        }
    }
}
