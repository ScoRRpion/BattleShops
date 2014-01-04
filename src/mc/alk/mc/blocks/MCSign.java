package mc.alk.mc.blocks;

import mc.alk.mc.MCBlock;

public interface MCSign extends MCBlock {

	public void setLine(int index, String msg);

	public String getLine(int index);

	public String[] getLines();

}
