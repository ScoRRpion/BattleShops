package mc.alk.bukkit;

import mc.alk.mc.MCBlock;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;

import org.bukkit.block.Block;

public class BukkitBlock implements MCBlock {
	Block block;

	public BukkitBlock(Block block) {
		this.block = block;
	}

	@Override
	public MCWorld getWorld() {
		return new BukkitWorld(this.block.getWorld());
	}

	@Override
	public MCLocation getLocation() {
		return new BukkitLocation(this.block.getLocation());
	}

	@Override
	public int getX() {
		return this.block.getX();
	}

	@Override
	public int getY() {
		return this.block.getY();
	}

	@Override
	public int getZ() {
		return this.block.getZ();
	}

	@Override
	public int getType() {
		return this.block.getTypeId();
	}

	@Override
	public void update(boolean b) {
		this.block.getState().update();
	}

	@Override
	public BukkitBlock clone() {
		return new BukkitBlock(this.block);
	}

	public Block getBlock() {
		return this.block;
	}

}
