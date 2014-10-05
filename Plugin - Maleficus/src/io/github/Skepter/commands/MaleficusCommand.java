package io.github.Skepter.commands;

import io.github.Skepter.Maleficus;
import io.github.Skepter.Other.MPlayer;
import io.github.Skepter.Tools.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaleficusCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String sabel, String[] args) {
		if (Utils.commandCheck(sender, command, "maleficus")) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(Utils.center("--- §6[§fMaleficus§6]§f ---"));
				player.sendMessage(" §6[§fVersion§6] §f" + Maleficus.getInstance().getDescription().getVersion());
				player.sendMessage(" §6[§fAuthor§6] §fSkepter");
				player.sendMessage("");
				player.sendMessage(Utils.center("--- §6[§fCommands§6]§f ---"));
				player.sendMessage(" §6/maleficus §f- ");
				player.sendMessage(" §6/power §f- ");
				player.sendMessage(" §6/anima §f- Displays how much Anima and SubAnima you have");
				player.sendMessage(" §6/level §f- Displays what level you are at");
			}else if(args[0].equalsIgnoreCase("stats")) {
				player.sendMessage("Anima: " + MPlayer.getAnima(player));
				player.sendMessage("SubAnima: " + MPlayer.getSubAnima(player));
				player.sendMessage("Level: " + MPlayer.getLevel(player));
				player.sendMessage("SubLevel: " + MPlayer.getSubLevel(player));
				player.sendMessage("Element: " + MPlayer.getElement(player).toString());
				player.sendMessage("Config: " + MPlayer.getPlayer(player).getKeys(false));
			} else if(args[0].equalsIgnoreCase("admin")) {
				if(args[1].equalsIgnoreCase("setanima")) {
					MPlayer.setAnima(player, Integer.parseInt(args[2]));
				} else if(args[1].equalsIgnoreCase("setlevel")) {
					MPlayer.setLevel(player, Integer.parseInt(args[2]));
				} else if(args[1].equalsIgnoreCase("setsublevel")) {
					MPlayer.setSubLevel(player, Integer.parseInt(args[2]));
				} else if(args[1].equalsIgnoreCase("setsubanima")) {
					MPlayer.setSubAnima(player, Integer.parseInt(args[2]));
				}
			}
			return true;
		}
		return false;
	}

}
