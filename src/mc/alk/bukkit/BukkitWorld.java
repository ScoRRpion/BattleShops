package mc.alk.bukkit;

import mc.alk.bukkit.blocks.BukkitChest;
import mc.alk.bukkit.blocks.BukkitSign;
import mc.alk.mc.MCBlock;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCWorld;
import mc.alk.mc.blocks.MCChest;
import mc.alk.mc.blocks.MCSign;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;

public class BukkitWorld implements MCWorld {
	World world;

	public BukkitWorld(World world) {
		this.world = world;
	}

	@Override
	public String getName() {
		return this.world.getName();
	}

	@Override
	public MCBlock getBlockAt(int x, int y, int z) {
		Block b = this.world.getBlockAt(x, y, z);
		switch (b.getType()) {
		case CHEST:
			return new BukkitChest((Chest) b.getState());
		case SIGN:
		case SIGN_POST:
		case WALL_SIGN:
			return new BukkitSign((Sign) b.getState());
		default:
			return new BukkitBlock(b);
		}
	}

	@Override
	public MCBlock getBlockAt(MCLocation loc) {
		return this.getBlockAt(loc.getBlockX(), loc.getBlockY(),
				loc.getBlockZ());
	}

	@Override
	public MCBlock toType(MCBlock block, Class<? extends MCBlock> clazz)
			throws ClassCastException {
		if (clazz.isAssignableFrom(block.getClass())) {
			return block;
		}
		Location loc = ((BukkitLocation) block.getLocation()).loc;
		Block b = loc.getBlock();
		if (b == null) {
			return null;
		}
		if (clazz == MCSign.class) {
			if (b.getState() instanceof Sign) {
				return new BukkitSign((Sign) b.getState());
			}
		} else if (clazz == MCChest.class) {
			if (b.getState() instanceof Chest) {
				return new BukkitChest((Chest) b.getState());
			}
		} else {
			throw new ClassCastException("Block can not be cast to "
					+ clazz.getSimpleName());
		}
		return null;
	}

	@Override
	public boolean isType(MCBlock block, Class<? extends MCBlock> clazz) {
		try {
			MCBlock b = this.toType(block, clazz);
			return b != null;
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "[World " + this.world.getName() + "]";
	}

}
