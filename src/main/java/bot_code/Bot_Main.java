package bot_code;


import commands.functions.Commands_List;
import database.Database;

import database.PDF_Generator;

import net.dv8tion.jda.api.JDABuilder;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.Statement;
import java.util.List;



public class Bot_Main {
    public static void main(String[] args) throws Exception{


        Statement statement = Database.initialize_database();
        List<ListenerAdapter> commands = Commands_List.ListaComenzi();
        PDF_Generator.generatePDF();
        try {

            JDABuilder builder = Building.Build(args[0]);
            for (ListenerAdapter i : commands)
                builder.addEventListeners(i);


            builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
