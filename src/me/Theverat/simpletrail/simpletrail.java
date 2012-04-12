package me.Theverat.simpletrail;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class simpletrail extends JavaPlugin {

	public WorldGuardPlugin worldGuard = null;

	@Override
	public void onEnable() {
		// Get the WorldGuard instance
		worldGuard = getWorldGuard();

		Playerlistener pl = new Playerlistener();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(pl, this);
	}

	@Override
	public void onDisable() {

	}

	/* THERE'S A BUG IN THE FOLLOWING CODE - SimpleTrail nether finds WorldGuard */

	// from WorldGuard wiki: http://wiki.sk89q.com/wiki/WorldGuard/Regions/API
	private WorldGuardPlugin getWorldGuard() {
		Plugin plugin = this.getServer().getPluginManager()
				.getPlugin("WorldGuard");

		// WorldGuard may not be loaded
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}
}
