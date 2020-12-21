package me.staartvin.utils.pluginlibrary.hooks;

import me.staartvin.utils.pluginlibrary.Library;
import net.flamedek.rpgme.RPGme;
import net.flamedek.rpgme.player.RPGPlayer;
import net.flamedek.rpgme.skills.SkillType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * RPGme library,
 * <a href="https://www.spigotmc.org/resources/rpgme.7857/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class RPGmeHook extends LibraryHook {

    @Override
    public boolean isHooked() {
        return isPluginAvailable(Library.RPGME);
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        return isPluginAvailable(Library.RPGME);
    }

    /**
     * Get the SkillType that is associated with the given name.
     * @param skillName Name of the skil
     * @return the associated SkillType or null if not found.
     */
    public SkillType getSkill(String skillName) {

        if (!this.isHooked()) {
            return null;
        }

        return SkillType.getByAlias(skillName);
    }

    /**
     * Get the level of a given skill for a given player.
     * @param player Player to get the skill level of
     * @param skillName Name of the skill
     * @return the level of the skill or -1 if invalid skill or no data is available
     */
    public int getSkillLevel(Player player, String skillName) {

        if (!this.isHooked())
            return -1;

        SkillType type = this.getSkill(skillName);

        if (type == null)
            return -1;

        return RPGme.getAPI().getLevel(player, type);
    }

    /**
     * Get the experience of a given skill for a given player.
     * @param player Player to get the skill experience of
     * @param skillName Name of the skill
     * @return the experience of the skill or -1 if invalid skill or no data is available
     */
    public float getSkillExp(Player player, String skillName) {
        if (!this.isHooked())
            return -1;

        SkillType type = this.getSkill(skillName);

        if (type == null)
            return -1;

        return RPGme.getAPI().getExp(player, type);
    }

    /**
     * Get the total level of all combined skills for a given player.
     * @param player Player to get the total level of
     * @return the total level or -1 if no data is available
     */
    public int getTotalLevel(Player player) {
        if (!this.isHooked())
            return -1;

        RPGPlayer RPGPlayer = RPGme.getAPI().get(player);

        if (RPGPlayer == null)
            return -1;

        return RPGPlayer.getSkillSet().getTotalLevel();
    }

    /**
     * Get the combat level of a given player.
     * @param player Player to get the combat level of
     * @return the combat level or -1 if no data is available
     */
    public int getCombatLevel(Player player) {
        if (!this.isHooked())
            return -1;

        RPGPlayer RPGPlayer = RPGme.getAPI().get(player);

        if (RPGPlayer == null)
            return -1;

        return RPGPlayer.getSkillSet().getCombatLevel();
    }

    /**
     * Get the average level of all skills for a given player.
     * @param player Player to get the average level of
     * @return the average level or -1 if no data is available
     */
    public int getAverageLevel(Player player) {
        if (!this.isHooked())
            return -1;

        RPGPlayer RPGPlayer = RPGme.getAPI().get(player);

        if (RPGPlayer == null)
            return -1;

        return RPGPlayer.getSkillSet().getAverageLevel();
    }

    /**
     * Get the players in a given player's party.
     * @param player Player to get the party members of.
     * @return A list of uuids that represent players in the given player's party or an empty list if no party was found.
     */
    public List<UUID> getPlayersInParty(Player player) {
        List<UUID> uuids = new ArrayList<>();

        if (!this.isHooked())
            return uuids;

        RPGPlayer RPGPlayer = RPGme.getAPI().get(player);

        if (RPGPlayer == null)
            return uuids;

        for (RPGPlayer rPlayer : RPGPlayer.getParty()) {
            uuids.add(rPlayer.getPlayerID());
        }

        return uuids;
    }

}
