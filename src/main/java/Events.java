import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.Locale;

public class Events extends ListenerAdapter {
    static boolean contains(String [] a,String b){
       return  Arrays.asList(a).contains(b);
    }
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageSent=event.getMessage().getContentRaw().toLowerCase().split(" ");
        if( contains(messageSent,"mihai")&&(contains(messageSent,"calin")||contains(messageSent,"călin"))){
            event.getChannel().sendMessage(event.getMember().getEffectiveName() + " ai fost Mihai Calined").queue();
        }
    }
}
