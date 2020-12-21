package me.staartvin.utils.pluginlibrary;

import me.staartvin.utils.pluginlibrary.hooks.LibraryHook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Main class of PluginLibrary
 * <p>
 * Date created: 14:06:30 12 aug. 2015
 *
 * @author Staartvin
 */
public class PluginLibrary {

    private final static List<me.staartvin.utils.pluginlibrary.Library> loadedLibraries = new ArrayList<>();
    public HashMap<UUID, Long> requestTimes = new HashMap<>();

    /**
     * Gets the library for a specific plugin. <br> Will throw a {@link IllegalArgumentException} when there is no
     * library with the given name.
     *
     * @param pluginName Name of the plugin. Case-insensitive!
     * @return {@link Library} class or an error.
     * @throws IllegalArgumentException When no plugin with the given name was found.
     */
    public static LibraryHook getLibrary(String pluginName) throws IllegalArgumentException {
        return me.staartvin.utils.pluginlibrary.Library.getEnum(pluginName).getHook();
    }

    /**
     * <br>Returns the same as {@link #getLibrary(String)}.
     *
     * @param lib Library enum to get the library hook for.
     * @return {@link Library} class or an error.
     * @see #getLibrary(String)
     */
    public static LibraryHook getLibrary(me.staartvin.utils.pluginlibrary.Library lib) {
        return lib.getHook();
    }

    /**
     * Checks to see whether the library is loaded and thus ready for use.
     *
     * @param lib Library to check.
     * @return true if the library is loaded; false otherwise.
     */
    public static boolean isLibraryLoaded(me.staartvin.utils.pluginlibrary.Library lib) {
        return loadedLibraries.contains(lib);
    }

    public static PluginLibrary getPluginLibrary(JavaPlugin plugin) {

        boolean loadNewInstance = false;

        PluginLibrary library = null;

        // Check whether the library has already been loaded before.
        if (Bukkit.getServer().getServicesManager().isProvidedFor(PluginLibrary.class)) {
            library = Bukkit.getServer().getServicesManager().load(PluginLibrary.class);

            // Check if the library actually exists
            if (library == null) {
                loadNewInstance = true;
            } else {
                System.out.println("Found PluginLibrary instance and using that");
            }
        } else {
            // It hasn't loaded, so we should definitely create a new one.
            loadNewInstance = true;
        }

        // If we should load a new instance, do that.
        if (loadNewInstance) {
            library = new PluginLibrary();

            System.out.println("Generating new PluginLibrary instance");

            // Also register this so we don't have to create one again.
            Bukkit.getServer().getServicesManager().register(PluginLibrary.class, library, plugin,
                    ServicePriority.Normal);
        }

        return library;
    }

    public int enablePluginLibrary() {

        loadedLibraries.clear();

        logMessage(ChatColor.GOLD + "***== Loading libraries ==***");

        int loadedLibraries = loadLibraries();

        logMessage(ChatColor.GOLD + "***== Loaded " + ChatColor.WHITE + loadedLibraries + ChatColor.GOLD
                + " libraries! ==***");

        if (loadedLibraries > 0) {
            logMessage(ChatColor.GOLD + "Loaded libraries: " + getLoadedLibrariesAsString());
        }

        logMessage(ChatColor.GREEN + "*** Ready for plugins to send/retrieve data. ***");

        return loadedLibraries;
    }

    public void disablePluginLibrary() {
        loadedLibraries.clear();
        logMessage("Unloaded all hooked libraries!");
    }

    /**
     * Load all libraries, this will be done automatically by the plugin.
     *
     * @return how many libraries were loaded.
     */
    public int loadLibraries() {
        int count = 0;

        for (me.staartvin.utils.pluginlibrary.Library l : me.staartvin.utils.pluginlibrary.Library.values()) {
            if (LibraryHook.isPluginAvailable(l)) {
                try {
                    LibraryHook libraryHook = l.getHook();
                    if (libraryHook.hook()) {
                        loadedLibraries.add(l);
                        count++;
                    }
                } catch (NoClassDefFoundError error) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Obtained error when " +
                            "loading " +
                            l.getHumanPluginName());
                    error.printStackTrace();
                }
            }
        }

        return count;
    }

    public void logMessage(String message) {
        // This makes sure it can support colours.
        Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "[PluginLibrary] " + message);
    }

    /**
     * Get a list of all loaded libraries. <br> This list is unmodifiable and when you try to alter it, it will give an
     * {@link UnsupportedOperationException}.
     *
     * @return a list of loaded libraries.
     */
    public List<me.staartvin.utils.pluginlibrary.Library> getLoadedLibraries() {
        return Collections.unmodifiableList(loadedLibraries);
    }

    private String getLoadedLibrariesAsString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0, l = loadedLibraries.size(); i < l; i++) {
            me.staartvin.utils.pluginlibrary.Library library = loadedLibraries.get(i);

            String addedString = ChatColor.DARK_AQUA + library.getHumanPluginName() + ChatColor.DARK_GREEN + " (by " +
                    library
                            .getAuthor() + ")" + ChatColor.RESET;

            if (i == 0) {
                builder.append(addedString);
            } else if (i == (l - 1)) {
                builder.append(ChatColor.GRAY).append(" and ").append(addedString);
            } else {
                builder.append(ChatColor.GRAY).append(", ").append(addedString);
            }
        }

        return builder.toString();
    }
}
