package commands;

import commands.functions.Get_Prefix;
import commands.functions.ICommand;
import commands.functions.Word_Finder;
import database.Database;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;


public class Verify extends ListenerAdapter implements ICommand {
   public  String  getInfo() {
      return
               "Command: !verify\n"+
               "Usage:Used to be verified in the server with your name\n"+
                       "-----------------------------\n";
   }



    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        if (Word_Finder.ContainsWord(messageSent[0], "verify")) {
            if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "verify") && (messageSent.length == 1)) {
                event.getChannel().sendMessage("Aceasta este lista de studenti,folositi !verify numele dumnevoastra daca va aflati printre ei.").queue();
                event.getChannel().sendFile(new File("C:\\Discord_Practica\\src\\main\\java\\database\\Name_List.pdf")).queue();

            }
            if (messageSent.length == 3) {
                try {
                    Statement statement = Database.initialize_database();
                    ResultSet resultSet = statement.executeQuery("select * from lista_nume where Nume='" + messageSent[1] + "'and Prenume='" + messageSent[2] + "'");

                    if (resultSet.next()) {
                        event.getChannel().sendMessage("You are registered!").queue();
                        event.getGuild().addRoleToMember(event.getMember().getId(), event.getGuild().getRoleById("861370086555910145")).queue();
                    } else {
                        event.getChannel().sendMessage("You are not  registered! Provide a valid name ").queue();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void onReady(ReadyEvent event) {
        System.out.println("The BOT started");
    }

}
