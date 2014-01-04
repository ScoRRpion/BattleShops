package mc.alk.bukkit;

import org.bukkit.inventory.PlayerInventory;

public class BukkitPlayerInventory extends BukkitInventory {
	PlayerInventory inventory;

	public BukkitPlayerInventory(PlayerInventory inventory) {
		super(inventory);
		this.inventory = inventory;
	}

}
