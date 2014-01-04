package mc.alk.shops.bukkit.controllers;

import mc.alk.bukkit.BukkitLocation;
import mc.alk.bukkit.BukkitPlayer;
import mc.alk.bukkit.MCCommandSender;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;
import mc.alk.shops.Defaults;
import mc.alk.shops.controllers.PermController;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;

import org.bukkit.entity.Player;
import org.bukkit.Location;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

/**
 * 
 * @author alkarin
 * 
 */
public class BukkitPermController extends PermController {
	public static WorldGuardPlugin worldGuard;
	public static GriefPrevention griefPrevention;

	public BukkitPermController() {
	}

	@Override
	public boolean hasMCBuildPerms(MCPlayer mcplayer, MCLocation mclocation) {
		if (griefPrevention != null) {
			PlayerData playerData = griefPrevention.dataStore
					.getPlayerData(mcplayer.getName());
			Claim claim = griefPrevention.dataStore.getClaimAt(
					((BukkitLocation) mclocation).getLocation(), false,
					playerData.lastClaim);
			if (claim != null) {
				return (claim
						.allowAccess(((BukkitPlayer) mcplayer).getPlayer()) == null);
			}
		}
		return worldGuard == null ? null : worldGuard.canBuild(
				((BukkitPlayer) mcplayer).getPlayer(),
				((BukkitLocation) mclocation).getLocation());
	}

	public boolean hasMCCreatePermissions(MCPlayer mcplayer,
			MCLocation mclocation, Player player, Location location) {
		if (worldGuard != null)
			return worldGuard.canBuild(((BukkitPlayer) mcplayer).getPlayer(),
					((BukkitLocation) mclocation).getLocation())
					|| isMCAdmin(mcplayer);
		if (hasMCPermission(mcplayer, Defaults.PERM_CREATE))
			return true;
		return isMCAdmin(mcplayer);
	}

	@Override
	public boolean isMCAdmin(MCCommandSender sender) {
		return hasMCPermission(sender, Defaults.PERM_ADMIN);
	}

	@Override
	public boolean hasMCPermission(MCCommandSender sender, String perm) {
		Player p = ((BukkitPlayer) sender).getPlayer();
		return p.hasPermission(perm) || p.isOp();
	}

	// public boolean isMCAdmin(String name) {
	// if (ShopOwner.isAdminShop(name)) return true;
	// Player p = BattleShops.getBukkitServer().getPlayer(name);
	// if (p == null)
	// return false;
	// return BukkitPermController.isAdmin(p);
	// }

}
