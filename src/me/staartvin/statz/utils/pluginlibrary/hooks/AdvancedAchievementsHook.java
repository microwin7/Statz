package me.staartvin.utils.pluginlibrary.hooks;

import com.hm.achievement.AdvancedAchievements;
import me.staartvin.utils.pluginlibrary.Library;

import java.util.UUID;

/**
 * AdvancedAchievements library,
 * <a href="https://www.spigotmc.org/resources/advanced-achievements.6239/">link</a>.
 * <p>
 * 
 * @author Staartvin
 * 
 */
public class AdvancedAchievementsHook extends LibraryHook {

    private AdvancedAchievements advancedAchievements;

    @Override
    public boolean isHooked() {
        return advancedAchievements != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.utils.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {

        if (!isPluginAvailable(Library.ADVANCEDACHIEVEMENTS))
            return false;

        advancedAchievements = (AdvancedAchievements) this.getServer().getPluginManager()
                .getPlugin(Library.ADVANCEDACHIEVEMENTS.getInternalPluginName());

        return advancedAchievements != null;
	}


    /**
     * Check whether a player has obtained a given achievement.
     * @param uuid UUID of the player
     * @param achievementName Name of the achievement
     * @return true if the player has obtained the given achievement, false otherwise.
     */
    public boolean hasAchievement(UUID uuid, String achievementName) {
        if (!this.isHooked()) {
            return false;
        }

        return advancedAchievements.getAdvancedAchievementsAPI().hasPlayerReceivedAchievement(uuid,
                achievementName);
    }

    /**
     * Get the number of achievements a player has obtained.
     * @param uuid UUID of the player
     * @return number of achievements of a player or -1 if no data is available.
     */
    public int getNumberOfAchievements(UUID uuid) {
        if (!this.isHooked()) {
            return -1;
        }

        return advancedAchievements.getAdvancedAchievementsAPI().getPlayerTotalAchievements(uuid);
    }

}
