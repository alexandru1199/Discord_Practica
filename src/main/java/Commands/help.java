package commands;

import commands.functions.Commands_List;
import commands.functions.Get_Prefix;
import commands.functions.ICommand;
import commands.functions.Word_Finder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;


public class Help extends ListenerAdapter implements ICommand {
    public String getInfo() {
        return
                "Command: !help\n" +
                        "Usage:Used to get commands\n" +
                        "-----------------------------\n";
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        if (Word_Finder.ContainsWord(messageSent[0], "help")) {
            if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event)+ "help")) {

                String help = "";
                List<ICommand> lista = Commands_List.ListaComenzi2();
                for (ICommand i : lista) {
                    help = help + i.getInfo();
                }

                event.getChannel().sendMessage(help).queue();
            }
        }
    }
}