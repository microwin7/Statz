package me.staartvin.utils.pluginlibrary.hooks;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import me.staartvin.utils.pluginlibrary.Library;

import java.util.Optional;

/**
 * TownyAdvanced library,
 * <a href="https://www.spigotmc.org/resources/towny-advanced.72694/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class TownyAdvancedHook extends LibraryHook {


    @Override
    public boolean isHooked() {
        return isPluginAvailable(Library.TOWNY_ADVANCED);
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        return isPluginAvailable(Library.TOWNY_ADVANCED);
    }

    public Optional<Resident> getResident(String playerName) {
        if (!this.isHooked()) return Optional.empty();

        if (!TownyAPI.getInstance().getDataSource().hasResident(playerName)) {
            return Optional.empty();
        }

        try {
            return Optional.ofNullable(TownyAPI.getInstance().getDataSource().getResident(playerName));
        } catch (NotRegisteredException e) {
            return Optional.empty();
        }
    }

    /**
     * Check whether the given player is part of a town.
     *
     * @param playerName Name of the player
     * @return True if the player is part of a town. False otherwise.
     */
    public boolean hasTown(String playerName) {
        Resident resident = this.getResident(playerName).orElse(null);

        if (resident == null) return false;

        return resident.hasTown();
    }

    /**
     * Check whether the given player is part of a nation.
     *
     * @param playerName Name of the player
     * @return True if the player is part of a nation. False otherwise.
     */
    public boolean hasNation(String playerName) {
        Resident resident = this.getResident(playerName).orElse(null);

        if (resident == null) return false;

        return resident.hasNation();
    }

    /**
     * Check whether the given player is a king of a nation.
     *
     * @param playerName Name of the player
     * @return true if the player is a king of a nation.
     */
    public boolean isKing(String playerName) {
        Resident resident = this.getResident(playerName).orElse(null);

        if (resident == null) return false;

        return resident.isKing();
    }

    /**
     * Check whether the given player is a mayor of a town.
     *
     * @param playerName Name of the player
     * @return true if the player is a mayor of a town.
     */
    public boolean isMayor(String playerName) {
        Resident resident = this.getResident(playerName).orElse(null);

        if (resident == null) return false;

        return resident.isMayor();
    }

    /**
     * Check whether the given player is jailed in their town.
     *
     * @param playerName Name of the player
     * @return true if the player is jailed, false otherwise.
     */
    public boolean isJailed(String playerName) {
        Resident resident = this.getResident(playerName).orElse(null);

        if (resident == null) return false;

        return resident.isJailed();
    }

    /**
     * Get the number of town blocks the given player owns.
     *
     * @param playerName Name of the player
     * @return the number of town blocks the player owns, or zero if this is not known.
     */
    public int getNumberOfTownBlocks(String playerName) {
        Resident resident = this.getResident(playerName).orElse(null);

        if (resident == null) return 0;

        return resident.getTownBlocks().size();
    }


}
