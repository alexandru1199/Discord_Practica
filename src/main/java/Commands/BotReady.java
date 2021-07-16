package commands;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotReady extends ListenerAdapter {
    public void onReady(ReadyEvent event) {
        System.out.println("The BOT started");
    }
}
