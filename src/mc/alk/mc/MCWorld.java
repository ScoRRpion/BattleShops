package mc.alk.mc;

public interface MCWorld {
	public String getName();

	public MCBlock getBlockAt(int x, int y, int z);

	public MCBlock getBlockAt(MCLocation loc);

	public boolean isType(MCBlock block, Class<? extends MCBlock> clazz);

	public MCBlock toType(MCBlock block, Class<? extends MCBlock> clazz)
			throws ClassCastException;
}
