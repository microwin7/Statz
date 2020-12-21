package me.staartvin.utils.pluginlibrary.hooks;

import com.gmail.nossr50.api.*;
import com.gmail.nossr50.api.exceptions.InvalidFormulaTypeException;
import com.gmail.nossr50.api.exceptions.InvalidPlayerException;
import com.gmail.nossr50.api.exceptions.InvalidSkillException;
import com.gmail.nossr50.api.exceptions.InvalidXPGainReasonException;
import com.gmail.nossr50.datatypes.party.Party;
import com.gmail.nossr50.mcMMO;
import me.staartvin.utils.pluginlibrary.Library;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

/**
 * mcMMO library,
 * <a href="https://www.spigotmc.org/resources/mcmmo.2445/">link</a>.
 * <p>
 * Date created: 18:25:13 12 aug. 2015
 *
 * @author Staartvin
 */
public class McMMOHook extends LibraryHook {

    private mcMMO api;


    @Override
    public boolean isHooked() {
        return api != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        if (!isPluginAvailable(Library.MCMMO))
            return false;

        api = (mcMMO) this.getServer().getPluginManager().getPlugin(Library.MCMMO.getInternalPluginName());

        return api != null;
    }

    /* ExperienceAPI below */

    /**
     * Checks whether given string is a valid type of skill suitable for the
     * other API calls in mcMMO. 
     *
     * @param skillType String to check.
     * @return true if this is a valid mcMMO skill.
     */
    public boolean isValidSkillType(String skillType) {

        if (!this.isHooked()) return false;

        try {
            return ExperienceAPI.isValidSkillType(skillType);
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Checks whether the given skill type string is both valid and not a child
     * skill. (Child skills have no XP of their own, and their level is derived
     * from the parent(s).) 
     *
     * @param skillType the skill to check
     * @return true if this is a valid, non-child mcMMO skill
     */
    public boolean isNonChildSkill(String skillType) {
        if (!this.isHooked()) return false;

        try {
            return ExperienceAPI.isNonChildSkill(skillType);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Adds raw XP to the player. 
     *
     * @param player       The player to add XP to
     * @param skillType    The skill to add XP to
     * @param XP           The amount of XP to add
     * @param xpGainReason The reason to gain XP, choose from PVP, PVE, VAMPIRISM,
     *                     SHARED_PVP, SHARED_PVE, COMMAND or UNKNOWN.
     * @param isUnshared   true if the XP cannot be shared with party members
     * @throws InvalidSkillException        if the given skill is not valid
     * @throws InvalidXPGainReasonException if the given xpGainReason is not valid
     */
    public void addRawXP(Player player, String skillType, float XP, String xpGainReason, boolean isUnshared) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.addRawXP(player, skillType, XP, xpGainReason, isUnshared);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Adds raw XP to an offline player. 
     *
     * @param uuid      The UUID of player to add XP to
     * @param skillType The skill to add XP to
     * @param XP        The amount of XP to add
     * @throws InvalidSkillException  if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public void addRawXPOffline(UUID uuid, String skillType, float XP) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.addRawXPOffline(uuid, skillType, XP);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Adds XP to the player, calculates for XP Rate and skill modifier. 
     *
     * @param player       The player to add XP to
     * @param skillType    The skill to add XP to
     * @param XP           The amount of XP to add
     * @param xpGainReason The reason to gain XP, choose from PVP, PVE, VAMPIRISM,
     *                     SHARED_PVP, SHARED_PVE, COMMAND or UNKNOWN.
     * @param isUnshared   true if the XP cannot be shared with party members
     * @throws InvalidSkillException        if the given skill is not valid
     * @throws InvalidXPGainReasonException if the given xpGainReason is not valid
     */
    public void addModifiedXP(Player player, String skillType, int XP, String xpGainReason, boolean isUnshared) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.addModifiedXP(player, skillType, XP, xpGainReason, isUnshared);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Adds XP to the player, calculates for XP Rate only. 
     *
     * @param player       The player to add XP to
     * @param skillType    The skill to add XP to
     * @param XP           The amount of XP to add
     * @param xpGainReason The reason to gain XP, choose from PVP, PVE, VAMPIRISM,
     *                     SHARED_PVP, SHARED_PVE, COMMAND or UNKNOWN.
     * @throws InvalidSkillException        if the given skill is not valid
     * @throws InvalidXPGainReasonException if the given xpGainReason is not valid
     */
    public void addMultipliedXP(Player player, String skillType, int XP, String xpGainReason) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.addMultipliedXP(player, skillType, XP, xpGainReason);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Adds XP to an offline player, calculates for XP Rate only. 
     *
     * @param playerName The player to add XP to
     * @param skillType  The skill to add XP to
     * @param XP         The amount of XP to add
     * @throws InvalidSkillException  if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     * @deprecated Unknown reason
     */
    @Deprecated
    public void addMultipliedXPOffline(String playerName, String skillType, int XP) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.addMultipliedXPOffline(playerName, skillType, XP);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Adds XP to an offline player, calculates for XP Rate and skill
     * modifier. 
     *
     * @param playerName The player to add XP to
     * @param skillType  The skill to add XP to
     * @param XP         The amount of XP to add
     * @throws InvalidSkillException  if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     * @deprecated Unknown reason
     */
    @Deprecated
    public void addModifiedXPOffline(String playerName, String skillType, int XP) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.addModifiedXPOffline(playerName, skillType, XP);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Adds XP to the player, calculates for XP Rate, skill modifiers, perks,
     * child skills, and party sharing. 
     *
     * @param player       The player to add XP to
     * @param skillType    The skill to add XP to
     * @param XP           The amount of XP to add
     * @param xpGainReason The reason to gain XP, choose from PVP, PVE, VAMPIRISM,
     *                     SHARED_PVP, SHARED_PVE, COMMAND or UNKNOWN.
     * @param isUnshared   true if the XP cannot be shared with party members
     * @throws InvalidSkillException        if the given skill is not valid
     * @throws InvalidXPGainReasonException if the given xpGainReason is not valid
     */
    public void addXP(Player player, String skillType, int XP, String xpGainReason, boolean isUnshared) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.addXP(player, skillType, XP, xpGainReason, isUnshared);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Get the amount of XP a player has in a specific skill. 
     *
     * @param player    The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP in a given skill
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getXP(Player player, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getXP(player, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the amount of XP an offline player has in a specific skill. 
     *
     * @param uuid      The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP in a given skill
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws InvalidPlayerException        if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getOfflineXP(UUID uuid, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getOfflineXP(uuid, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the raw amount of XP a player has in a specific skill. 
     *
     * @param player    The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP in a given skill
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public float getXPRaw(Player player, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getXPRaw(player, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the raw amount of XP an offline player has in a specific skill.
     *
     * @param uuid      The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP in a given skill
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws InvalidPlayerException        if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public float getOfflineXPRaw(UUID uuid, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getOfflineXPRaw(uuid, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the total amount of XP needed to reach the next level. 
     *
     * @param player    The player to get the XP amount for
     * @param skillType The skill to get the XP amount for
     * @return the total amount of XP needed to reach the next level
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getXPToNextLevel(Player player, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getXPToNextLevel(player, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the total amount of XP an offline player needs to reach the next
     * level. 
     *
     * @param uuid      The player to get XP for
     * @param skillType The skill to get XP for
     * @return the total amount of XP needed to reach the next level
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws InvalidPlayerException        if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getOfflineXPToNextLevel(UUID uuid, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getOfflineXPToNextLevel(uuid, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the amount of XP remaining until the next level. 
     *
     * @param player    The player to get the XP amount for
     * @param skillType The skill to get the XP amount for
     * @return the amount of XP remaining until the next level
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getXPRemaining(Player player, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getXPRemaining(player, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the amount of XP an offline player has left before leveling up. 
     *
     * @param uuid      The player to get XP for
     * @param skillType The skill to get XP for
     * @return the amount of XP needed to reach the next level
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws InvalidPlayerException        if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public float getOfflineXPRemaining(UUID uuid, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getOfflineXPRemaining(uuid, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Add levels to a skill. 
     *
     * @param player    The player to add levels to
     * @param skillType Type of skill to add levels to
     * @param levels    Number of levels to add
     * @throws InvalidSkillException if the given skill is not valid
     */
    public void addLevel(Player player, String skillType, int levels) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.addLevel(player, skillType, levels);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Add levels to a skill for an offline player. 
     * .
     *
     * @param uuid      The player to add levels to
     * @param skillType Type of skill to add levels to
     * @param levels    Number of levels to add
     * @throws InvalidSkillException  if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public void addLevelOffline(UUID uuid, String skillType, int levels) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.addLevelOffline(uuid, skillType, levels);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Get the level a player has in a specific skill. 
     *
     * @param player    The player to get the level for
     * @param skillType The skill to get the level for
     * @return the level of a given skill
     * @throws InvalidSkillException if the given skill is not valid
     */
    public int getLevel(Player player, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getLevel(player, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the level an offline player has in a specific skill. 
     *
     * @param uuid      The player to get the level for
     * @param skillType The skill to get the level for
     * @return the level of a given skill
     * @throws InvalidSkillException  if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public int getLevelOffline(UUID uuid, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getLevelOffline(uuid, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Gets the power level of a player. 
     *
     * @param player The player to get the power level for
     * @return the power level of the player
     */
    public int getPowerLevel(Player player) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getPowerLevel(player);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Gets the power level of an offline player. 
     *
     * @param uuid The player to get the power level for
     * @return the power level of the player
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public int getPowerLevelOffline(UUID uuid) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getPowerLevelOffline(uuid);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the level cap of a specific skill. 
     *
     * @param skillType The skill to get the level cap for
     * @return the level cap of a given skill
     * @throws InvalidSkillException if the given skill is not valid
     */
    public int getLevelCap(String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getLevelCap(skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the power level cap. 
     *
     * @return the overall power level cap
     */
    public int getPowerLevelCap() {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getPowerLevelCap();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the position on the leaderboard of a player. 
     *
     * @param uuid      The name of the player to check
     * @param skillType The skill to check
     * @return the position on the leaderboard
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws InvalidPlayerException        if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public int getPlayerRankSkill(UUID uuid, String skillType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getPlayerRankSkill(uuid, skillType);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get the position on the power level leaderboard of a player. 
     *
     * @param uuid The name of the player to check
     * @return the position on the power level leaderboard
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public int getPlayerRankOverall(UUID uuid) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getPlayerRankOverall(uuid);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Sets the level of a player in a specific skill type. 
     *
     * @param player     The player to set the level of
     * @param skillType  The skill to set the level for
     * @param skillLevel The value to set the level to
     * @throws InvalidSkillException if the given skill is not valid
     */
    public void setLevel(Player player, String skillType, int skillLevel) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.setLevel(player, skillType, skillLevel);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Sets the level of an offline player in a specific skill type. 
     *
     * @param uuid       The player to set the level of
     * @param skillType  The skill to set the level for
     * @param skillLevel The value to set the level to
     * @throws InvalidSkillException  if the given skill is not valid
     * @throws InvalidPlayerException if the given player does not exist in the database
     */
    public void setLevelOffline(UUID uuid, String skillType, int skillLevel) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.setLevelOffline(uuid, skillType, skillLevel);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Sets the XP of a player in a specific skill type. 
     *
     * @param player    The player to set the XP of
     * @param skillType The skill to set the XP for
     * @param newValue  The value to set the XP to
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public void setXP(Player player, String skillType, int newValue) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.setXP(player, skillType, newValue);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Sets the XP of an offline player in a specific skill type. 
     *
     * @param uuid      The player to set the XP of
     * @param skillType The skill to set the XP for
     * @param newValue  The value to set the XP to
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws InvalidPlayerException        if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public void setXPOffline(UUID uuid, String skillType, int newValue) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.setXPOffline(uuid, skillType, newValue);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Removes XP from a player in a specific skill type. 
     *
     * @param player    The player to change the XP of
     * @param skillType The skill to change the XP for
     * @param xp        The amount of XP to remove
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public void removeXP(Player player, String skillType, int xp) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.removeXP(player, skillType, xp);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Removes XP from an offline player in a specific skill type. 
     *
     * @param uuid      The player to change the XP of
     * @param skillType The skill to change the XP for
     * @param xp        The amount of XP to remove
     * @throws InvalidSkillException         if the given skill is not valid
     * @throws InvalidPlayerException        if the given player does not exist in the database
     * @throws UnsupportedOperationException if the given skill is a child skill
     */
    public void removeXPOffline(UUID uuid, String skillType, int xp) {
        if (!this.isHooked()) return;

        try {
            ExperienceAPI.removeXPOffline(uuid, skillType, xp);
        } catch (Exception e) {
            return;
        }

    }

    /**
     * Check how much XP is needed for a specific level with the selected level
     * curve. 
     *
     * @param level The level to get the amount of XP for
     * @throws InvalidFormulaTypeException if the given formulaType is not valid
     */
    public int getXpNeededToLevel(int level) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getXpNeededToLevel(level);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Check how much XP is needed for a specific level with the provided level
     * curve. 
     *
     * @param level       The level to get the amount of XP for
     * @param formulaType The formula type to get the amount of XP for
     * @throws InvalidFormulaTypeException if the given formulaType is not valid
     */
    public int getXpNeededToLevel(int level, String formulaType) {
        if (!this.isHooked()) return -1;

        try {
            return ExperienceAPI.getXpNeededToLevel(level, formulaType);
        } catch (Exception e) {
            return -1;
        }
    }

    /* AbilityAPI below */

    /**
     * Checks whether Berserk is enabled for a specific player.
     *
     * @param player Player to check for
     * @return true if enabled; false otherwise.
     */
    public boolean berserkEnabled(Player player) {
        if (!this.isHooked()) return false;

        try {
            return AbilityAPI.berserkEnabled(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks whether GigaDrill Breaker is enabled for a specific player.
     *
     * @param player Player to check for
     * @return true if enabled; false otherwise.
     */
    public boolean gigaDrillBreakerEnabled(Player player) {
        if (!this.isHooked()) return false;

        try {
            return AbilityAPI.gigaDrillBreakerEnabled(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks whether Green Terra is enabled for a specific player.
     *
     * @param player Player to check for
     * @return true if enabled; false otherwise.
     */
    public boolean greenTerraEnabled(Player player) {
        if (!this.isHooked()) return false;

        try {
            return AbilityAPI.greenTerraEnabled(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks whether Serrated Strikes is enabled for a specific player.
     *
     * @param player Player to check for
     * @return true if enabled; false otherwise.
     */
    public boolean serratedStrikesEnabled(Player player) {
        if (!this.isHooked()) return false;

        try {
            return AbilityAPI.serratedStrikesEnabled(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks whether Skull Splitter is enabled for a specific player.
     *
     * @param player Player to check for
     * @return true if enabled; false otherwise.
     */
    public boolean skullSplitterEnabled(Player player) {
        if (!this.isHooked()) return false;

        try {
            return AbilityAPI.skullSplitterEnabled(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks whether Super Breaker is enabled for a specific player.
     *
     * @param player Player to check for
     * @return true if enabled; false otherwise.
     */
    public boolean superBreakerEnabled(Player player) {
        if (!this.isHooked()) return false;

        try {
            return AbilityAPI.superBreakerEnabled(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks whether Tree Feller is enabled for a specific player.
     *
     * @param player Player to check for
     * @return true if enabled; false otherwise.
     */
    public boolean treeFellerEnabled(Player player) {
        if (!this.isHooked()) return false;

        try {
            return AbilityAPI.treeFellerEnabled(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks whether any special ability is enabled for a specific player.
     *
     * @param player Player to check for
     * @return true if enabled; false otherwise.
     */
    public boolean isAnyAbilityEnabled(Player player) {
        if (!this.isHooked()) return false;

        try {
            return AbilityAPI.isAnyAbilityEnabled(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Resets all ability cooldowns for a player so he/she can use it again
     * immediately.
     *
     * @param player Player to reset cooldowns for.
     */
    public void resetCooldowns(Player player) {
        if (!this.isHooked()) return;

        try {
            AbilityAPI.resetCooldowns(player);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Set the cooldown of Berserk of a specific player.
     *
     * @param player   Player to set the cooldown for.
     * @param cooldown Cooldown (in seconds) for the ability.
     */
    public void setBerserkCooldown(Player player, long cooldown) {
        if (!this.isHooked()) return;

        try {
            AbilityAPI.setBerserkCooldown(player, cooldown);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Set the cooldown of GigaDrill Breaker of a specific player.
     *
     * @param player   Player to set the cooldown for.
     * @param cooldown Cooldown (in seconds) for the ability.
     */
    public void setGigaDrillBreakerCooldown(Player player, long cooldown) {
        if (!this.isHooked()) return;

        try {
            AbilityAPI.setGigaDrillBreakerCooldown(player, cooldown);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Set the cooldown of Green Terra of a specific player.
     *
     * @param player   Player to set the cooldown for.
     * @param cooldown Cooldown (in seconds) for the ability.
     */
    public void setGreenTerraCooldown(Player player, long cooldown) {
        if (!this.isHooked()) return;

        try {
            AbilityAPI.setGreenTerraCooldown(player, cooldown);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Set the cooldown of Serrated Strikes of a specific player.
     *
     * @param player   Player to set the cooldown for.
     * @param cooldown Cooldown (in seconds) for the ability.
     */
    public void setSerratedStrikesCooldown(Player player, long cooldown) {
        if (!this.isHooked()) return;

        try {
            AbilityAPI.setSerratedStrikesCooldown(player, cooldown);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Set the cooldown of Skull Splitter of a specific player.
     *
     * @param player   Player to set the cooldown for.
     * @param cooldown Cooldown (in seconds) for the ability.
     */
    public void setSkullSplitterCooldown(Player player, long cooldown) {
        if (!this.isHooked()) return;

        try {
            AbilityAPI.setSkullSplitterCooldown(player, cooldown);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Set the cooldown of Super Breaker of a specific player.
     *
     * @param player   Player to set the cooldown for.
     * @param cooldown Cooldown (in seconds) for the ability.
     */
    public void setSuperBreakerCooldown(Player player, long cooldown) {
        if (!this.isHooked()) return;

        try {
            AbilityAPI.setSuperBreakerCooldown(player, cooldown);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Set the cooldown of Tree Feller of a specific player.
     *
     * @param player   Player to set the cooldown for.
     * @param cooldown Cooldown (in seconds) for the ability.
     */
    public void setTreeFellerCooldown(Player player, long cooldown) {
        if (!this.isHooked()) return;

        try {
            AbilityAPI.setTreeFellerCooldown(player, cooldown);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Checks whether the given entity is bleeding.
     *
     * @param entity Entity to check.
     * @return true if bleeding; false otherwise.
     */
    public boolean isBleeding(LivingEntity entity) {
        if (!this.isHooked()) return false;

        try {
            return AbilityAPI.isBleeding(entity);
        } catch (Exception e) {
            return false;
        }
    }

    /* ChatAPI below */

    /**
     * Send a message to all members of a party 
     *
     * @param plugin      The plugin sending the message
     * @param sender      The name of the sender
     * @param displayName The display name of the sender
     * @param party       The name of the party to send to
     * @param message     The message to send
     */
    public void sendPartyChat(Plugin plugin, String sender, String displayName, String party, String message) {
        if (!this.isHooked()) return;

        try {
            ChatAPI.sendPartyChat(plugin, sender, displayName, party, message);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Send a message to all members of a party 
     *
     * @param plugin  The plugin sending the message
     * @param sender  The name of the sender to display in the chat
     * @param party   The name of the party to send to
     * @param message The message to send
     */
    public void sendPartyChat(Plugin plugin, String sender, String party, String message) {
        if (!this.isHooked()) return;

        try {
            ChatAPI.sendPartyChat(plugin, sender, party, message);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Send a message to administrators 
     *
     * @param plugin      The plugin sending the message
     * @param sender      The name of the sender
     * @param displayName The display name of the sender
     * @param message     The message to send
     */
    public void sendAdminChat(Plugin plugin, String sender, String displayName, String message) {
        if (!this.isHooked()) return;

        try {
            ChatAPI.sendAdminChat(plugin, sender, displayName, message);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Send a message to administrators 
     *
     * @param plugin  The plugin sending the message
     * @param sender  The name of the sender to display in the chat
     * @param message The message to send
     */
    public void sendAdminChat(Plugin plugin, String sender, String message) {
        if (!this.isHooked()) return;

        try {
            ChatAPI.sendAdminChat(plugin, sender, message);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Check if a player is currently talking in party chat.
     *
     * @param player The player to check
     * @return true if the player is using party chat, false otherwise
     */
    public boolean isUsingPartyChat(Player player) {
        if (!this.isHooked()) return false;

        try {
            return ChatAPI.isUsingPartyChat(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if a player is currently talking in party chat.
     *
     * @param playerName The name of the player to check
     * @return true if the player is using party chat, false otherwise
     */
    public boolean isUsingPartyChat(String playerName) {
        if (!this.isHooked()) return false;

        try {
            return ChatAPI.isUsingPartyChat(playerName);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if a player is currently talking in admin chat.
     *
     * @param player The player to check
     * @return true if the player is using admin chat, false otherwise
     */
    public boolean isUsingAdminChat(Player player) {
        if (!this.isHooked()) return false;

        try {
            return ChatAPI.isUsingAdminChat(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if a player is currently talking in admin chat.
     *
     * @param playerName The name of the player to check
     * @return true if the player is using admin chat, false otherwise
     */
    public boolean isUsingAdminChat(String playerName) {
        if (!this.isHooked()) return false;

        try {
            return ChatAPI.isUsingAdminChat(playerName);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Toggle the party chat mode of a player.
     *
     * @param player The player to toggle party chat on.
     */
    public void togglePartyChat(Player player) {
        if (!this.isHooked()) return;

        try {
            ChatAPI.togglePartyChat(player);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Toggle the party chat mode of a player.
     *
     * @param playerName The name of the player to toggle party chat on.
     */
    public void togglePartyChat(String playerName) {
        if (!this.isHooked()) return;

        try {
            ChatAPI.togglePartyChat(playerName);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Toggle the admin chat mode of a player.
     *
     * @param player The player to toggle admin chat on.
     */
    public void toggleAdminChat(Player player) {
        if (!this.isHooked()) return;

        try {
            ChatAPI.toggleAdminChat(player);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Toggle the admin chat mode of a player.
     *
     * @param playerName The name of the player to toggle party chat on.
     */
    public void toggleAdminChat(String playerName) {
        if (!this.isHooked()) return;

        try {
            ChatAPI.toggleAdminChat(playerName);
        } catch (Exception e) {
            return;
        }
    }

    /* PartyAPI below */

    /**
     * Get the name of the party a player is in. 
     *
     * @param player The player to check the party name of
     * @return the name of the player's party, or null if not in a party
     */
    public String getPartyName(Player player) {
        if (!this.isHooked()) return null;

        try {
            return PartyAPI.getPartyName(player);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checks if a player is in a party. 
     *
     * @param player The player to check
     * @return true if the player is in a party, false otherwise
     */
    public boolean inParty(Player player) {
        if (!this.isHooked()) return false;

        try {
            return PartyAPI.inParty(player);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if two players are in the same party. 
     *
     * @param playera The first player to check
     * @param playerb The second player to check
     * @return true if the two players are in the same party, false otherwise
     */
    public boolean inSameParty(Player playera, Player playerb) {
        if (!this.isHooked()) return false;

        try {
            return PartyAPI.inSameParty(playera, playerb);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get a list of all current parties. 
     *
     * @return the list of parties.
     */
    public List<Party> getParties() {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return PartyAPI.getParties();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Add a player to a party. 
     *
     * @param player    The player to add to the party
     * @param partyName The party to add the player to
     */
    public void addToParty(Player player, String partyName) {
        if (!this.isHooked()) return;

        try {
            PartyAPI.addToParty(player, partyName);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Remove a player from a party. 
     *
     * @param player The player to remove
     */
    public void removeFromParty(Player player) {
        if (!this.isHooked()) return;

        try {
            PartyAPI.removeFromParty(player);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Get the leader of a party. 
     *
     * @param partyName The party name
     * @return the leader of the party
     */
    public String getPartyLeader(String partyName) {
        if (!this.isHooked()) return null;

        try {
            return PartyAPI.getPartyLeader(partyName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Set the leader of a party. 
     *
     * @param partyName  The name of the party to set the leader of
     * @param playerName The playerName to set as leader
     * @deprecated Unknown reason.
     */
    @Deprecated
    public void setPartyLeader(String partyName, String playerName) {
        if (!this.isHooked()) return;

        try {
            PartyAPI.setPartyLeader(partyName, playerName);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Get a list of all players in this player's party. 
     *
     * @param player The player to check
     * @return all the players in the player's party
     * @deprecated Unknown reason.
     */
    @Deprecated
    public List<OfflinePlayer> getOnlineAndOfflineMembers(Player player) {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return PartyAPI.getOnlineAndOfflineMembers(player);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Get a list of all player names in this player's party. 
     *
     * @param player The player to check
     * @return all the player names in the player's party
     * @deprecated Unknown reason.
     */
    @Deprecated
    public LinkedHashSet<String> getMembers(Player player) {
        if (!this.isHooked()) return null;

        try {
            return PartyAPI.getMembers(player);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get a list of all player names and uuids in this player's party. 
     *
     * @param player The player to check
     * @return all the player names and uuids in the player's party
     */
    public LinkedHashMap<UUID, String> getMembersMap(Player player) {
        if (!this.isHooked()) return null;

        try {
            return PartyAPI.getMembersMap(player);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get a list of all online players in this party. 
     *
     * @param partyName The party to check
     * @return all online players in this party
     */
    public List<Player> getOnlineMembers(String partyName) {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return PartyAPI.getOnlineMembers(partyName);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Get a list of all online players in this player's party. 
     *
     * @param player The player to check
     * @return all online players in the player's party
     */
    public List<Player> getOnlineMembers(Player player) {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return PartyAPI.getOnlineMembers(player);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Whether the given party has an ally.
     *
     * @param partyName Party to check.
     * @return true if the party has an ally; false otherwise.
     */
    public boolean hasAlly(String partyName) {
        if (!this.isHooked()) return false;

        try {
            return PartyAPI.hasAlly(partyName);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the ally of a party.
     *
     * @param partyName Party to get the ally from.
     * @return Name of the ally or null if not found.
     */
    public String getAllyName(String partyName) {
        if (!this.isHooked()) return null;

        try {
            return PartyAPI.getAllyName(partyName);
        } catch (Exception e) {
            return null;
        }
    }

    /* SkillAPI below */

    /**
     * Returns a list of strings with mcMMO's skills This includes parent and
     * child skills 
     *
     * @return a list of strings with valid skill names
     */
    public List<String> getSkills() {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return SkillAPI.getSkills();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Returns a list of strings with mcMMO's skills This only includes parent
     * skills 
     *
     * @return a list of strings with valid skill names
     */
    public List<String> getNonChildSkills() {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return SkillAPI.getNonChildSkills();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Returns a list of strings with mcMMO's skills This only includes child
     * skills 
     *
     * @return a list of strings with valid skill names
     */
    public List<String> getChildSkills() {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return SkillAPI.getChildSkills();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Returns a list of strings with mcMMO's skills This only includes combat
     * skills 
     *
     * @return a list of strings with valid skill names
     */
    public List<String> getCombatSkills() {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return SkillAPI.getCombatSkills();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Returns a list of strings with mcMMO's skills This only includes
     * gathering skills 
     *
     * @return a list of strings with valid skill names
     */
    public List<String> getGatheringSkills() {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return SkillAPI.getGatheringSkills();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Returns a list of strings with mcMMO's skills This only includes misc
     * skills 
     *
     * @return a list of strings with valid skill names
     */
    public List<String> getMiscSkills() {
        if (!this.isHooked()) return new ArrayList<>();

        try {
            return SkillAPI.getMiscSkills();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
