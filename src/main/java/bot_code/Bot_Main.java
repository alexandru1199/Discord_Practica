package bot_code;


import commands.Commands_List;
import database.Database;

import events.Events;
import net.dv8tion.jda.api.JDABuilder;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.Statement;
import java.util.List;

public class Bot_Main {
    public static void main(String[] args) throws java.sql.SQLException {
        Statement statement = Database.initialize_database();
        List<ListenerAdapter> commands = Commands_List.ListaComenzi();
        try {

            JDABuilder builder = Building.Build(args[0]);
            for (ListenerAdapter i : commands)
                builder.addEventListeners(i);

            builder.addEventListeners(new Events());
            builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
