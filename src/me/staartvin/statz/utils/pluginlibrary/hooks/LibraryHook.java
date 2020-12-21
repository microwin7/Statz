package me.staartvin.utils.pluginlibrary.hooks;

import me.staartvin.utils.pluginlibrary.Library;
import me.staartvin.utils.pluginlibrary.PluginLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

/**
 * Represents a hook to another plugin
 * <p>
 * Date created: 14:13:45 12 aug. 2015
 *
 * @author Staartvin
 */
public abstract class LibraryHook {

    /**
     * Check if the given library is available. This means that it exists in the plugins folder and is enabled.
     *
     * @param library Library to check
     * @return true if it exists and is started, false otherwise.
     */
    public static boolean isPluginAvailable(Library library) {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(library.getInternalPluginName());

        if (plugin == null) return false;

        // Check if plugin has a main class defined.
        // If so, check if the main class is equal to that of the enabled plugin to make sure we have the correct one.
        // Some plugins have the same name, but are of different authors. Checking the main class path makes sure we
        // have the correct one.
        return !library.hasMainClass() || plugin.getDescription().getMain().equalsIgnoreCase(library.getMainClass());
    }

    protected PluginLibrary getPlugin() {
        return Bukkit.getServer().getServicesManager().load(PluginLibrary.class);
    }

    protected Plugin getProvidedJavaPlugin() {
        return Bukkit.getServer().getServicesManager().getRegistration(PluginLibrary.class).getPlugin();
    }

    protected Server getServer() {
        return Bukkit.getServer();
    }

    /**
     * Check whether PluginLibrary is hooked into this plugin. Note that {@link #isPluginAvailable(Library)} only checks
     * whether the plugin is available, but not whether it is hooked.
     * <p>
     * If the plugin is not hooked, it cannot be used properly and should first be hooked by calling {@link #hook()}.
     *
     * @return true if the hook was successfully made. False otherwise.
     */
    public abstract boolean isHooked();

    /**
     * Hook the plugin to make sure data can be retrieved.
     *
     * @return true if PluginLibrary could successfully hook; false otherwise.
     */
    public abstract boolean hook();
}
