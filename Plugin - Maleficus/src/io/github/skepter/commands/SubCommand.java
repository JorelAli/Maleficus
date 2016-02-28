package io.github.skepter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.skepter.Maleficus;
import io.github.skepter.other.MPlayer;
import io.github.skepter.subelements.SubElements;
import io.github.skepter.tools.Utils;

public class SubCommand implements CommandExecutor {

	/* WTF is this? */
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String sabel, String[] args) {
		if (Utils.commandCheck(sender, command, "sub")) {
			Player player = (Player) sender;
			if (args.length == 0) {
				// /power - displays power help
				// /power list - displays all powers (elements), with green and
				// red indicator (showing what you have)
				// /power current - displays current powers, how much mana they
				// do/use
				// /power info <power> - learn info about a power
				// /power select <power> - learn a power (element) - drains
				// anima to 0 and resets max to default

				// not to be confused with the elemental attacks!!!!!!!!!!!!!
				switch (args.length) {
				case 0:
					// show help
					break;
				case 1:
					// list & current
					break;
				case 2:
					// info & select
					break;
				}
			} else if (args.length == 1) {
				if (!Maleficus.getInstance().getSubMap().containsKey(args[0].toLowerCase())) {
					player.sendMessage(" §6[§fUh oh!§6]§f The subElement §a" + args[0] + " §fdoesn't exist!");
					return true;
				}
				SubElements p = Maleficus.getInstance().getSubMap().get(args[0].toLowerCase());
				if(MPlayer.hasEnoughSubAnima(player, MPlayer.getSubLevel(player) * 100)) {
	 				p.run(player);
	 				return true;
				} else {
					player.sendMessage(" §6[§fUh oh!§6]§f You need §a" + ((MPlayer.getSubLevel(player) * 100) - MPlayer.getSubAnima(player)) + " §fmore SubAnima to use that!");
				}
			}
			return true;

		}
		return false;
	}

}
