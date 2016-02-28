package io.github.skepter.listeners;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.skepter.Maleficus;
import io.github.skepter.other.MPlayer;
import io.github.skepter.tools.MinecraftReflectionUtils;
import io.github.skepter.tools.PacketBuilder;
import io.github.skepter.tools.PacketBuilder.PacketType;

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
				//Calculate the bar things
				int animaBarSize = 130;

				int maxanima = MPlayer.getLevel(player) * 100;
				int maxsubanima = MPlayer.getSubLevel(player) * 100;
				int anima = MPlayer.getAnima(player);
				int subanima = MPlayer.getSubAnima(player);

				if (anima > maxanima) {
					anima = maxanima;
				}
				if (subanima > maxsubanima) {
					subanima = maxsubanima;
				}

				int indicator_anima = 0;
				int indicator_subanima = 0;

				try {
					indicator_anima = (anima * animaBarSize) / maxanima;
					indicator_subanima = (subanima * animaBarSize) / maxsubanima;
				} catch (ArithmeticException e) {
					// Do nothing because anima/subanima is already 0
				}

				int empty_anima = animaBarSize - indicator_anima;
				int empty_subanima = animaBarSize - indicator_subanima;

				String fullanima = "";
				for (int i = 0; i < indicator_anima; i++) {
					fullanima = fullanima + "|";
				}
				String fullsubanima = "";
				for (int i = 0; i < indicator_subanima; i++) {
					fullsubanima = fullsubanima + "|";
				}
				String emptyanima = "";
				for (int i = 0; i < empty_anima; i++) {
					emptyanima = emptyanima + "|";
				}
				String emptysubanima = "";
				for (int i = 0; i < empty_subanima; i++) {
					emptysubanima = emptysubanima + "|";
				}
				
				String animaStr = " §6{§a" + fullanima + ChatColor.GRAY + emptyanima + "§6} §flvl " + MPlayer.getLevel(player);
				
				//player.sendMessage(animaStr);
				try {
					//new PacketBuilder(player, PacketType.PLAY_OUT_CHAT).set("a", new MinecraftReflectionUtils(player).chatSerialize(animaStr)).set("b", (byte) 2).send();
				} catch (Exception e) {
					e.printStackTrace();
				}
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
