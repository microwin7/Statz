package me.staartvin.utils.pluginlibrary.hooks;

import com.massivecraft.factions.*;
import me.staartvin.utils.pluginlibrary.Library;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * SavageFactions,
 * <a href="https://www.spigotmc.org/resources/savagefactions-factionsuuid-reimagined-production-server-ready.52891/">link</a>.
 * <p>
 * Date created: 20:22:17 12 aug. 2015
 *
 * @author Staartvin
 */
public class SavageFactionsHook extends LibraryHook {

    private SavageFactions savageFactions;

    @Override
    public boolean isHooked() {
        return savageFactions != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        if (!isPluginAvailable(Library.SAVAGE_FACTIONS))
            return false;

        Plugin plugin = this.getServer().getPluginManager()
                .getPlugin(Library.SAVAGE_FACTIONS.getInternalPluginName());

        if (!(plugin instanceof SavageFactions))
            return false;

        savageFactions = (SavageFactions) plugin;

        return true;
    }

    /* Faction vars */

    /**
     * Gets the faction by its tag, see {@link com.massivecraft.factions.Faction#getTag()}
     * .
     *
     * @param factionName Name of the faction. <b>Without colour codes!</b>
     * @return {@link Faction} or null if no faction found.
     */
    public com.massivecraft.factions.Faction getFactionByName(String factionName) {

        if (!this.isHooked()) return null;

        if (factionName == null)
            return null;

        com.massivecraft.factions.Faction fac = Factions.getInstance().getByTag
                (factionName);

        return fac;
    }

    /**
     * Gets the faction a player is in.
     *
     * @param uuid UUID of the player.
     * @return {@link Faction}, null if the player doesn't exist or is not in
     * a faction.
     */
    public com.massivecraft.factions.Faction getFactionByUUID(UUID uuid) {

        if (!this.isHooked()) return null;

        if (uuid == null)
            return null;

        FPlayer fPlayer = getFactionsPlayer(uuid);

        if (fPlayer == null)
            return null;

        com.massivecraft.factions.Faction fac = fPlayer.getFaction();

        return fac;
    }

    /**
     * Gets a faction by its internal Factions id.
     *
     * @param factionId Id of the faction
     * @return {@link Faction} or null if id is invalid.
     */
    public com.massivecraft.factions.Faction getFactionById(String factionId) {

        if (!this.isHooked()) return null;

        if (factionId == null)
            return null;

        com.massivecraft.factions.Faction faction = Factions.getInstance()
                .getFactionById
                (factionId);

        return faction;
    }

    /**
     * Gets a list of all savageFactions that currently exist.
     *
     * @return a list of all savageFactions.
     */
    public List<com.massivecraft.factions.Faction> getAllFactions() {

        List<com.massivecraft.factions.Faction> factions = new ArrayList<>();

        if (!this.isHooked()) return factions;

        factions.addAll(com.massivecraft.factions.Factions.getInstance().getAllFactions());

        return factions;
    }

    /**
     * Gets the Wilderness 'faction'.
     *
     * @return the Wilderness faction.
     */
    public Faction getWilderness() {

        if (!this.isHooked()) return null;

        Faction fac = Factions.getInstance().getWilderness();

        return fac;
    }

    /**
     * Gets the Safezone 'faction'.
     *
     * @return the Safezone faction.
     */
    public Faction getSafezone() {

        if (!this.isHooked()) return null;

        Faction fac = Factions.getInstance().getSafeZone();

        return fac;
    }

    /**
     * Gets the Warzone 'faction'.
     *
     * @return the Warzone faction.
     */
    public Faction getWarzone() {

        if (!this.isHooked()) return null;

        Faction fac = Factions.getInstance().getWarZone();

        return fac;
    }

    /**
     * Gets the faction at a specific {@link Location}.
     *
     * @param location Location for the faction to be at.
     * @return A {@link Faction} or null if the location does not contain a
     * faction.
     */
    public Faction getFactionAt(Location location) {

        if (!this.isHooked()) return null;

        if (location == null)
            return null;

        FLocation fLocation = new FLocation(location);

        Faction fac = Board.getInstance().getFactionAt(fLocation);

        return fac;
    }

    /**
     * Gets the
     * {@link FPlayer} for a
     * player, which represents the player object Factions internally uses.
     *
     * @param uuid UUID of the player.
     * @return {@link FPlayer}
     * or null if player does not exist/is not stored by Factions.
     */
    public FPlayer getFactionsPlayer(UUID uuid) {

        if (!this.isHooked()) return null;

        OfflinePlayer offlinePlayer = this.getServer().getOfflinePlayer(uuid);

        FPlayer fPlayer = FPlayers.getInstance().getByOfflinePlayer(offlinePlayer);

        return fPlayer;
    }

    /**
     * Get the power of the faction of a player. Will return -1 if player has no faction.
     *
     * @param uuid UUID of the player to check
     * @return power of the faction, or -1 if not found.
     */
    public double getFactionPower(UUID uuid) {

        if (!this.isHooked()) return -1;

        if (uuid == null) return -1;

        FPlayer fPlayer = this.getFactionsPlayer(uuid);

        if (fPlayer == null) return -1;

        if (!fPlayer.hasFaction()) return -1;

        return fPlayer.getFaction().getPower();
    }
}