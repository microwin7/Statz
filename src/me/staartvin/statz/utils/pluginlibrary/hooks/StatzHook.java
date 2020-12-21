package me.staartvin.utils.pluginlibrary.hooks;

import me.staartvin.statz.Statz;
import me.staartvin.statz.database.datatype.RowRequirement;
import me.staartvin.statz.datamanager.player.PlayerStat;
import me.staartvin.utils.pluginlibrary.Library;
import org.bukkit.Statistic;

import java.util.UUID;

/**
 * Statz library,
 * <a href="https://www.spigotmc.org/resources/statz.25969/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class StatzHook extends LibraryHook {

    private Statz statz;

    @Override
    public boolean isHooked() {
        return statz != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        if (!isPluginAvailable(Library.STATZ))
            return false;

        statz = (Statz) this.getServer().getPluginManager()
                .getPlugin(Library.STATZ.getInternalPluginName());

        return statz != null;
    }

    /**
     * Get a statistic (tracked by vanilla Minecraft) of a player.
     *
     * @param uuid      UUID of the player
     * @param statistic Statistic to obtain
     * @return an integer representing the statistic of a given player.
     */
    public int getMinecraftStatistic(UUID uuid, Statistic statistic) {
        if (!this.isHooked()) return -1;

        return statz.getStatzAPI().getMinecraftStatistic(uuid, statistic);
    }

    /**
     * Get the total value for a given statistic (tracked by Statz). For example,
     * how many animals did a player kill (on a given world)? If you want to obtain more specific info, use the
     * {@link #getSpecificStatistics(PlayerStat, UUID, RowRequirement...)}
     *
     * @param statType  Type of statistic to get
     * @param uuid      UUID of a player
     * @param worldName Name of the world, can be null for all world.
     * @return A list of all requirements
     */
    public Double getTotalStatistics(PlayerStat statType, UUID uuid, String worldName) {
        if (!this.isHooked()) return -1.0;

        return statz.getStatzAPI().getTotalOf(statType, uuid, worldName);
    }

    /**
     * Get data about a specific statistic, possibly involving multiple requirements. For example, how many cows did
     * a player kill on a world 'world'? If you want to obtain not-so-specific data about a player (e.g. how many animals
     * did a player kill (on a given world)?), you can use {@link #getTotalStatistics(PlayerStat, UUID, String)}
     * <p>
     * <p>
     * Row requirements are the way to specify what requirements the dataset has to meet. You can specify an infinite
     * amount of requirements.
     * </p>
     *
     * @param statType     Type of statistic to obtain
     * @param uuid         UUID of a player
     * @param requirements Requirements that the dataset has to meet.
     * @return
     */
    public Double getSpecificStatistics(PlayerStat statType, UUID uuid, RowRequirement... requirements) {
        if (!this.isHooked()) return -1.0;

        return statz.getStatzAPI().getSpecificData(statType, uuid, requirements);
    }
}
