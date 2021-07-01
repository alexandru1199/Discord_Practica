import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bot_Main {
    public static void main(String[] args)
            {
                Database.initialize_database();
        try {

            JDABuilder builder = Building.Build(args[0]);
            builder.addEventListeners(new Events());
            builder.build();
        }
        catch (Exception e){
        e.printStackTrace();
        }

    }
}
