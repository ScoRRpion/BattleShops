package mc.alk.bukkit;

import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCPlugin;
import mc.alk.mc.MCServer;
import mc.alk.mc.MCWorld;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitServer extends MCServer {

	@Override
	public APIType getAPIType() {
		return APIType.BUKKIT;
	}

	@Override
	public MCLocation getMCLocation(String world, int x, int y, int z) {
		return new BukkitLocation(world, x, y, z);
	}

	@Override
	public MCWorld getMCWorld(String world) {
		return new BukkitWorld(Bukkit.getWorld(world));
	}

	@Override
	public long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis) {
		return Bukkit.getScheduler().scheduleSyncDelayedTask(
				(JavaPlugin) plugin, runnable, millis / 50);
	}

	@Override
	public MCPlayer getMCPlayer(String name) {
		Player p = Bukkit.getPlayer(name);
		return p == null ? null : new BukkitPlayer(p);
	}

	@Override
	public boolean cancelMCTask(long id) {
		Bukkit.getScheduler().cancelTask((int) id);
		return true;
	}

}
