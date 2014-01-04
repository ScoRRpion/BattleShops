package mc.alk.bukkit.blocks;

import mc.alk.bukkit.BukkitBlock;
import mc.alk.mc.blocks.MCSign;

import org.bukkit.block.Sign;

public class BukkitSign extends BukkitBlock implements MCSign {
	Sign sign;

	public BukkitSign(Sign sign) {
		super(sign.getBlock());
		this.sign = sign;
	}

	@Override
	public void setLine(int index, String line) {
		this.sign.setLine(index, line);
	}

	@Override
	public String getLine(int index) {
		return this.sign.getLine(index);
	}

	@Override
	public String[] getLines() {
		return this.sign.getLines();
	}

	@Override
	public BukkitSign clone() {
		return new BukkitSign(this.sign);
	}

	@Override
	public void update(boolean b) {
		this.sign.update(true);
	}

	public Sign getSign() {
		return this.sign;
	}
}
