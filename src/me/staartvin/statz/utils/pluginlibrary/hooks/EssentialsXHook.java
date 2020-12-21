package me.staartvin.utils.pluginlibrary.hooks;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import me.staartvin.utils.pluginlibrary.Library;
import me.staartvin.utils.pluginlibrary.hooks.afkmanager.AFKManager;

import java.util.UUID;

/**
 * EssentialsX library,
 * <a href="https://www.spigotmc.org/resources/essentialsx.9089/">link</a>.
 * <p>
 * 
 * @author Staartvin
 *
 */
public class EssentialsXHook extends LibraryHook implements AFKManager {

	private Essentials essentials;


    @Override
    public boolean isHooked() {
        return essentials != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        if (!isPluginAvailable(Library.ESSENTIALSX))
            return false;

        essentials = (Essentials) this.getServer().getPluginManager()
                .getPlugin(Library.ESSENTIALSX.getInternalPluginName());

        return essentials != null;
    }

    /**
     * Check whether a player is jailed by EssentialsX.
     * @param uuid UUID of player to check
     * @return true if the given player is jailed, false otherwise.
     */
    public boolean isJailed(final UUID uuid) {
        if (!isHooked())
            return false;

        final User user = essentials.getUser(uuid);

        if (user == null) {
            return false;
        }

        return user.isJailed();
    }

    /**
     * Get the Geo location of the IP of a given player.
     * @param uuid UUID of player to get the location of.
     * @return the estimated country of the IP of the given player.
     */
    public String getGeoIPLocation(final UUID uuid) {
        if (!isHooked())
            return null;

        final User user = essentials.getUser(uuid);

        if (user == null) {
            return null;
        }

        return user.getGeoLocation();
    }

    /**
     * Check whether a player is AFK (away from keyboard).
     * @param uuid UUID of player to check
     * @return true if player if AFK, false otherwise.
     */
    public boolean isAFK(UUID uuid) {
        if (!isHooked()) return false;

        final User user = essentials.getUser(uuid);

        if (user == null) return false;

        return user.isAfk();
    }

    @Override
    public boolean hasAFKData() {
        return true;
    }

}
