package me.Theverat.simpletrail;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Playerlistener implements Listener {

	// instance of the main plugin class
	private final simpletrail mainpluginclass = new simpletrail();

	@EventHandler
	public void playermoveevent(PlayerMoveEvent event) {

		Player p = event.getPlayer();
		Block StandsOn = p.getLocation().subtract(0, 1, 0).getBlock();
		Block AboveStandsOn = p.getLocation().getBlock();
		double randomvalue = Math.random();

		if (canPlayerBuild(p) == true) {

			// if the player is standing on grass, replace it with dirt
			if (StandsOn.getTypeId() == 2) {
				if (randomvalue >= 0.8) { // random factor
					StandsOn.setTypeId(3);
				}
			}

			// if the block above the one the player stands on is weed or a
			// flower, replace it with air
			if (AboveStandsOn.getTypeId() == 31
					|| AboveStandsOn.getTypeId() == 37
					|| AboveStandsOn.getTypeId() == 38) {
				if (randomvalue >= 0.8) { // random factor
					AboveStandsOn.setTypeId(0);
				}
			}
		}
	}

	private boolean canPlayerBuild(Player player) {
		// Make sure that a WorldGuard instance was found
		if (mainpluginclass.worldGuard != null) {
			// Get the players location
			Location playerloc = player.getLocation().subtract(0, 1, 0);

			if (mainpluginclass.worldGuard.canBuild(player, playerloc))
				return true;
			else
				return false;
		} else if (mainpluginclass.worldGuard == null){
			player.sendMessage("SimpleTrail debug: worldguard not found!");
		}
		return true;
	}
}