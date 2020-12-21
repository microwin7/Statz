package me.staartvin.utils.pluginlibrary.hooks;

import me.edge209.afkTerminator.AfkDetect;
import me.edge209.afkTerminator.AfkDetect.AFKMACHINES;
import me.staartvin.utils.pluginlibrary.Library;
import me.staartvin.utils.pluginlibrary.hooks.afkmanager.AFKManager;

import java.util.UUID;

/**
 * afkTerminator library,
 * <a href="http://dev.bukkit.org/bukkit-plugins/afkterminator/">link</a>.
 * <p>
 * Date created: 16:26:41 14 aug. 2015
 * 
 * @author Staartvin
 * 
 */
public class AFKTerminatorHook extends LibraryHook implements AFKManager {

    @Override
    public boolean isHooked() {
        return isPluginAvailable(Library.AFKTERMINATOR);
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.utils.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
		// All api calls are done static, so there is no need to get the plugin
		// class.
		// We only check if the plugin is available.
		return isPluginAvailable(Library.AFKTERMINATOR);
	}

	/**
	 * See if a player is suspected of using an AFK Machine. <br>
	 * This will return 'true' if the player's activity is indicative of an AFK
	 * Machine, but not all conditions of an AFK machine are confirmed.
	 * 
	 * @param uuid
	 *            UUID of the player to check.
	 * @return true if afkTerminator suspects that the player is using an AFK
	 *         machine; false otherwise.
	 */
	public boolean isAFKMachineSuspected(UUID uuid) {
        if (!this.isHooked()) return false;
	    return AfkDetect.isAFKMachineSuspected(uuid);
	}

	/**
	 * See if use of an AFK machine has been confirmed for a user. <br>
	 * This will return 'true' if the player's activity is confirmed to be an
	 * AFK Machine by the plugin. <br>
	 * If the machine is a big one, it can take a while before afkTerminator
	 * detects it.
	 * 
	 * @param uuid
	 *            UUID of the player to check.
	 * @return true if afkTerminator detected that the player is using an AFK
	 *         machine; false otherwise.
	 */
	public boolean isAFKMachineDetected(UUID uuid) {
        if (!this.isHooked()) return false;
	    return AfkDetect.isAFKMachineDetected(uuid);
	}

	/**
	 * If a machine is suspected or later detected, find out the timestamp of
	 * when the player is thought to have started using the machine. <br>
	 * This is expressed in milliseconds, as measured from the java epoch of Jan
	 * 1, 1970.
	 * 
	 * @param uuid
	 *            UUID of the player to check.
	 * @return Time in milliseconds since the player started using the machine,
	 *         UNIX time; otherwise 0.
	 */
	public long getAFKMachineStartTime(UUID uuid) {
        if (!this.isHooked()) return -1;
		return AfkDetect.getAFKMachineStartTime(uuid);
	}

	/**
	 * When a player is using an AFK machine, you can get the type of the AFK
	 * machine. <br>
	 * Types possible: <br>
	 * VEHICLE, TETHERED_PIG, HORSE, WATER, JUMP, PISTON, FISHING, PROJECTILE or
	 * INTERACTION.
	 * 
	 * @param uuid
	 *            UUID of the player to check.
	 * @return Type of the AFK machine or null if the player is not using any.
	 */
	public String getAFKMachineType(UUID uuid) {
        if (!this.isHooked()) return null;

		AFKMACHINES type = AfkDetect.getAFKMachineType(uuid);

		if (type == null)
			return null;

		return type.toString();
	}

	@Override
	public boolean isAFK(UUID uuid) {
		return this.isAFKMachineDetected(uuid);
	}

	@Override
	public boolean hasAFKData() {
		return true;
	}
}
