package bot_code;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.utils.ChunkingFilter;

import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class MemoryUsage {
    public static void configureMemoryUsage(JDABuilder builder) {
        // Disable cache for member activities (streaming/games/spotify)
        builder.disableCache(CacheFlag.ACTIVITY);



        // Disable member chunking on startup
        builder.setChunkingFilter(ChunkingFilter.NONE);


    }
}
