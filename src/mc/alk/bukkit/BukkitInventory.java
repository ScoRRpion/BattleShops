package mc.alk.bukkit;

import java.util.ArrayList;

import mc.alk.bukkit.util.BukkitInventoryUtil;
import mc.alk.mc.MCInventory;
import mc.alk.mc.MCItemStack;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BukkitInventory implements MCInventory {
	Inventory inventory;

	public BukkitInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public void addItem(MCItemStack... itemStacks) {
		for (MCItemStack item : itemStacks) {
			this.addItem(item);
		}
	}

	public void addItem(MCItemStack itemStack) {
		if (itemStack != null && itemStack.getType() == 0) {
			return;
		}

		BukkitInventory.addItemToInventory(this.inventory,
				((BukkitItemStack) itemStack).getItem(),
				itemStack.getQuantity());
	}

	// /Adds item to inventory
	public static void addItemToInventory(Inventory inv, ItemStack is, int left) {
		int maxStackSize = is.getType().getMaxStackSize();
		if (left <= maxStackSize) {
			is.setAmount(left);
			inv.addItem(is);
			return;
		}

		if (maxStackSize != 64) {
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			for (int i = 0; i < Math.ceil(left / maxStackSize); i++) {
				if (left < maxStackSize) {
					is.setAmount(left);
					items.add(is);
					return;
				} else {
					is.setAmount(maxStackSize);
					items.add(is);
				}
			}
			Object[] iArray = items.toArray();
			for (Object o : iArray) {
				inv.addItem((ItemStack) o);
			}
		} else {
			inv.addItem(is);
		}
	}

	@Override
	public void removeItem(MCItemStack itemStack) {
		BukkitInventoryUtil.removeItem(this.inventory,
				((BukkitItemStack) itemStack).getItem());
	}

	@Override
	public int getItemAmount(MCItemStack itemStack) {
		return BukkitInventoryUtil.getItemAmountFromInventory(this.inventory,
				((BukkitItemStack) itemStack).getItem());
	}

	@Override
	public boolean canFit(MCItemStack itemStack) {
		int space = BukkitInventoryUtil.amountFreeSpace(this.inventory,
				((BukkitItemStack) itemStack).getItem(),
				itemStack.getQuantity());
		return space >= 0;
	}

	@Override
	public int freeSpaceAfter(MCItemStack itemStack) {
		return BukkitInventoryUtil.amountFreeSpace(this.inventory,
				((BukkitItemStack) itemStack).getItem(),
				itemStack.getQuantity());
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public MCItemStack[] getContents() {
		ItemStack[] is = this.inventory.getContents();
		MCItemStack[] items = new MCItemStack[is.length];
		for (int i = 0; i < is.length; i++) {
			items[i] = new BukkitItemStack(is[i]);
		}
		return items;
	}

}
