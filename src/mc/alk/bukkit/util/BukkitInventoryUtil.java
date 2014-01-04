package mc.alk.bukkit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mc.alk.mc.util.MCInventoryUtil;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * 
 * @author alkarin
 * 
 */
public class BukkitInventoryUtil implements MCInventoryUtil {
	public static HashMap<String, ItemStack> commonToStack = new HashMap<String, ItemStack>();
	public static HashMap<String, String> idToCommon = new HashMap<String, String>();
	public static final HashMap<String, ItemStack> itemNames = new HashMap<String, ItemStack>();
	static {
		BukkitInventoryUtil.itemNames.put("light_gray_wool", new ItemStack(
				Material.WOOL.getId(), 1, (short) 8));
		BukkitInventoryUtil.itemNames.put("stone_brick", new ItemStack(
				Material.SMOOTH_BRICK, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("mossy_stone", new ItemStack(
				Material.SMOOTH_BRICK, 1, (short) 1));
		BukkitInventoryUtil.itemNames.put("mossy_smooth", new ItemStack(
				Material.SMOOTH_BRICK, 1, (short) 1));
		BukkitInventoryUtil.itemNames.put("cracked_stone", new ItemStack(
				Material.SMOOTH_BRICK, 1, (short) 2));
		BukkitInventoryUtil.itemNames.put("piston", new ItemStack(
				Material.PISTON_BASE, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("sticky_piston", new ItemStack(
				Material.PISTON_STICKY_BASE, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("long_grass", new ItemStack(
				Material.GRASS, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("fern", new ItemStack(Material.GRASS,
				1, (short) 0));
		BukkitInventoryUtil.itemNames.put("mycelium", new ItemStack(
				Material.MYCEL, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("nether_wart", new ItemStack(
				Material.NETHER_STALK, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("redstone_lamp", new ItemStack(
				Material.REDSTONE_LAMP_OFF, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("redstone_torch", new ItemStack(
				Material.REDSTONE_TORCH_ON, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("carrot", new ItemStack(
				Material.CARROT_ITEM, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("carrots", new ItemStack(
				Material.CARROT, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("carrot_seed", new ItemStack(
				Material.CARROT, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("potato", new ItemStack(
				Material.POTATO_ITEM, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("potatoes", new ItemStack(
				Material.POTATO, 1, (short) 0));
		BukkitInventoryUtil.itemNames.put("potato_seed", new ItemStack(
				Material.POTATO, 1, (short) 0));
	}

	static {
		for (Material m : Material.values()) {
			// System.out.println(" data = " + m.getData());
			for (int i = 0; i < 64; i++) {
				try {
					String s = m.getNewData((byte) i).toString();
					s = s.replaceAll("null", ""); // / get rid of null
					s = s.replaceAll("generic", ""); // / get rid of generic
					s = s.replaceAll("\\(\\d+\\)$", "");
					s = s.replaceAll("durability.*", "");
					s = s.replaceAll(" up ", "");
					s = s.replaceAll("^\\s+", "").replaceAll("\\s+$", ""); // /remove
																			// left
																			// and
																			// right
																			// whitespace
					s = s.replaceAll(" ", "_");
					s = s.toLowerCase();
					if (s.split("_").length > 3 || s.contains("(")
							|| s.contains(")")) { // / Some strange blocks
						break;
					}
					if (BukkitInventoryUtil.commonToStack.containsKey(s)) { // /
																			// already
																			// have
																			// gone
																			// through
																			// all
																			// of
																			// these
						break;
					}
					ItemStack is = m.getNewData((byte) i).toItemStack();
					// System.out.println("    data = <" + s +">  " +
					// printItemStack(is));
					is.setAmount(1);
					BukkitInventoryUtil.commonToStack.put(s, is);
					s = s.replaceAll("_", " ");
					BukkitInventoryUtil.idToCommon.put(is.getTypeId() + ":"
							+ is.getDurability(), s);
				} catch (Exception e) {
					// / well whoops.. no data for that byte
					break;
				}
			}
		}
	}

	public static int getItemAmountFromInventory(Inventory inv, ItemStack is) {
		return BukkitInventoryUtil.getItemAmount(inv.getContents(), is);
	}

	public static boolean sameItem(final ItemStack is1, final ItemStack is2,
			boolean checkDura) {
		if (is1 == null || is2 == null) {
			return false;
		}
		if (is1.getType() != is2.getType()) {
			return false;
		}
		if (checkDura
				&& (is1.getDurability() != -1 && is1.getDurability() != is2
						.getDurability())) {
			return false;
		}
		final Map<Enchantment, Integer> e1 = is1.getEnchantments();
		final Map<Enchantment, Integer> e2 = is2.getEnchantments();
		return e1.size() == e2.size();
	}

	public static int getItemAmount(ItemStack[] items, ItemStack is) {
		boolean checkDurability = true;
		int count = 0;
		for (ItemStack item : items) {
			if (BukkitInventoryUtil.sameItem(item, is, checkDurability)
					&& item.getAmount() > 0) {
				count += item.getAmount();
			}
		}
		return count;
	}

	// / Checks if there is enough free space in inventory
	public static boolean checkFreeSpace(Chest chest, ItemStack is, int left) {
		Inventory inv = chest.getInventory();
		return BukkitInventoryUtil.checkFreeSpace(inv, is, left);
	}

	public static boolean checkFreeSpace(Inventory inv, ItemStack is, int left) {
		return BukkitInventoryUtil.checkFreeSpace(inv.getContents(), is, left);
	}

	public static boolean checkFreeSpace(ItemStack[] contents, ItemStack is,
			int left) {
		int maxStack = is.getType().getMaxStackSize();
		for (ItemStack curitem : contents) {
			if (left <= 0) {
				return true;
			}
			if (curitem == null || curitem.getType() == Material.AIR) {
				left = left - maxStack;
				continue;
			}
			if (!BukkitInventoryUtil.sameItem(curitem, is, true)) {
				continue;
			}

			int amount = curitem.getAmount();
			if (amount < maxStack) {
				left = left - (maxStack - amount);
			}
		}
		return left <= 0;
	}

	public static int amountFreeSpace(ItemStack[] contents, ItemStack is,
			int left) {
		int maxStack = is.getType().getMaxStackSize();
		for (ItemStack curitem : contents) {
			if (curitem == null) {
				left = left - maxStack;
				continue;
			}
			if (!BukkitInventoryUtil.sameItem(curitem, is, true)) {
				continue;
			}
			int amount = curitem.getAmount();
			if (amount < maxStack) {
				left = left - (maxStack - amount);
			}
		}
		return -left;
	}

	public static int amountFreeSpace(Chest chest, ItemStack is, int left) {
		Inventory inv = chest.getInventory();
		return BukkitInventoryUtil.amountFreeSpace(inv, is, left);
	}

	// Checks if there is enough free space in inventory
	public static int amountFreeSpace(Inventory inv, ItemStack is, int left) {
		return BukkitInventoryUtil.amountFreeSpace(inv.getContents(), is, left);
	}

	@SuppressWarnings("deprecation")
	public static void addItemToInventory(Player player, ItemStack itemStack,
			int stockAmount) {
		BukkitInventoryUtil.addItemToInventory(player.getInventory(),
				itemStack, stockAmount);
		player.updateInventory();
	}

	public static void addItemToInventory(Chest chest, ItemStack is, int left) {
		BukkitInventoryUtil.addItemToInventory(chest.getInventory(), is, left);
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

	public static int first(Inventory inv, ItemStack is1) {
		if (is1 == null) {
			return -1;
		}
		ItemStack[] inventory = inv.getContents();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack is2 = inventory[i];
			if (is2 == null) {
				continue;
			}
			if (BukkitInventoryUtil.sameItem(is1, is2, true)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This is nearly a direct copy of the removeItem from CraftBukkit The
	 * difference is my ItemStack == ItemStack comparison (found in first())
	 * there I change it to go by itemid and datavalue as opposed to itemid and
	 * quantity
	 * 
	 * @param inv
	 * @param items
	 * @return
	 */
	public static HashMap<Integer, ItemStack> removeItem(Inventory inv,
			ItemStack... items) {
		HashMap<Integer, ItemStack> leftover = new HashMap<Integer, ItemStack>();

		for (int i = 0; i < items.length; i++) {
			ItemStack item = items[i];
			int toDelete = item.getAmount();

			while (true) {
				// System.out.println("inv= " + inv + "   " + items.length +
				// "    item=" + item);
				int first = BukkitInventoryUtil.first(inv, item);
				// System.out.println("first= " + first);

				// Drat! we don't have this type in the inventory
				if (first == -1) {
					item.setAmount(toDelete);
					leftover.put(i, item);
					break;
				} else {
					ItemStack itemStack = inv.getItem(first);
					int amount = itemStack.getAmount();

					if (amount <= toDelete) {
						toDelete -= amount;
						// clear the slot, all used up
						inv.setItem(first, null);
					} else {
						// split the stack and store
						itemStack.setAmount(amount - toDelete);
						inv.setItem(first, itemStack);
						toDelete = 0;
					}
				}

				// Bail when done
				if (toDelete <= 0) {
					break;
				}
			}
		}
		return leftover;
	}

	public static String printItemStack(ItemStack is) {
		StringBuilder sb = new StringBuilder("[ItemStack] " + is.getTypeId()
				+ ":" + is.getAmount() + " dura=" + is.getDurability());
		if (is.getData() != null) {
			sb.append(" data=" + is.getData() + "  d.itemType="
					+ is.getData().getItemType() + " d.itemTypeId="
					+ is.getData().getItemTypeId() + " d.data="
					+ is.getData().getData());
		} else {
			sb.append(" data=null");
		}
		return sb.toString();
	}

	/**
	 * Return a item stack from a given string
	 * 
	 * @param name
	 * @return
	 */
	public static ItemStack getItemStack(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}
		name = name.replace(" ", "_");
		name = name.replace(";", ":");
		name = BukkitInventoryUtil.decolorChat(name);
		name = name.toLowerCase();

		String split[] = name.split(":");
		short dataValue = 0;
		if (split.length > 1 && BukkitInventoryUtil.isInt(split[1])) {
			int i = Integer.valueOf(split[1]);
			dataValue = (short) i;
			name = split[0];
		}

		// / Check our prenamed items first
		ItemStack is = BukkitInventoryUtil.itemNames.get(name);
		if (is != null) {
			return is;
		}

		Material mat = Material.matchMaterial(name);
		if (mat != null && mat != Material.AIR) {
			return new ItemStack(mat.getId(), 1, dataValue);
		}
		// / Try to get from our generic list
		is = BukkitInventoryUtil.commonToStack.get(name);
		if (is == null) {
			// / look for first matching item
			for (String itemName : BukkitInventoryUtil.commonToStack.keySet()) {
				int index = itemName.indexOf(name, 0);
				if (index != -1 && index == 0) {
					is = BukkitInventoryUtil.commonToStack.get(itemName);
					return is;
				}
			}
		} else {
			is.setAmount(1);
		}
		return is;
	}

	public static boolean isInt(String i) {
		try {
			Integer.parseInt(i);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String decolorChat(String string) {
		// / Remove all the color codes, first the user defined &[0-9a-fA-F]
		string = string.replaceAll("&[0-9a-fA-F]", "");
		// / Remove the server color codes
		string = ChatColor.stripColor(string);
		return string;
	}

	// public static String getCommonName(ItemStack is) {
	// int id = is.getTypeId();
	// int datavalue = is.getDurability();
	// if (datavalue > 0){
	// return Material.getMaterial(id).toString() + ":" + datavalue;
	// }
	// return Material.getMaterial(id).toString();
	// }

	public static String getCommonName(ItemStack is) {
		int id = is.getTypeId();
		short datavalue = is.getDurability();

		// System.out.println(id + "   " + datavalue)

		String iname = "";
		try {
			if (datavalue > 0) {
				iname = Material.getMaterial(id).toString() + ":" + datavalue;
			}
			String idkey = id + ":" + datavalue;

			String cname = BukkitInventoryUtil.idToCommon.get(idkey);
			if (cname != null) {
				return cname;
			}
			iname = Material.getMaterial(id).toString().toLowerCase()
					+ " durability(" + datavalue + ")";
		} catch (Exception e) {
			System.err.println("Error getting commonName id=" + id
					+ "   iname=" + iname + "   datavalue=" + datavalue);
			e.printStackTrace();
		}
		return iname;
	}

	public static String getCustomName(ItemStack item) {
		ItemMeta im = item.getItemMeta();
		if (im == null) {
			return item.getType().name().toLowerCase();
		}
		String displayName = im.getDisplayName();
		return displayName == null || displayName.isEmpty() ? item.getType()
				.name().toLowerCase() : displayName;
	}

	public static ItemStack getItemStack(int id, short datavalue) {
		return new ItemStack(id, 1, datavalue);
	}

}
