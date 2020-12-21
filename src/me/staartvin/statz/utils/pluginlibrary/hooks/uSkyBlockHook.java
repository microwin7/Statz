package me.staartvin.utils.pluginlibrary.hooks;

import me.staartvin.utils.pluginlibrary.Library;
import org.bukkit.entity.Player;
import us.talabrek.ultimateskyblock.api.uSkyBlockAPI;

/**
 * uSkyBlock library,
 * <a href="https://www.spigotmc.org/resources/uskyblock.2280/">link</a>.
 * <p>
 * 
 * @author Staartvin
 * 
 */
public class uSkyBlockHook extends LibraryHook {

    private uSkyBlockAPI uSkyBlock;


    @Override
    public boolean isHooked() {
        return uSkyBlock != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {

        if (!isPluginAvailable(Library.USKYBLOCK))
            return false;

        uSkyBlock = (uSkyBlockAPI) this.getServer().getPluginManager()
                .getPlugin(Library.USKYBLOCK.getInternalPluginName());

        return uSkyBlock != null;
	}


    /**
     * Get the level of the island of a player. Returns 0 if the player does not have an island.
     * @param player Player to get island level of
     * @return level of the island.
     */
    public double getIslandLevel(Player player) {
        if (!isHooked())
            return -1;

        return uSkyBlock.getIslandLevel(player);
    }

    /**
     * Get the rank of the island of a player.
     * @param player Player to get island of
     * @return island rank of a player
     */
    public int getIslandRank(Player player) {
        if (!isHooked())
            return -1;

        return uSkyBlock.getIslandRank(player).getRank();
    }

}
