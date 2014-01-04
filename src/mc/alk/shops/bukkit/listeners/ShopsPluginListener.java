package mc.alk.shops.bukkit.listeners;

import mc.alk.shops.Defaults;
import mc.alk.shops.bukkit.controllers.BukkitPermController;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

/**
 * 
 * @author alkarin
 * 
 */
public class ShopsPluginListener implements Listener {
	public void loadAll() {
		loadWorldGuard();
		loadGriefPrevention();
	}

	@EventHandler
	public void onPluginEnable(PluginEnableEvent event) {
		if (Defaults.DEBUG_TRACE)
			System.out.println("onPluginEnable");
		if (event.getPlugin().getName().equalsIgnoreCase("WorldGuard")) {
			loadWorldGuard();
		}
		if (event.getPlugin().getName().equalsIgnoreCase("GriefPrevention")) {
			loadGriefPrevention();
		}
	}

	public void loadWorldGuard() {
		// /World Guard
		if (BukkitPermController.worldGuard == null) {
			Plugin wg = Bukkit.getPluginManager().getPlugin("WorldGuard");

			if (wg != null) {
				BukkitPermController.worldGuard = (WorldGuardPlugin) wg;
				PluginDescriptionFile pDesc = wg.getDescription();
				System.out.println("[BattleShops] " + pDesc.getName()
						+ " version " + pDesc.getVersion() + " loaded.");
			}
		}
	}

	public void loadGriefPrevention() {
		// /Grief Prevention
		if (BukkitPermController.griefPrevention == null) {
			Plugin wg = Bukkit.getPluginManager().getPlugin("GriefPrevention");

			if (wg != null) {
				BukkitPermController.griefPrevention = (GriefPrevention) wg;
				PluginDescriptionFile pDesc = wg.getDescription();
				System.out.println("[BattleShops] " + pDesc.getName()
						+ " version " + pDesc.getVersion() + " loaded.");
			}
		}
	}

}
