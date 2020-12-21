package me.staartvin.utils.pluginlibrary.hooks;

import me.blackvein.quests.Quester;
import me.blackvein.quests.Quests;
import me.staartvin.utils.pluginlibrary.Library;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * Quests,
 * <a href="https://www.spigotmc.org/resources/quests.3711/">link</a>.
 * <p>
 *
 * @author Staartvin
 *
 */
public class QuestsHook extends LibraryHook {

	private Quests quests;

    @Override
    public boolean isHooked() {
        return quests != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.utils.pluginlibrary.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        if (!isPluginAvailable(Library.QUESTS))
            return false;

        Plugin plugin = this.getServer().getPluginManager()
                .getPlugin(Library.QUESTS.getInternalPluginName());

        if (!(plugin instanceof Quests))
            return false;

        quests = (Quests) plugin;

        return quests != null;
    }

	private Quester getQuester(UUID uuid) {
	    return quests.getQuester(uuid);
    }

    /**
     * Get the number of quests a player has completed.
     * @param uuid UUID of the player
     * @return the number of completed quests or -1 if no data was available
     */
	public int getNumberOfCompletedQuests(UUID uuid) {
        if (!this.isHooked()) return -1;

        Quester quester = getQuester(uuid);

        if (quester == null) {
            return -1;
        }

        return quester.getCompletedQuests().size();
    }

    /**
     * Get the number of quests a player has currently active
     * @param uuid UUID of the player
     * @return the number of active quests or -1 if no data was available
     */
    public int getNumberOfActiveQuests(UUID uuid) {
        if (!this.isHooked()) return -1;

        Quester quester = getQuester(uuid);

        if (quester == null) {
            return -1;
        }

        return quester.getCurrentQuests().size();
    }

    /**
     * Get the points achieved by completing quests.
     * @param uuid UUID of the player
     * @return the number of questspoints the player has or -1 if no data was available
     */
    public int getQuestsPoints(UUID uuid) {
        if (!this.isHooked()) return -1;

        Quester quester = getQuester(uuid);

        if (quester == null) {
            return -1;
        }

        return quester.getQuestPoints();
    }

    /**
     * Check whether a player has completed a quest.
     * @param uuid UUID of the player
     * @param questName Name of the quest to check
     * @return true if the player has completed the quest, false otherwise.
     */
    public boolean isQuestCompleted(UUID uuid, String questName) {
        if (!this.isHooked()) return false;

        Quester quester = getQuester(uuid);

        if (quester == null) {
            return false;
        }

        return quester.getCompletedQuests().contains(questName);
    }


}