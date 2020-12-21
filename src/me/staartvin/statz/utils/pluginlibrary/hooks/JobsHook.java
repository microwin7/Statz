package me.staartvin.utils.pluginlibrary.hooks;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobProgression;
import com.gamingmesh.jobs.container.JobsPlayer;
import com.gamingmesh.jobs.container.PlayerPoints;
import me.staartvin.utils.pluginlibrary.Library;
import me.staartvin.utils.pluginlibrary.util.Util;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Jobs library,
 * <a href="https://www.spigotmc.org/resources/jobs-reborn.4216/">link</a>.
 * <p>
 *
 * @author Staartvin
 */
public class JobsHook extends LibraryHook {

    @Override
    public boolean isHooked() {
        return isPluginAvailable(Library.JOBS);
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        return isPluginAvailable(Library.JOBS);
    }


    /**
     * Get the current points of a player
     *
     * @param uuid UUID of the player
     * @return the number of points a player has or -1 if no data is available
     */
    public double getCurrentPoints(UUID uuid) {
        if (!this.isHooked() || uuid == null)
            return -1;

        PlayerPoints pointInfo = Jobs.getPlayerManager().getPointsData().getPlayerPointsInfo(uuid);

        if (pointInfo == null)
            return -1;

        return Util.roundDouble(pointInfo.getCurrentPoints(), 2);
    }

    /**
     * Get the total points of a player
     *
     * @param uuid UUID of the player
     * @return the total number of points a player has or -1 if no data is available
     */
    public double getTotalPoints(UUID uuid) {
        if (!this.isHooked() || uuid == null)
            return -1;

        PlayerPoints pointInfo = Jobs.getPlayerManager().getPointsData().getPlayerPointsInfo(uuid);

        if (pointInfo == null)
            return -1;

        return Util.roundDouble(pointInfo.getTotalPoints(), 2);
    }

    /**
     * Get the current xp points of a job of a player
     *
     * @param player  Player Player to get xp points for
     * @param jobName Name of job to get the xp points for
     * @return the number of xp points a player has or -1 if no data is available
     */
    public double getCurrentXP(Player player, String jobName) {
        if (!this.isHooked())
            return -1;

        Job job = this.getJob(jobName);

        if (job == null)
            return -1;

        JobsPlayer jobsPlayer = Jobs.getPlayerManager().getJobsPlayer(player);

        if (jobsPlayer == null)
            return -1;

        JobProgression progress = jobsPlayer.getJobProgression(job);

        if (progress == null)
            return -1;

        return Util.roundDouble(progress.getExperience(), 2);
    }

    /**
     * Get the current level of a job of a player
     *
     * @param player  Player Player to get level for
     * @param jobName Name of job to get the level for
     * @return the level a player has or -1 if no data is available
     */
    public double getCurrentLevel(Player player, String jobName) {
        if (!this.isHooked())
            return -1;

        Job job = this.getJob(jobName);

        if (job == null)
            return -1;

        JobsPlayer jobsPlayer = Jobs.getPlayerManager().getJobsPlayer(player);

        if (jobsPlayer == null)
            return -1;

        JobProgression progress = jobsPlayer.getJobProgression(job);

        if (progress == null)
            return -1;

        return Util.roundDouble(progress.getLevel(), 2);
    }

    /**
     * Get the Job that is associated by the given name
     * @param jobName Name of the job
     * @return Job associated by the given name or null if Jobs was not available or the given job doesn't exist
     */
    public Job getJob(String jobName) {
        if (!this.isHooked())
            return null;

        return Jobs.getJob(jobName);
    }

    /**
     * Get the job progression of a player. This provides you with a list of jobs and their progression.
     * @param player Player
     * @return a list of jobs of the given player or null if no jobs were found or Jobs was not available.
     */
    public List<JobProgression> getJobs(Player player) {
        if (!this.isHooked())
            return null;

        JobsPlayer jobsPlayer = Jobs.getPlayerManager().getJobsPlayer(player);

        if (jobsPlayer == null)
            return null;

        return jobsPlayer.getJobProgression();
    }

}
