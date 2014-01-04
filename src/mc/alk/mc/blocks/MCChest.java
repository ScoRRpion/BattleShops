package mc.alk.mc.blocks;

import mc.alk.mc.MCBlock;
import mc.alk.mc.MCInventory;
import mc.alk.mc.MCItemStack;

public interface MCChest extends MCBlock {

	MCItemStack[] getItems();

	boolean isDoubleChest();

	MCChest getNeighborChest();

	MCInventory getInventory();
}
