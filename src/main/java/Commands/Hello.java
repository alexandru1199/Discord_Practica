package commands;

import bot_code.Info;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Hello extends ListenerAdapter implements ICommand {
    String name="Comanda_SALUT";
    @Override
    public String getName(){
        return this.name;
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        if(messageSent[0].equalsIgnoreCase( Info.PREFIX+"!hi")) {
            event.getChannel().sendMessage("Salut coaie-miu").queue();
        }
    }

    public void onReady(ReadyEvent event) {
        System.out.println("The BOT started");
    }

}
