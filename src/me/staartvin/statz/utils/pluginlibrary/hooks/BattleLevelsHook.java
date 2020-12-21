package me.staartvin.utils.pluginlibrary.hooks;

import me.robin.battlelevels.api.BattleLevelsAPI;
import me.staartvin.utils.pluginlibrary.Library;

import java.util.UUID;

/**
 * BattleLevels library,
 * <a href="https://www.spigotmc.org/resources/battlelevels.2218/">link</a>.
 * <p>
 * 
 * @author Staartvin
 * 
 */
public class BattleLevelsHook extends LibraryHook {


    @Override
    public boolean isHooked() {
        return isPluginAvailable(Library.BATTLELEVELS);
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.utils.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        return isPluginAvailable(Library.BATTLELEVELS);
    }

    /**
     * Get the kill death ratio of a player
     * @param uuid UUID of a player
     * @return kill death ratio of given player or -1 if not available.
     */
    public double getKillDeathRatio(UUID uuid) {

        if (!this.isHooked()) {
            return -1;
        }

        return BattleLevelsAPI.getKdr(uuid);
    }

    /**
     * Get the number of kills of a player
     * @param uuid UUID of a player
     * @return number of kills of given player or -1 if not available.
     */
    public int getKills(UUID uuid) {

        if (!this.isHooked()) {
            return -1;
        }

        return BattleLevelsAPI.getKills(uuid);
    }

    /**
     * Get the number of deaths of a player
     * @param uuid UUID of a player
     * @return number of deaths of given player or -1 if not available.
     */
    public int getDeaths(UUID uuid) {

        if (!this.isHooked()) {
            return -1;
        }

        return BattleLevelsAPI.getDeaths(uuid);
    }

    /**
     * Get the BattleLevel of a player
     * @param uuid UUID of a player
     * @return BattleLevel of given player or -1 if not available.
     */
    public int getLevel(UUID uuid) {

        if (!this.isHooked()) {
            return -1;
        }

        return BattleLevelsAPI.getLevel(uuid);
    }

    /**
     * Get the BattleLevel score of a player
     * @param uuid UUID of a player
     * @return BattleLevel score of given player or -1 if not available.
     */
    public double getScore(UUID uuid) {

        if (!this.isHooked()) {
            return -1;
        }

        return BattleLevelsAPI.getScore(uuid);
    }

    /**
     * Get the current killstreak of a player
     * @param uuid UUID of a player
     * @return current killstreak of given player or -1 if not available.
     */
    public int getKillStreak(UUID uuid) {

        if (!this.isHooked()) {
            return -1;
        }

        return BattleLevelsAPI.getKillstreak(uuid);
    }

    /**
     * Get the top killstreak of a player
     * @param uuid UUID of a player
     * @return top killstreak of given player or -1 if not available.
     */
    public int getTopKillStreak(UUID uuid) {

        if (!this.isHooked()) {
            return -1;
        }

        return BattleLevelsAPI.getTopKillstreak(uuid);
    }

}
