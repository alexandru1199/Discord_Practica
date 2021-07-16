package bot_code;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


import net.dv8tion.jda.api.utils.cache.CacheFlag;



public class Building {
    public static JDABuilder Build(String token) {
        // creates an instance of the bot
        JDABuilder builder = JDABuilder.createDefault(token);
        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);


        // Sets activity
        builder.setActivity(Activity.watching("WORKING FOR COLLEGUE"));
        //Uses the Memoryusage function so the java virtual machine doesnt run out of memory
        MemoryUsage.configureMemoryUsage(builder);
        // returns the instance of the bot
        return builder;
    }
}
