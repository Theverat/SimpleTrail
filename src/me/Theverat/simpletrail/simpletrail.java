package me.Theverat.simpletrail;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.Plugin;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class simpletrail extends JavaPlugin {

	public WorldGuardPlugin worldGuard = null;
	public PreciousStones preciousStones = null;

	@Override
	public void onEnable() {
		//Get the PreciousStones instance
		preciousStones = getPreciousStones();
		
		// Get the WorldGuard instance
		worldGuard = getWorldGuard();

		Playerlistener pl = new Playerlistener();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(pl, this);
	}

	@Override
	public void onDisable() {

	}
	
	/* THERE'S A BUG IN THE FOLLOWING CODE:
	 * SimpleTrail nether finds WorldGuard/PreciousStones */

	//WorldGuard support:
	// from WorldGuard wiki: http://wiki.sk89q.com/wiki/WorldGuard/Regions/API
	private WorldGuardPlugin getWorldGuard() {
		Plugin wgplugin = getServer().getPluginManager()
				.getPlugin("WorldGuard");

		// WorldGuard may not be loaded
		if (wgplugin == null || !(wgplugin instanceof WorldGuardPlugin)) {
			return null;
		}
		return (WorldGuardPlugin) wgplugin;
	}
	
	//PreciousStones support:
	private PreciousStones getPreciousStones() {
		Plugin psplugin = getServer().getPluginManager()
				.getPlugin("PreciousStones");

		// PreciousStones may not be loaded
		if (psplugin == null || !(psplugin instanceof PreciousStones)) {
			return null;
		}
		return (PreciousStones) psplugin;
	}
}
