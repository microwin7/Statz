package me.staartvin.utils.pluginlibrary.hooks;

import me.staartvin.utils.pluginlibrary.Library;
import us.eunoians.mcrpg.api.exceptions.McRPGPlayerNotFoundException;
import us.eunoians.mcrpg.players.McRPGPlayer;
import us.eunoians.mcrpg.players.PlayerManager;
import us.eunoians.mcrpg.types.Skills;

import java.util.Optional;
import java.util.UUID;

/**
 * McRPG library,
 * <a href="https://www.spigotmc.org/resources/mcrpg.63020/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class McRPGHook extends LibraryHook {

    @Override
    public boolean isHooked() {
        return isPluginAvailable(Library.MCRPG);
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        // All api calls are done static, so there is no need to get the plugin
        // class.
        // We only check if the plugin is available.

        return isPluginAvailable(Library.MCRPG);
    }

    /**
     * Get the {@link McRPGPlayer} object matching the given uuid.
     *
     * @param uuid UUID of the player.
     * @return Matched object or none if it doesn't exist.
     */
    public Optional<McRPGPlayer> getPlayer(UUID uuid) {

        if (!this.isHooked()) return Optional.empty();

        try {
            return Optional.ofNullable(PlayerManager.getPlayer(uuid));
        } catch (McRPGPlayerNotFoundException e) {
            return Optional.of(new McRPGPlayer(uuid));
        }
    }

    /**
     * Get the power level of the given player.
     *
     * @param uuid UUID of the player
     * @return power level or zero if the player could not be found.
     */
    public int getPowerLevel(UUID uuid) {
        Optional<McRPGPlayer> player = this.getPlayer(uuid);

        return player.map(McRPGPlayer::getPowerLevel).orElse(0);
    }

    /**
     * Get the level of a skill for the given player
     *
     * @param uuid      UUID of the player
     * @param skillName Name of the skill (use {@link Skills} as reference)
     * @return level of a skill or zero if the skill or player cannot be found.
     */
    public int getSkillLevel(UUID uuid, String skillName) {
        Optional<McRPGPlayer> player = this.getPlayer(uuid);

        if (!player.isPresent()) return 0;

        Skills matchingSkill = Skills.fromString(skillName);

        if (matchingSkill == null) return 0;

        return player.map(play -> play.getSkill(matchingSkill).getCurrentLevel()).orElse(0);
    }
}
