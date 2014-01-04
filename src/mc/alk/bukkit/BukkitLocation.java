package mc.alk.bukkit;

import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BukkitLocation implements MCLocation {
	Location loc;

	public BukkitLocation(Location loc) {
		this.loc = loc;
	}

	public BukkitLocation(String world, int x, int y, int z) {
		this.loc = new Location(Bukkit.getWorld(world), x, y, z);
	}

	@Override
	public MCWorld getWorld() {
		return new BukkitWorld(this.loc.getWorld());
	}

	@Override
	public int getBlockX() {
		return this.loc.getBlockX();
	}

	@Override
	public int getBlockY() {
		return this.loc.getBlockY();
	}

	@Override
	public int getBlockZ() {
		return this.loc.getBlockZ();
	}

	public Location getLocation() {
		return this.loc;
	}

	@Override
	public String toString() {
		return this.loc == null ? "[Location null]" : "[Location "
				+ this.loc.getWorld() + " " + this.loc.getBlockX() + ":"
				+ this.loc.getBlockY() + ":" + this.loc.getBlockZ() + "]";
	}
}
