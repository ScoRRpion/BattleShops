package mc.alk.mc;

public interface MCBlock {
	public MCWorld getWorld();

	public MCLocation getLocation();

	public int getX();

	public int getY();

	public int getZ();

	public int getType();

	public void update(boolean b);
}
