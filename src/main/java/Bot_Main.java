import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Bot_Main {

    public static void main(String[] args)
            throws LoginException {
        JDABuilder builder = Building.Build(args[0]);
        builder.addEventListeners(new Events());
        builder.build();
    }
}
