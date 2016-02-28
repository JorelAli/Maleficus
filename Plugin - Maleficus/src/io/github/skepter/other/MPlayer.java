package io.github.skepter.other;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import io.github.skepter.Maleficus;
import io.github.skepter.powers.PowerType.Element;

public class MPlayer{

	private static File getFile(Player player) {
		return new File(Maleficus.getInstance().getDataFolder() + File.separator + "Players", player.getName() + ".yml");
	}
	
	public static YamlConfiguration getPlayer(Player player) {
		return YamlConfiguration.loadConfiguration(getFile(player));
	}

	public static void savePlayer(Player player) {
		File configFile = getFile(player);
		try {
			getPlayer(player).save(configFile);
		} catch (IOException ex) {
			Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
		}
	}
	
	public static void savePlayer(Player player, FileConfiguration config) {
		File configFile = getFile(player);
		try {
			config.save(configFile);
		} catch (IOException ex) {
			Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
		}
	}

	public static void saveDefaultPlayer(Player player) {
		File configFile = getFile(player);
		if (!configFile.exists())
			try {
				configFile.createNewFile();
			} catch (IOException e) {
			}
		return;
	}
	
	public static void saveAllPlayers() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			savePlayer(player);
		}
	}
	
	public static int getAnima(Player player) {
		return getPlayer(player).getInt("anima");
	}

	public static int getSubAnima(Player player) {
		return getPlayer(player).getInt("subanima");
	}
	
	public static void setAnima(Player player, int anima) {
		FileConfiguration fileConfig = MPlayer.getPlayer(player);
		fileConfig.set("anima", anima);
		savePlayer(player, fileConfig);
	}

	public static void setSubAnima(Player player, int subanima) {
		FileConfiguration fileConfig = MPlayer.getPlayer(player);
		fileConfig.set("subanima", subanima);
		savePlayer(player, fileConfig);
	}
	
	public static int getLevel(Player player) {
		return getPlayer(player).getInt("level");
	}
	
	public static void setLevel(Player player, int level) {
		FileConfiguration fileConfig = MPlayer.getPlayer(player);
		fileConfig.set("level", level);
		savePlayer(player, fileConfig);
	}
	
	public static boolean hasEnoughAnima(Player player, int anima) {
		if(getAnima(player) >= anima) {
			return true;
		}
		return false;
	}
	
	public static void takeAnima(Player player, int anima) {
		int newanima = getAnima(player) - anima;
		if(newanima < 0) {
			newanima = 0;
		}
		setAnima(player, newanima);
		savePlayer(player);
	}
	
	public static void giveAnima(Player player, int anima) {
		int newanima = getAnima(player) + anima;
		if(newanima > getLevel(player) * 100) {
			newanima = getLevel(player) * 100;
		}
		setAnima(player, newanima);
		savePlayer(player);
	}
	
	public static int getSubLevel(Player player) {
		return getPlayer(player).getInt("sublevel");
	}
	
	public static void setSubLevel(Player player, int sublevel) {
		FileConfiguration fileConfig = MPlayer.getPlayer(player);
		fileConfig.set("sublevel", sublevel);
		savePlayer(player, fileConfig);
	}
	
	public static boolean hasEnoughSubAnima(Player player, int anima) {
		if(getSubAnima(player) >= anima) {
			return true;
		}
		return false;
	}
	
	public static void takeSubAnima(Player player, int anima) {
		int newanima = getAnima(player) - anima;
		if(newanima < 0) {
			newanima = 0;
		}
		setSubAnima(player, newanima);
		savePlayer(player);
	}
	
	public static void giveSubAnima(Player player, int anima) {
		int newanima = getAnima(player) + anima;
		if(newanima > getLevel(player) * 100) {
			newanima = getLevel(player) * 100;
		}
		setSubAnima(player, newanima);
		savePlayer(player);
	}
	
	public static Element getElement(Player player) {
		//error on line below
		return Element.valueOf(getPlayer(player).getString("element"));
	}
	

	public static void setElement(Player player, String element) {
		FileConfiguration fileConfig = MPlayer.getPlayer(player);//something wrong here
		fileConfig.set("element", element.toUpperCase());
		fileConfig.set("foo", "bar");
		savePlayer(player, fileConfig);
	}
}
