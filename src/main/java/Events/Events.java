package events;

import database.Database;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;


public class Events extends ListenerAdapter {
    static boolean contains(String[] a, String b) {
        return Arrays.asList(a).contains(b);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] messageSent = event.getMessage().getContentRaw().toLowerCase().split(" ");
        if (event.getMember().getUser().isBot()) {
            return;
        }
        if (contains(messageSent, "mihai") && (contains(messageSent, "calin") || contains(messageSent, "călin"))) {
            try {
                int lastID;
                Statement statement = Database.initialize_database();


                ResultSet resultSet= statement.executeQuery("select * from `discord`.`discord_users` " +
                        "where discordID='" + event.getMember().getUser().getId() + "'");
                if (!resultSet.next()) {
                    resultSet=statement.executeQuery("select MAX(idUser) as maxID from `discord`.`discord_users`");
                    if (resultSet.next()) {
                        lastID = resultSet.getInt("maxID");
                        statement.executeUpdate("INSERT INTO `discord`.`discord_users`" +
                                "VALUES(" +
                                "' " + (lastID + 1) + "','" + event.getMember().getUser().getName() +
                                "','" + event.getMember().getUser().getId() + "',2000);");
                    } }
            } catch (Exception e) {
                e.printStackTrace();
            }

            event.getChannel().sendMessage(event.getMember().getUser().getName() + " ai fost Mihai Calined").queue();
        }
    }


}
