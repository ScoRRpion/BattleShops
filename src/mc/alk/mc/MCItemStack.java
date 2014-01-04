package mc.alk.mc;

import java.util.Map;

public interface MCItemStack {
	public void setType(int id);

	public int getType();

	public void setDataValue(short value);

	public short getDataValue();

	public void setQuantity(int quantity);

	public int getQuantity();

	public Map<Integer, Integer> getEnchantments();

	public void addEnchantment(int id, int level);

	public boolean hasMetaData();

	public String getCommonName();

	public MCItemStack clone();

	public int isSpecial();
}
