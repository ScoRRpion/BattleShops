package mc.alk.bukkit;

public interface MCCommandSender {

	public boolean hasPermission(String node);

	public void sendMessage(String message);

	public String getName();
}
