package commands;

import commands.functions.Get_Prefix;
import commands.functions.Word_Finder;
import database.Database;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.ResultSet;
import java.sql.Statement;

public class Take_Project extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        int lastID=1;
        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        if (Word_Finder.ContainsWord(messageSent[0], "takeproject")) {
            if (messageSent[0].equalsIgnoreCase(Get_Prefix.get_prefix(event) + "takeproject"))
                try {
                    Statement statement = Database.initialize_database();
                   String  description="";
                   for(int i =3;i<messageSent.length;i++){
                       description+=" " +messageSent[i];
                   }
                   ResultSet resultSet=statement.executeQuery("select MAX(idproject) as maxID from `discord`.`projects`");
                    if(resultSet.next()){
                        lastID=resultSet.getInt("maxID")+1; }

                    statement.executeUpdate("insert into projects values ("+lastID+", ' "+event.getMember().getNickname()+"', ' "+
                            messageSent[1]+" ', " + messageSent[2] + ", '"+description+ " ', ' "+event
                    .getMember().getId() +"' ); "

                    );

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
