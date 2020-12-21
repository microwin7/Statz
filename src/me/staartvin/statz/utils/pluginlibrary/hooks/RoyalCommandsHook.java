package me.staartvin.utils.pluginlibrary.hooks;

import me.staartvin.utils.pluginlibrary.Library;
import me.staartvin.utils.pluginlibrary.hooks.afkmanager.AFKManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.royaldev.royalcommands.RoyalCommands;
import org.royaldev.royalcommands.WorldManager;

import java.util.UUID;

/**
 * RoyalCommands library,
 * <a href="http://dev.bukkit.org/bukkit-plugins/royalcommands/">link</a>.
 * <p>
 * Date created: 16:46:20 14 aug. 2015
 * 
 * @author Staartvin
 * 
 */
public class RoyalCommandsHook extends LibraryHook implements AFKManager {

	private RoyalCommands api;

    @Override
    public boolean isHooked() {
        return api != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.utils.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        if (!isPluginAvailable(Library.ROYALCOMMANDS))
			return false;

        api = (RoyalCommands) this.getServer().getPluginManager()
				.getPlugin(Library.ROYALCOMMANDS.getInternalPluginName());

		return api != null;
	}

	public void doSOmething() {

	}

	/**
	 * Whether or not the given player is afk according to RoyalCommands.
	 * 
	 * @param player
	 *            Player to check.
	 * @return true if the player is afk; false otherwise.
	 */
	public boolean isAFK(Player player) {
        if (!this.isHooked()) return false;

	    return api.getAPI().getPlayerAPI().isAfk(player);
	}

	/**
	 * Gets the display name of the given player.
	 * 
	 * @param player
	 *            Player to get the display name of.
	 * @return The display name of the player; when no display name was found,
	 *         the default name of the player.
	 */
	public String getDisplayName(Player player) {
        if (!this.isHooked()) return null;
		return api.getAPI().getPlayerAPI().getDisplayName(player);
	}

	/**
	 * Gets the offline inventory of a player on a given world.
	 * 
	 * @param player
	 *            Offline player to get the inventory of.
	 * @param worldName
	 *            Name of the world to get the inventory on.
	 * @return A {@link Inventory} representing the inventory of the offline
	 *         player on that world; null if any errors occured.
	 */
	public Inventory getOfflineInventory(OfflinePlayer player, String worldName) {
        if (!this.isHooked()) return null;
		return WorldManager.il.getOfflinePlayerInventory(player, worldName);
	}

	/**
	 * Gets the offline inventory (enderchest) of a player on a given world.
	 * 
	 * @param player
	 *            Offline player to get the inventory of.
	 * @param worldName
	 *            Name of the world to get the inventory on.
	 * @return A {@link Inventory} representing the enderchest inventory of the
	 *         offline player on that world; null if any errors occured.
	 */
	public Inventory getOfflineEnderInventory(OfflinePlayer player, String worldName) {
        if (!this.isHooked()) return null;
		return WorldManager.il.getOfflinePlayerEnderInventory(player, worldName);
	}

	@Override
	public boolean isAFK(UUID uuid) {
		return isAFK(Bukkit.getPlayer(uuid));
	}

	@Override
	public boolean hasAFKData() {
		return true;
	}
}
