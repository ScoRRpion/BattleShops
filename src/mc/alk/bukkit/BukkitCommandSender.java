package mc.alk.bukkit;

import org.bukkit.command.CommandSender;

public class BukkitCommandSender implements MCCommandSender {
	final CommandSender sender;

	public BukkitCommandSender(CommandSender sender) {
		this.sender = sender;
	}

	@Override
	public boolean hasPermission(String node) {
		return this.sender.hasPermission(node);
	}

	@Override
	public void sendMessage(String message) {
		this.sender.sendMessage(message);
	}

	@Override
	public String getName() {
		return "Console";
	}
}
