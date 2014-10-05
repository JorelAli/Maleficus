package io.github.Skepter.commands;

import io.github.Skepter.Tools.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BindCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String sabel, String[] args) {
		if (Utils.commandCheck(sender, command, "bind")) {
			@SuppressWarnings("unused")
			Player player = (Player) sender;
			
			switch(args.length) {
			case 0:
				//explain bind
				return true;
			case 1:
				//bind 1 power by name
				return true;
			case 2:
				//bind a combo by name
				return true;
			}
			return true;
		}
		return false;
	}

}
