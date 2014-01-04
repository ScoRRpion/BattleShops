package mc.alk.mc.factories;

import mc.alk.bukkit.factories.BukkitItemFactory;
import mc.alk.mc.MCItemStack;
import mc.alk.mc.MCServer;

public abstract class ItemFactory {
	public abstract MCItemStack createMCItem(int id, short value, int quantity);

	public abstract MCItemStack createMCItem(String text);

	public static MCItemStack createItem(int id, short value, int quantity) {
		switch (MCServer.getType()) {
		case TEST:
		case BUKKIT:
			return new BukkitItemFactory().createMCItem(id, value, quantity);
		default:
			return null;
		}
	}

	public static MCItemStack createItem(String text) {
		switch (MCServer.getType()) {
		case TEST:
		case BUKKIT:
			return new BukkitItemFactory().createMCItem(text);
		default:
			return null;
		}
	}
}
