package commands;

import bot_code.Info;
import commands.functions.Get_Prefix;
import commands.functions.Word_Finder;
import database.Database;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import java.sql.ResultSet;
import java.sql.Statement;

public class Change_Prefix  extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (event.getMember().getUser().isBot()) {
            return;
        }
        try {
            int lastID=1;
            boolean insertable=false;
            Statement statement = Database.initialize_database();
            ResultSet resultSet=statement.executeQuery("select Guild_ID from `discord`.`prefix` where GUILD_ID="+event.getGuild().getId());
            if(!resultSet.next()) {

            resultSet=statement.executeQuery("select MAX(idPrefix) as maxID from `discord`.`prefix`");
            if(resultSet.next()){
                lastID=resultSet.getInt("maxID")+1;
                insertable=true;
            } }
            if (Word_Finder.ContainsWord(messageSent[0], "changeprefix")) {
            if(insertable) {

            if (messageSent[0] .equalsIgnoreCase(Get_Prefix.get_prefix(event)+"changeprefix" )&&messageSent.length==2) {

               statement.executeUpdate( "insert into prefix values("+lastID+",'"+event.getGuild().getId()+"','"+messageSent[1]+"')");
                event.getChannel().sendMessage("Prefix changed"+messageSent[1]).queue();

            }
            }

            else if(messageSent[0] .equalsIgnoreCase(Get_Prefix.get_prefix(event)+ "changeprefix" )&&messageSent.length==2 &&!insertable){

               statement.executeUpdate("update `discord`.`prefix` set prefix= '"+messageSent[1]+ "' where GUILD_ID='"+event.getGuild().getId()+"'");
                event.getChannel().sendMessage("Prefix changed to "+messageSent[1]).queue();
            } }
            else if(messageSent[0] .equalsIgnoreCase("resetprefix")) {
                statement.executeUpdate("update `discord`.`prefix` set prefix= '"+"!"+ "' where GUILD_ID='"+event.getGuild().getId()+"'");
                event.getChannel().sendMessage("Prefix reseted to !").queue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
