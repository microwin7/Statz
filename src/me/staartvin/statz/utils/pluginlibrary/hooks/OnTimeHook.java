package me.staartvin.utils.pluginlibrary.hooks;

import me.edge209.OnTime.DataIO;
import me.edge209.OnTime.OnTimeAPI;
import me.edge209.OnTime.OnTimeAPI.topData;
import me.edge209.OnTime.PlayingTime;
import me.staartvin.utils.pluginlibrary.Library;

import java.util.HashMap;
import java.util.Map;

/**
 * OnTime library,
 * <a href="http://dev.bukkit.org/bukkit-plugins/ontime//">link</a>.
 * <p>
 * Date created: 15:35:44 14 aug. 2015
 * 
 * @author Staartvin
 * 
 */
public class OnTimeHook extends LibraryHook {

    @Override
    public boolean isHooked() {
        return isPluginAvailable(Library.ONTIME);
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
		return isPluginAvailable(Library.ONTIME);
	}

	/**
	 * Whether or not the given player is stored by OnTime.
	 * 
	 * @param playerName
	 *            Name of the player to check.
	 * @return true if the player is stored; false otherwise.
	 */
	public boolean isPlayerStored(String playerName) {
        if (!this.isHooked()) return false;

	    return PlayingTime.playerHasOnTimeRecord(playerName);
	}

	/**
	 * Gets a specific data piece of the given player. <br>
	 * There a few data types that can be used:
	 * <p>
	 * <b>TOTALPLAY</b>: Total play time (OnTime) a player has spent on your
	 * server. The returned value is in milliseconds.
	 * <p>
	 * <b>TODAYPLAY</b>: The time a player has spent on your server since
	 * midnight of the current day. The returned value is in milliseconds.
	 * <p>
	 * <b>WEEKPLAY</b>: The time a player has spent on your server since
	 * midnight of the first day of the current week. The first day of the week
	 * is set in the OnTime/config.yml. The returned value is in milliseconds.
	 * <p>
	 * <b>MONTHPLAY</b>: The time a player has spent on your server since
	 * midnight of the first day of the current month. The first day of the
	 * month is set in the OnTime/config.yml. The returned value is in
	 * milliseconds.
	 * <p>
	 * <b>LASTLOGIN</b>: The time stamp (date and time) of the player's last
	 * login (join) event. The returned value is in milliseconds, and is the
	 * number of milliseconds that have passed since the JAVA epoch date of
	 * January 1, 1970.
	 * <p>
	 * <b>LASTVOTE</b>: The time stamp (date and time) of the player's last
	 * received vote for the server. The returned value is in milliseconds, and
	 * is the number of milliseconds that have passed since the JAVA epoch date
	 * of January 1, 1970.
	 * <p>
	 * <b>TOTALVOTE</b>: Number of votes coast by the player for all time.
	 * <p>
	 * <b>TODAYVOTE</b>: Number of votes cast by the player in the current day.
	 * <p>
	 * <b>WEEKVOTE</b>: Number of votes cast by the player in the current week.
	 * <p>
	 * <b>MONTHVOTE</b>: Number of votes cast by the player in the current
	 * month.
	 * <p>
	 * <b>TOTALREFER</b>: Number of referrals made by the player for all time.
	 * <p>
	 * <b>TODAYREFER</b>: Number of referrals made by the player in the current
	 * day.
	 * <p>
	 * <b>WEEKREFER</b>: Number of referrals made by the player in the current
	 * week.
	 * <p>
	 * <b>MONTHREFER</b>: Number of referrals made by the player in the current
	 * month.
	 * 
	 * @param playerName
	 *            Name of the player to get data for.
	 * @param dataType
	 *            Type of data, see above.
	 * @return long value corresponding to the data type, -1 if no data was
	 *         found or the player was invalid.
	 */
	public long getPlayerData(String playerName, String dataType) {
        if (!this.isHooked()) return -1;

		if (playerName == null || dataType == null)
			return -1;

		OnTimeAPI.data data = OnTimeAPI.data.valueOf(dataType.toUpperCase());

		if (data == null) {
			return -1;
		}

		return DataIO.getPlayerTimeData(playerName, data);
	}

	/**
	 * Gets a leaderboard-like map containing all top scores of players for the
	 * given data type. <br>
	 * All data types that are specified at
	 * {@link #getPlayerData(String, String)} can be used, except LASTLOGIN and
	 * LASTVOTE.
	 * 
	 * @param dataType
	 *            Data type to get the leaderboard for.
	 * @return a map containing the names of the players and their values; null
	 *         when data type was invalid.
	 */
	public Map<String, Long> getTopData(String dataType) {
        if (!this.isHooked()) return new HashMap<String, Long>();

		HashMap<String, Long> leaderboard = new HashMap<String, Long>();

		OnTimeAPI.data data = OnTimeAPI.data.valueOf(dataType.toUpperCase());

		if (data == null) {
			return null;
		}

		topData[] topData = DataIO.getTopData(data);

		for (topData td : topData) {
			leaderboard.put(td.getPlayerName(), td.getValue());
		}

		return leaderboard;
	}
}
