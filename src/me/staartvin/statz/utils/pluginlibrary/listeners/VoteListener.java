package me.staartvin.utils.pluginlibrary.listeners;

import com.vexsoftware.votifier.model.VotifierEvent;
import me.staartvin.utils.pluginlibrary.events.PlayerVotedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Listen for votes for NuVotifier and Votifier and create our own event to let developers know a vote happened.
 */
public class VoteListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onVote(final VotifierEvent event) {

        String userName = event.getVote().getUsername();

        // Get player
        Player player = Bukkit.getServer().getPlayer(userName);

        // Player is not online, so ignore it.
        if (player == null) {
            // Player is null, so we never show that the player voted.
            return;
        }

        // Create our own event to let developers know that a player has voted.
        PlayerVotedEvent playerVotedEvent = new PlayerVotedEvent(player);

        // Call event!
        Bukkit.getPluginManager().callEvent(playerVotedEvent);
    }
}
