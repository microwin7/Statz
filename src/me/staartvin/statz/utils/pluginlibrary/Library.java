package me.staartvin.utils.pluginlibrary;

import me.staartvin.utils.pluginlibrary.hooks.*;
import org.bukkit.Bukkit;

/**
 * This class holds all libraries PluginLibrary has.
 * <p>
 * Date created: 14:12:35 12 aug. 2015
 *
 * @author Staartvin
 */
public enum Library {

    AUTORANK("Autorank", AutorankHook.class, "Staartvin"),
    MCMMO("mcMMO", McMMOHook.class, "t00thpick1"),
    FACTIONS("Factions", FactionsHook.class, "Cayorion", "com.massivecraft.factions.Factions"),
    ONTIME("OnTime", OnTimeHook.class, "Edge209"),
    AFKTERMINATOR("afkTerminator", AFKTerminatorHook.class, "Edge209"),
    ROYALCOMMANDS("RoyalCommands", RoyalCommandsHook.class, "WizardCM"),
    ULTIMATECORE("UltimateCore", UltimateCoreHook.class, "Bammerbom"),
    STATZ("Statz", StatzHook.class, "Staartvin"),
    ACIDISLAND("AcidIsland", AcidIslandHook.class, "tastybento"),
    ADVANCEDACHIEVEMENTS("AdvancedAchievements", AdvancedAchievementsHook.class, "DarkPyves"),
    ASKYBLOCK("ASkyBlock", ASkyBlockHook.class, "tastybento"),
    BATTLELEVELS("BattleLevels", BattleLevelsHook.class, "RobiRami"),
    GRIEFPREVENTION("GriefPrevention", GriefPreventionHook.class, "RoboMWM"),
    JOBS("Jobs", JobsHook.class, "Zrips"),
    RPGME("RPGme", RPGmeHook.class, "Flamedek"),
    USKYBLOCK("uSkyBlock", uSkyBlockHook.class, "R4zorax"),
    VAULT("Vault", VaultHook.class, "Kainzo"),
    WORLDGUARD("WorldGuard", WorldGuardHook.class, "sk89q"),
    ESSENTIALSX("Essentials", "EssentialsX", EssentialsXHook.class, "drtshock"),
    QUESTS("Quests", QuestsHook.class, "PikaMug", "me.blackvein.quests.Quests"),
    STATS("Stats", StatsHook.class, "Lolmewn"),
    QUESTS_ALTERNATIVE("Quests", QuestsAlternative.class, "LMBishop", "com.leonardobishop.quests.Quests"),
    SAVAGE_FACTIONS("Factions", "SavageFactions", SavageFactionsHook.class, "ProSavage", "com.massivecraft.factions" +
            ".SavageFactions"),
    PLAYERPOINTS("PlayerPoints", PlayerPointsHook.class, "Blackixx"),
    NUVOTIFIER("Votifier", "NuVotifier", NuVotifierHook.class, "Ichbinjoe", "com.vexsoftware.votifier" +
            ".NuVotifierBukkit"),
    CMI("CMI", CMIHook.class, "Zrips"),
    UHCSTATS("UhcStats", UHCStatsHook.class, "Mezy"),
    TOWNY_ADVANCED("Towny", TownyAdvancedHook.class, "Shade"),
    MCRPG("McRPG", McRPGHook.class, "Eunoians");

    private final String internalPluginName;
    private final String authorName;
    private final Class<? extends LibraryHook> libraryClass;
    private LibraryHook hook;
    private String humanPluginName;
    private String mainClass;

    Library(String pluginName, String humanPluginName, Class<? extends LibraryHook> libraryClass, String authorName) {
        this.internalPluginName = pluginName;
        this.humanPluginName = humanPluginName;
        this.libraryClass = libraryClass;
        this.authorName = authorName;
    }

    Library(String pluginName, Class<? extends LibraryHook> libraryClass, String authorName) {
        this.internalPluginName = pluginName;
        this.libraryClass = libraryClass;
        this.authorName = authorName;
    }

    Library(String pluginName, Class<? extends LibraryHook> libraryClass, String authorName, String mainClass) {
        this.internalPluginName = pluginName;
        this.libraryClass = libraryClass;
        this.authorName = authorName;
        this.mainClass = mainClass;
    }

    Library(String pluginName, String humanPluginName, Class<? extends LibraryHook> libraryClass, String authorName,
            String mainClass) {
        this.internalPluginName = pluginName;
        this.humanPluginName = humanPluginName;
        this.libraryClass = libraryClass;
        this.authorName = authorName;
        this.mainClass = mainClass;
    }

    /**
     * Get a library programmatically. This method is the same as valueOf(), but is case-insensitive.
     *
     * @param value name of the library
     *
     * @return the Library object.
     *
     * @throws IllegalArgumentException When no library with the given name was found.
     */
    public static Library getEnum(String value) throws IllegalArgumentException {
        for (Library e : Library.values()) {
            if (e.getInternalPluginName().equalsIgnoreCase(value))
                return e;
        }

        throw new IllegalArgumentException("There is no library called '" + value + "'!");
    }

    public String getInternalPluginName() {
        return internalPluginName;
    }

    public LibraryHook getHook() {

        // Check if hook is not initialized yet.
        if (hook == null) {
            try {
                hook = libraryClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return hook;
    }

    public String getAuthor() {
        return authorName;
    }

    public String getHumanPluginName() {
        if (humanPluginName == null) {
            return internalPluginName;
        }

        return humanPluginName;
    }

    /**
     * The main class field as described by the description file in the JAR.
     * It is used to distinguish plugins that have the same name, but are of the different authors.
     * @return string containing path to main class.
     */
    public String getMainClass() {
        return mainClass;
    }

    public boolean hasMainClass() {
        return mainClass != null;
    }


    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    /**
     * Check if this plugin is installed on the server.
     *
     * @return true if the plugin is enabled, false otherwise.
     */
    public boolean isPluginInstalled() {
        return Bukkit.getPluginManager().isPluginEnabled(Library.MCRPG.getInternalPluginName());
    }

}
