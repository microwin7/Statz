package me.staartvin.utils.pluginlibrary.hooks;

import me.staartvin.utils.pluginlibrary.Library;
import me.staartvin.utils.pluginlibrary.hooks.afkmanager.AFKManager;

import java.util.UUID;

/**
 * UltimateCore library,
 * <a href="http://dev.bukkit.org/bukkit-plugins/ultimatecore/">link</a>.
 * <p>
 * Date created: 17:30:19 14 aug. 2015
 * 
 * @author Staartvin
 *
 */
public class UltimateCoreHook extends LibraryHook implements AFKManager {

//	private UltimateCore api;


    @Override
    public boolean isHooked() {
        return isPluginAvailable(Library.ULTIMATECORE);
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        if (!isPluginAvailable(Library.ULTIMATECORE))
            return false;

//		api = (UltimateCore) this.getServer().getPluginManager()
//                .getPlugin(Library.ULTIMATECORE.getInternalPluginName());

		return false;
	}

    /**
     * Check whether a player is AFK.
     * @param uuid UUID of the player to check.
     * @return true if the player is AFK, false otherwise.
     */
	public boolean isAFK(UUID uuid) {

		return false;
    }

	@Override
	public boolean hasAFKData() {
		return true;
	}
}
