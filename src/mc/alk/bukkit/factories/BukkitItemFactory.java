package mc.alk.bukkit.factories;

import mc.alk.bukkit.BukkitItemStack;
import mc.alk.bukkit.util.BukkitInventoryUtil;
import mc.alk.mc.MCItemStack;
import mc.alk.mc.factories.ItemFactory;

import org.bukkit.inventory.ItemStack;

public class BukkitItemFactory extends ItemFactory {

	@Override
	public MCItemStack createMCItem(int id, short value, int quantity) {
		return new BukkitItemStack(new ItemStack(id, quantity, value));
	}

	@Override
	public MCItemStack createMCItem(String text) {
		ItemStack item = BukkitInventoryUtil.getItemStack(text);
		return item == null ? null : new BukkitItemStack(item);
	}
}
