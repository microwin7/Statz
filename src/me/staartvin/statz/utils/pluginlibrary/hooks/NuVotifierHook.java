package me.staartvin.utils.pluginlibrary.hooks;

import com.vexsoftware.votifier.NuVotifierBukkit;
import me.staartvin.utils.pluginlibrary.Library;
import me.staartvin.utils.pluginlibrary.listeners.VoteListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * NuVotifier library, <a href="https://www.spigotmc.org/resources/nuvotifier.13449/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class NuVotifierHook extends LibraryHook {

    private NuVotifierBukkit api;


    @Override
    public boolean isHooked() {
        return api != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {

        if (!isPluginAvailable(Library.NUVOTIFIER)) {
            return false;
        }

        final Plugin plugin = this.getServer().getPluginManager()
                .getPlugin(Library.NUVOTIFIER.getInternalPluginName());

        try {
            // May not be loaded
            if (plugin == null || !(plugin instanceof NuVotifierBukkit)) {
                return false;
            }
        } catch (NoClassDefFoundError e) {
            // Votifier was not found, maybe try NuVotifier
            return false;
        }

        api = (NuVotifierBukkit) plugin;

        // Set up listener for listening to players voting.
        setupVoteListener();

        return true;
    }

    private boolean setupVoteListener() {
        Bukkit.getPluginManager().registerEvents(new VoteListener(), this.getProvidedJavaPlugin());

        return true;
    }


}
