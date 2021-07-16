package commands.verify_classes;

import commands.functions.Get_Prefix;
import commands.interfaces.ICommand;
import commands.functions.Word_Finder;
import database.Database;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;

public class Verify extends ListenerAdapter implements ICommand {
    public String getInfo() {
        return
                "Command: prefix+verify\n" +
                        "Usage:Returns a list with the students\n" +
                        "Command 2 : prefix+verify+student_name+student_surname\n " +
                        "Usage 2 : verifies the student\n" +
                        "Example of usage: prefix+Sirbu +Alexandru-Vladut\n" +

                        "-----------------------------\n";
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");
        if (event.getMember().getUser().isBot()) {
            return;
        }
        if (Word_Finder.ContainsWord(messageSent[0], "verify")) {
            // in the case the command is only verify
            if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "verify") && (messageSent.length == 1)) {
                event.getChannel().sendMessage("Aceasta este lista de studenti,folositi !verify numele dumnevoastra " +
                        "daca va aflati printre ei.").queue();
                //gets the file that we created with PDF_Generator and uploads it to the text channel
                event.getChannel().sendFile(new File("C:\\Discord_Practica\\src\\main\\java\\database\\Name_List.pdf")).queue();
            }
            //in the case the command is verify name surname
            if (messageSent.length == 3 && Word_Finder.ContainsWord(messageSent[0], "verify")) {
                try {
                    Statement statement = Database.initialize_database();
                    //checks if the data that the name and surname that the user provided is in the database
                    ResultSet resultSet = statement.executeQuery("select * from namelist where Name='" + messageSent[1] + "'and " +
                            "Surname='" + messageSent[2] + "'");

                    if (resultSet.next()) {
                        event.getChannel().sendMessage("You are registered!").queue();

                        //checks if you used addroleverify before to add a role
                        resultSet = statement.executeQuery("select * from roles where Guild_id='" + event.getGuild().getId() + "' ");

                        //gives multiple roles if they are tied to verify
                        while (resultSet.next()) {
                            //gives the role to the user that used verify corectly

                            event.getGuild().addRoleToMember(event.getMember().getId(), event.getGuild().getRoleById(resultSet.getString
                                    ("idroles")))
                                    .queue();
                            return;
                        }
                        if (!resultSet.next()) {
                            // if there is no role provided it will notify you to get one
                            event.getChannel().sendMessage("You dont have a role to get verified yet!").queue();

                        }
                    } else {
                        event.getChannel().sendMessage("You are not  registered! Provide a valid name ").queue();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
