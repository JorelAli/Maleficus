package io.github.skepter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.skepter.tools.Utils;

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
