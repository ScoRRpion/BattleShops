package mc.alk.bukkit;

import java.util.HashMap;
import java.util.Map;

import mc.alk.bukkit.util.BukkitInventoryUtil;
import mc.alk.mc.MCItemStack;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class BukkitItemStack implements MCItemStack {
	ItemStack itemStack;

	public BukkitItemStack(ItemStack itemStack) {
		this.itemStack = itemStack == null ? new ItemStack(0) : itemStack;
	}

	@Override
	public void setType(int id) {
		this.itemStack.setTypeId(id);
	}

	@Override
	public int getType() {
		return this.itemStack.getTypeId();
	}

	@Override
	public void setDataValue(short value) {
		this.itemStack.setDurability(value);
	}

	@Override
	public short getDataValue() {
		return this.itemStack.getDurability();
	}

	@Override
	public void setQuantity(int quantity) {
		this.itemStack.setAmount(quantity);
	}

	@Override
	public int getQuantity() {
		return this.itemStack.getAmount();
	}

	@Override
	public Map<Integer, Integer> getEnchantments() {
		Map<Integer, Integer> encs = new HashMap<Integer, Integer>();
		for (Map.Entry<Enchantment, Integer> entry : this.itemStack
				.getEnchantments().entrySet()) {
			encs.put(entry.getKey().getId(), entry.getValue());
		}
		return encs;
	}

	@Override
	public boolean hasMetaData() {
		return this.itemStack.hasItemMeta();
	}

	@Override
	public String getCommonName() {
		return BukkitInventoryUtil.getCommonName(this.itemStack);
	}

	@Override
	public MCItemStack clone() {
		return new BukkitItemStack(this.itemStack.clone());
	}

	public ItemStack getItem() {
		return this.itemStack;
	}

	@Override
	public String toString() {
		return this.itemStack != null ? "[" + this.itemStack.getType() + ":"
				+ this.itemStack.getDurability() + " q=" + this.getQuantity()
				+ "]" : "null";
	}

	public void addEnchantment(int id, int level) {
		this.itemStack.addEnchantment(Enchantment.getById(id), level);
	}

	public void addEnchantment(Enchantment enc, int level) {
		this.itemStack.addEnchantment(enc, level);
	}

	@Override
	public int isSpecial() {
		int special = 0;
		// ItemMeta im = itemStack.getItemMeta();
		// Map<Enchantment,Integer> map = itemStack.getEnchantments();
		// if (map != null){
		// for (Entry<Enchantment,Integer> entry : map.entrySet()){
		// // entry.getKey().getId()
		// }
		// }

		return special;
	}

}
