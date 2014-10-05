package io.github.Skepter.listeners;

import io.github.Skepter.Maleficus;
import io.github.Skepter.Other.MPlayer;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	static HashMap<String, Integer> taskIDMap = new HashMap<String, Integer>();
	
	@EventHandler
	public void storeData(PlayerJoinEvent event) {
		File playerFiles = new File(Maleficus.getInstance().getDataFolder(), "Players");
		File checkPlayer = new File(playerFiles, event.getPlayer().getName() + ".yml");

		if (!checkPlayer.exists()) {
			FileConfiguration fileConfig = MPlayer.getPlayer(event.getPlayer());
			MPlayer.saveDefaultPlayer(event.getPlayer());
			if (!fileConfig.contains("anima") || fileConfig == null) {
				fileConfig.set("anima", Integer.valueOf(100));
			}
			if (!fileConfig.contains("subanima") || fileConfig == null) {
				fileConfig.set("subanima", Integer.valueOf(100));
			}
			if (!fileConfig.contains("level") || fileConfig == null) {
				fileConfig.set("level", Integer.valueOf(1));
			}
			if (!fileConfig.contains("sublevel") || fileConfig == null) {
				fileConfig.set("sublevel", Integer.valueOf(1));
			}
			if (!fileConfig.contains("element") || fileConfig == null) {
				fileConfig.set("element", "NONE");
			}
			MPlayer.savePlayer(event.getPlayer(), fileConfig);
		} else {
			//if the file does exist, but data is missing; update the dataFile
			//forget this... because the methods above do this already
		}

	}
	
	@EventHandler
	public void startClock(final PlayerJoinEvent event) {
		startClock(event.getPlayer());
	}
	
	public static void startClock(final Player player) {
		int taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Maleficus.getInstance(), new Runnable() {
			@Override
			public void run() {
				MPlayer.giveAnima(player, 1);
			}
		}, 0, 20);
		taskIDMap.put(player.getName(), taskID);
		return;
	}
	
	@EventHandler
	public void stopClock(PlayerQuitEvent event) {
		Bukkit.getServer().getScheduler().cancelTask(taskIDMap.get(event.getPlayer().getName()));
		return;
	}
	
	@EventHandler
	public void onSlay(EntityDeathEvent event) {
		if(event.getEntity() instanceof Monster && event.getEntity().getKiller() instanceof Player) {
			int reward = Maleficus.getInstance().getConfig().getInt("reward-for-mobs");
			Player player = event.getEntity().getKiller();
			int r = (int)Math.random();
			MPlayer.giveSubAnima(player, r * reward + event.getDroppedExp());
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
			Player player = event.getEntity().getKiller();
			int penalty = Maleficus.getInstance().getConfig().getInt("penalty-for-death");
			int r = (int)Math.random();
			//error on line below
			MPlayer.takeSubAnima(player, r * penalty);
	}
}
