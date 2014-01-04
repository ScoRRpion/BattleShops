package mc.alk.mc;

import mc.alk.bukkit.MCCommandSender;

public interface MCPlayer extends MCCommandSender {

	public MCWorld getWorld();

	public String getName();

	public String getDiplayName();

	public MCInventory getInventory();

	public void updateInventory();
}
