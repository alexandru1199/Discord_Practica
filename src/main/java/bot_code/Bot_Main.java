package bot_code;
import commands.functions.Commands_List;
import database.PDF_Generator;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.List;
public class Bot_Main {
    public static void main(String[] args) throws Exception {


        //gets a list of the all commmands
        List<ListenerAdapter> commands = Commands_List.CommandListRun();
        // used to generate a pdf with all the names
        PDF_Generator.generatePDF();
        try {
            // creates the instance of the bot
            JDABuilder builder = Building.Build(args[0]);
            // adds the commands to the bot
            for (ListenerAdapter i : commands)
                builder.addEventListeners(i);
            // powers the bot on( connects to the discord service)
            builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
