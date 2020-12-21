package me.staartvin.utils.pluginlibrary.hooks;

import me.staartvin.utils.pluginlibrary.Library;
import nl.lolmewn.stats.BukkitMain;
import nl.lolmewn.stats.Util;
import nl.lolmewn.stats.player.PlayerManager;
import nl.lolmewn.stats.player.StatTimeEntry;
import nl.lolmewn.stats.stat.StatManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.Map;
import java.util.UUID;

/**
 * Stats library,
 * <a href="https://www.spigotmc.org/resources/stats.3638//">link</a>.
 * <p>
 * Date created: 16:30:26 12 aug. 2015
 *
 * @author Staartvin
 */
public class StatsHook extends LibraryHook {

    private BukkitMain stats;

    @Override
    public boolean isHooked() {
        return stats != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.staartvin.plugins.pluginlibrary.hooks.LibraryHook#hook()
     */
    @Override
    public boolean hook() {
        if (!isPluginAvailable(Library.STATS))
            return false;

        stats = (BukkitMain) this.getServer().getPluginManager().getPlugin(Library.STATS.getInternalPluginName());

        return stats != null;
    }

    /**
     * Gets the total amount of blocks that are broken by a player with a
     * certain item id and damage value.
     * <p>
     * You must specify the UUID of the player and the item id of the block.
     * <br>
     * Damage value and worldName are optional. If you don't want to check for a
     * block with a damage value, just use -1. <br>
     * If you want to check on all worlds, use null as worldName.
     *
     * @param uuid        UUID of player.
     * @param id          Item ID to check for.
     * @param damageValue Damage value to check for. (-1 for no damage value)
     * @param worldName   World to check in. Null for global.
     * @return The amount of blocks a player has broken.
     */

    @SuppressWarnings("deprecation")
    public int getBlocksBroken(final UUID uuid, final int id, final int damageValue, final String worldName) {
        if (!isHooked())
            return 0;

        PlayerManager.getInstance().getPlayer(uuid).subscribe(player -> {
            StatManager.getInstance().getStat("Blocks broken").ifPresent(stat -> {

                int value = 0;

                for (StatTimeEntry entry : player.getStats(stat).getEntries()) {
                    Map<String, Object> metadata = entry.getMetadata();

                    // Check world
                    if (worldName != null && metadata.containsKey("world")) {
                        // Not in the world we look for
                        if (!metadata.get("world").equals(worldName))
                            continue;
                    }

                    // Check correct id
                    if (metadata.containsKey("name")) {
                        Material material = Material.matchMaterial(metadata.get("name").toString());

                        if (material.getId() != id)
                            continue;
                    }

                    value += entry.getAmount();
                }
            });
        }, Util::handleError);

        return 0;
    }

    /**
     * Gets the total amount of blocks that are placed by a player with a
     * certain item id and damage value.
     * <p>
     * You must specify the UUID of the player and the item id of the block.
     * <br>
     * Damage value and worldName are optional. If you don't want to check for a
     * block with a damage value, just use -1. <br>
     * If you want to check on all worlds, use null as worldName.
     *
     * @param uuid        UUID of player.
     * @param id          Item ID to check for.
     * @param damageValue Damage value to check for. (-1 for no damage value)
     * @param worldName   World to check in. Null for global.
     * @return The amount of blocks a player has placed.
     */

    @SuppressWarnings("deprecation")
    public int getBlocksPlaced(final UUID uuid, final int id, final int damageValue, final String worldName) {
        if (!isHooked())
            return 0;

//		final Collection<StatEntry> stat = getStatType("Blocks placed", uuid);
//		boolean checkDamageValue = false;
//
//		if (damageValue > 0) {
//			checkDamageValue = true;
//		}
//
//		int value = 0;
//
//		for (StatEntry s : stat) {
//			Map<String, Object> metadata = s.getMetadata();
//
//			// Check world
//			if (worldName != null && metadata.containsKey("world")) {
//				// Not in the world we look for
//				if (!metadata.get("world").equals(worldName))
//					continue;
//			}
//
//			// Check damage value
//			if (checkDamageValue) {
//				if (metadata.containsKey("data")) {
//					if (!metadata.get("data").equals(damageValue))
//						continue;
//				}
//			}
//
//			// Check correct id
//			if (metadata.containsKey("name")) {
//				Material material = Material.matchMaterial(metadata.get("name").toString());
//
//				if (material.getId() != id)
//					continue;
//			}
//
//			value += s.getValue();
//		}

        return 0;
    }

    public EntityType getEntityType(final String entityName) {
        try {
            return EntityType.valueOf(entityName.toUpperCase());
        } catch (final Exception e) {
            return null;
        }
    }

    /**
     * If there is no method for getting specific data, you can use this one.
     * <br>
     * This is just for generic stats. <br>
     * Most stats can be retrieved with this method: <br>
     * <b>Arrows, Beds entered, Buckets emptied, Buckets filled, Commands done,
     * Damage taken, Eggs thrown, Fish caught, Joins, Last join, Last seen,
     * Money, Omnomnom (amount of food eaten), PVP streak, PVP top streak,
     * Playtime, Shears, Teleports, Times kicked, Trades, Votes, Words said,
     * Times changed world, XP gained</b>
     *
     * @param uuid      UUID of the player.
     * @param statName  Name of the stat.
     * @param worldName Name of the world, or null for global.
     * @return
     */
    public int getNormalStat(final UUID uuid, final String statName, final String worldName) {
        if (!isHooked())
            return 0;
//
//		final Collection<StatEntry> stat = getStatType(statName, uuid);
//
//		int value = 0;
//
//		for (StatEntry s : stat) {
//			Map<String, Object> metadata = s.getMetadata();
//
//			if (worldName != null && metadata.containsKey("world")) {
//				// Not in the world we look for
//				if (!metadata.get("world").equals(worldName))
//					continue;
//			}
//
//			value += s.getValue();
//		}

        return 0;
    }

//	/**
//	 * Get the stats of a player, a new stat will be created if it didn't exist
//	 * yet.
//	 *
//	 * @param statName
//	 *            Name of the stat to get
//	 * @param uuid
//	 *            UUID to get the stats of.
//	 * @return Requested stat of the player
//	 */
//	public Collection<StatEntry> getStatType(final String statName, final UUID uuid) {
//
//		if (uuid == null) {
//			return new ArrayList<StatEntry>();
//		}
//
//		StatsHolder holder = stats.getUserManager().getUser(uuid);
//
//		if (holder == null) {
//			this.getPlugin().logMessage("UUID '" + uuid.toString() + "' was not found in Stats database!");
//
//			return new ArrayList<StatEntry>();
//		}
//
//		Stat stat = stats.getStatManager().getStat(statName);
//
//		if (stat == null)
//			throw new IllegalArgumentException("Unknown stat '" + statName + "'!");
//
//		return holder.getStats(stat);
//
//	}

    /**
     * Gets the total amount of all broken blocks. It ignores item id or damage
     * value. <br>
     * If you want to check the amount of broken blocks for a certain type of
     * block or with a damage value, <br>
     * use {@link #getBlocksBroken(UUID, int, int, String)}.
     *
     * @param uuid      UUID of the player.
     * @param worldName Name of the world, or null for global.
     * @return The amount of total blocks broken.
     */
    public int getTotalBlocksBroken(final UUID uuid, final String worldName) {
        if (!isHooked())
            return 0;

        return this.getNormalStat(uuid, "Blocks broken", worldName);
    }

    /**
     * Gets the total amount of blocks a player has moved. <br>
     * You need specify a type of movement: <br>
     * 0: By foot <br>
     * 1: By boat <br>
     * 2: By cart <br>
     * 3: By pig <br>
     * 4: By pig in cart <br>
     * 5: By horse
     *
     * <p>
     * If you want to check on all worlds, use null as worldName.
     *
     * @param uuid      UUID of the player to check
     * @param type      Type of movement
     * @param worldName Name of world, null for global
     * @return The amount of blocks moved by a player
     */
    public int getTotalBlocksMoved(final UUID uuid, final int type, final String worldName) {
        if (!isHooked())
            return 0;
//
//		final String statName = "Move";
//
//		final Collection<StatEntry> stat = getStatType(statName, uuid);
//
//		int value = 0;
//
//		for (StatEntry s : stat) {
//
//			Map<String, Object> metadata = s.getMetadata();
//
//			if (worldName != null && metadata.containsKey("world")) {
//				// Not in the world we look for
//				if (!metadata.get("world").equals(worldName))
//					continue;
//			}
//
//			if (metadata.containsKey("type") && (Integer) metadata.get("type") != type)
//				continue;
//
//			value += s.getValue();
//
//		}
//
//		return value;
        return 0;
    }

    /**
     * Gets the total amount of all placed blocks. It ignores item id or damage
     * value. <br>
     * If you want to check the amount of placed blocks for a certain type of
     * block or with a damage value, <br>
     * use {@link #getBlocksPlaced(UUID, int, int, String)}.
     *
     * @param uuid      UUID of the player.
     * @param worldName Name of the world, or null for global.
     * @return The amount of total blocks placed.
     */
    public int getTotalBlocksPlaced(final UUID uuid, final String worldName) {
        if (!isHooked())
            return 0;

        return this.getNormalStat(uuid, "Blocks placed", worldName);
    }

    /**
     * Gets the amount of mobs a player has killed. <br>
     * You can specify a mob name. If you don't, it will return the total amount
     * of mob kills. <br>
     * A few special mobs are the 'wither_skeleton', 'charged_creeper',
     * 'spider_jockey', 'chicken_jockey' and 'elder_guardian'.
     *
     * @param uuid      UUID of the player.
     * @param mobName   Name of the mob, or null for all mobs.
     * @param worldName Name of the world, or null for global.
     * @return The amount of mobs killed a player has killed.
     */
    public int getMobsKilled(final UUID uuid, final String mobName, final String worldName) {
        if (!isHooked())
            return 0;
//
//		final String statName = "Kill";
//
//		// Mob type
//		String type = null;
//
//		if (mobName != null && !mobName.equals("")) {
//
//			if (mobName.equalsIgnoreCase("wither_skeleton")) {
//				return this.getSpecialMobsKilled(uuid, "WITHER SKELETON", worldName);
//			} else if (mobName.equalsIgnoreCase("charged_creeper")) {
//				return this.getSpecialMobsKilled(uuid, "POWERED CREEPER", worldName);
//			} else if (mobName.equalsIgnoreCase("spider_jockey")) {
//				return this.getSpecialMobsKilled(uuid, "SPIDER JOCKEY", worldName);
//			} else if (mobName.equalsIgnoreCase("chicken_jockey")) {
//				return this.getSpecialMobsKilled(uuid, "CHICKEN JOCKEY", worldName);
//			} else if (mobName.equalsIgnoreCase("killer_rabbit")) {
//				return this.getSpecialMobsKilled(uuid, "KILLER RABBIT", worldName);
//			} else if (mobName.equalsIgnoreCase("elder_guardian")) {
//				return this.getSpecialMobsKilled(uuid, "ELDER GUARDIAN", worldName);
//			}
//
//			type = EntityType.valueOf(mobName.toUpperCase().replaceAll(" ", "_")).toString();
//		}
//
//		final Collection<StatEntry> stat = getStatType(statName, uuid);
//
//		int value = 0;
//
//		for (StatEntry s : stat) {
//
//			Map<String, Object> metadata = s.getMetadata();
//
//			if (worldName != null && metadata.containsKey("world")) {
//				// Not in the world we look for
//				if (!metadata.get("world").equals(worldName))
//					continue;
//			}
//
//			if (type != null && metadata.containsKey("entityType") && !metadata.get("entityType").equals(type))
//				continue;
//
//			// If no type was given (so any mob can be killed, exclude 'player'
//			// kills, as most admins don't see players as a real mob).
//			if (type == null && metadata.containsKey("entityType") && metadata.get("entityType").equals("PLAYER"))
//				continue;
//
//			value += s.getValue();
//
//		}

        return 0;
    }

    private int getSpecialMobsKilled(UUID uuid, String mobName, String worldName) {
        if (!isHooked())
            return 0;

//		final String statName = MobKilledStat.statName;
//
//		final Collection<StatEntry> stat = getStatType(statName, uuid);
//
//		int value = 0;
//
//		String extraType = mobName.split(" ")[0].trim();
//		String entityType = mobName.split(" ")[1].trim();
//
//		for (StatEntry s : stat) {
//
//			Map<String, Object> metadata = s.getMetadata();
//
//			if (worldName != null && metadata.containsKey("world")) {
//				// Not in the world we look for
//				if (!metadata.get("world").equals(worldName))
//					continue;
//			}
//
//			if (metadata.containsKey("entityType") && !metadata.get("entityType").equals(entityType))
//				continue;
//
//			if (metadata.containsKey("extraType") && !metadata.get("extraType").equals(extraType))
//				continue;
//
//			value += s.getValue();
//
//		}

        return 0;
    }

    /**
     * Gets the amount of players a player has killed.
     *
     * @param uuid      UUID of the player.
     * @param worldName Name of the world, or null for global.
     * @return The amount of players a player has killed.
     */
    public int getPlayersKilled(UUID uuid, String worldName) {
        if (!this.isHooked()) return -1;
        return this.getMobsKilled(uuid, "player", worldName);
    }

    /**
     * Gets the amount of specific food a player has eaten. <br>
     * If you only want to know about the total amount of food eaten, use
     * {@link #getNormalStat(UUID, String, String)} with statName as 'Omnomnom'
     * instead.
     * <p>
     * A list of usable food types can be found
     * <a href="https://github.com/Armarr/Autorank-2/wiki/Food-types">here</a>.
     *
     * @param uuid      UUID of the player.
     * @param worldName Name of the world, or null for global.
     * @param foodType  Type of food.
     * @return The amount of specific food eaten by a player.
     */
    public int getFoodEaten(final UUID uuid, final String worldName, String foodType) {
        if (!isHooked())
            return 0;
//
//		String statName = FoodEatenStat.statName;
//
//		final Collection<StatEntry> stat = getStatType(statName, uuid);
//
//		int value = 0;
//
//		for (StatEntry s : stat) {
//			Map<String, Object> metadata = s.getMetadata();
//
//			if (worldName != null && metadata.containsKey("world")) {
//				// Not in the world we look for
//				if (!metadata.get("world").equals(worldName))
//					continue;
//			}
//
//			if (foodType != null && metadata.containsKey("foodType")) {
//				if (!metadata.get("foodType").equals(foodType))
//					continue;
//			}
//
//			value += s.getValue();
//		}

        return 0;
    }

//
//    /**
//     * Get a list of uuids that Stats has logged.
//     *
//     * @return a list of uuids that represent players that Stats has logged in
//     *         its database.
//     */
//    public List<UUID> getLoggedPlayers() {
//        if (!this.isAvailable()) return new ArrayList<>();
//
//        List<UUID> playerNames = new ArrayList<>();
//
//        int count = 0;
//
//        for (OfflinePlayer player : this.getServer().getOfflinePlayers()) {
//            StatsHolder user = this.getStatsHolder(player.getUniqueId());
//            count++;
//
//            if (count % 1000 == 0) {
//                System.out.println("Loaded " + count);
//            }
//
//            if (count > 10000) {
//                break;
//            }
//
//            if (user == null) {
//                try {
//                    user = stats.getUserManager().loadUser(player.getUniqueId(), stats.getStatManager());
//				} catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                playerNames.add(user.getUuid());
//            } else {
//                playerNames.add(player.getUniqueId());
//            }
//
//            getPlugin().requestTimes.put(user.getUuid(), System.currentTimeMillis());
//
//        }
//
//        return playerNames;
//    }
//
//	public void addStat(Stat stat) {
//        if (!this.isAvailable()) return;
//        stats.getStatManager().addStat(stat);
//	}
//
//	public Stat getStat(String statName) {
//        if (!this.isAvailable()) return null;
//
//		return stats.getStatManager().getStat(statName);
//	}
//
//	public StatsHolder getStatsHolder(UUID uuid) {
//        if (!this.isAvailable()) return null;
//        return stats.getUserManager().getUser(uuid);
//	}

}
