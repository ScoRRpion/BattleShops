package mc.alk.mc;

import mc.alk.bukkit.BukkitWorld;

import org.bukkit.Location;

public class StringLocation implements MCLocation {
	String world;
	int x;
	int y;
	int z;

	public StringLocation(String world, Integer x, Integer y, Integer z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getWorldName() {
		return this.world;
	}

	@Override
	public MCWorld getWorld() {
		return null;
	}

	@Override
	public int getBlockX() {
		return this.x;
	}

	@Override
	public int getBlockY() {
		return this.y;
	}

	@Override
	public int getBlockZ() {
		return this.z;
	}
}
