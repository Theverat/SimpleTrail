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
		//Block StandsOn = p.getLocation().subtract(0, 1, 0).getBlock();
		Block AboveStandsOn = p.getLocation().getBlock();
		double randomvalue = Math.random();

		if (WGcanPlayerBuild(p) == true) {
			
			if(PScanPlayerBuild(p, AboveStandsOn) == true) {
				
			/*--------------------------------------------------------------
			 * FEATURE REMOVED: if the player is standing on grass, replace it with dirt
			
			if (StandsOn.getTypeId() == 2) {
				if (randomvalue >= 0.8) { // random factor
					StandsOn.setTypeId(3);
				}
			}
			--------------------------------------------------------------*/

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
	}

	private boolean WGcanPlayerBuild (Player player) {
		if (mainpluginclass.worldGuard != null) { // Make sure that a WorldGuard instance was found
			// Get the players location
			Location playerloc = player.getLocation().subtract(0, 1, 0);

			if (mainpluginclass.worldGuard.canBuild(player, playerloc))
				return true;
			else
				return false;
		} else if (mainpluginclass.worldGuard == null) { //what to do if no WorldGuard instance was found
			player.sendMessage("SimpleTrail debug: worldguard not found!"); //DEBUG-message -> delete when WG bug is resolved
		}
		return true; //the plugin also works without WorldGuard, thus the "true" return even if no WorldGuard was found
	}
	
	private boolean PScanPlayerBuild (Player player, Block block)
	{
		if (mainpluginclass.preciousStones != null) {
			if (mainpluginclass.preciousStones.getForceFieldManager().isAllowed(
					mainpluginclass.preciousStones.getForceFieldManager().getField(block), player.getName()) == false) {
				return false;
			}
			else if (mainpluginclass.preciousStones.getForceFieldManager().getOwner(block) == player.getName()){
				return true;
			}
			return true;
		}
		else if (mainpluginclass.preciousStones == null) {
			player.sendMessage("SimpleTrail debug: PreciousStones not found!");
		}
		return true; //the plugin also works without PreciousStones, thus the "true" return even if no PS was found
	}
}