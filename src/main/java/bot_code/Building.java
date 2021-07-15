package bot_code;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


import net.dv8tion.jda.api.utils.cache.CacheFlag;



public class Building {
    public static JDABuilder Build(String token) {
        JDABuilder builder = JDABuilder.createDefault(token);
        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);


        // Set activity (like "playing Something")
        builder.setActivity(Activity.watching("WORKING FOR COLLEGUE"));
        MemoryUsage.configureMemoryUsage(builder);

        return builder;
    }
}
