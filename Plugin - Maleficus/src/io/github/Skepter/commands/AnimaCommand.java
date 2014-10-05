package io.github.Skepter.commands;

import io.github.Skepter.Other.MPlayer;
import io.github.Skepter.Tools.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AnimaCommand implements CommandExecutor {

	JavaPlugin plugin;
	
	public AnimaCommand(JavaPlugin myplugin) {
		this.plugin = myplugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String sabel, String[] args) {
		if (Utils.commandCheck(sender, command, "anima")) {
			Player player = (Player) sender;
			if (args.length == 0) {

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

				player.sendMessage(Utils.center("--- §6[§fAnima - " + anima + " §6|§f SubAnima - " + subanima + "§6]§f ---"));
				player.sendMessage(Utils.center(" §6{§a" + fullanima + ChatColor.GRAY + emptyanima + "§6} §flvl " + MPlayer.getLevel(player)));
				player.sendMessage(Utils.center(" §6{§a" + fullsubanima + ChatColor.GRAY + emptysubanima + "§6} §flvl " + MPlayer.getSubLevel(player)));
			}
			return true;

		}
		return false;
	}

}
