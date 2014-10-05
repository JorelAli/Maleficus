package io.github.Skepter.commands;

import io.github.Skepter.Maleficus;
import io.github.Skepter.Other.MPlayer;
import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;
import io.github.Skepter.Tools.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PowerCommand implements CommandExecutor {

	//change it and remove runSub, that way it'll just be run and the params can be the subElement if there is one!
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String sabel, String[] args) {
		if (Utils.commandCheck(sender, command, "power")) {
			Player player = (Player) sender;
			// /power - displays power help
			// /power list - displays all powers (elements), with green and
			// red indicator (showing what you have)
			// /power current - displays current powers, how much mana they
			// do/use
			// /power info <power> - learn info about a power
			// /power select <power> - learn a power (element) - drains
			// anima to 0 and resets max to default

			// ***not to be confused with the elemental attacks!!!!!!!!!!!!!***
			/*
			 * This is merely the 'elements' - PowerType Element enum!
			 */
			switch (args.length) {
			case 0:
				player.sendMessage(Utils.center("--- §6[§fPower Help§6]§f ---"));
				player.sendMessage(" §6/power list §f- Shows a list of all powers");
				player.sendMessage(" §6/power elements §f- Shows a list of all elements");
				player.sendMessage(" §6/power current §f- Shows a list of your elemental attributes and other powers"); // ?
				player.sendMessage(" §6/power info <power> §f- Shows information about a power");
				player.sendMessage(" §6/power select <power> §f- Selects an element - will reset levels!");
				return true;
			case 1:
				switch (args[0]) {
				case "elements":
					player.sendMessage(Utils.center("--- §6[§fElements§6]§f ---"));
					player.sendMessage(" §cFire §f- Utilize the power of hot energy");
					player.sendMessage(" §9Water §f- Use the waves to your advantage");
					player.sendMessage(" §7Air §f- Harness the power of the wind around you");
					player.sendMessage(" §eShock §f- Electrocute all and bring justice to the world");
					player.sendMessage(" §6Earth §f- Use Mother Nature to aid you");
					player.sendMessage(" §fLight §f- Make use of the good inside you to defeat evil");
					player.sendMessage(" §0Dark §f- Fight with the good and become the evil");
					player.sendMessage(" §5Void §f- The mysterious power with incredible capabilities");
					return true;
				case "list":
					for (String s : Maleficus.getInstance().getPowerMapValuesByElement()) {
						player.sendMessage(s);
					}
					return true;
				case "current":
					String element = Utils.capitalize(MPlayer.getElement(player).toString().toLowerCase());
					switch (MPlayer.getElement(player)) {
					case AIR:
						player.sendMessage(" §6[§fCurrent element§6] §f" + element);
						return true;
					case DARK:
						player.sendMessage(" §6[§fCurrent element§6] §0" + element);
						return true;
					case EARTH:
						player.sendMessage(" §6[§fCurrent element§6] §e" + element);
						return true;
					case FIRE:
						player.sendMessage(" §6[§fCurrent element§6] §c" + element);
						return true;
					case LIGHT:
						player.sendMessage(" §6[§fCurrent element§6] §f" + element);
						return true;
					case NONE:
						player.sendMessage(" §6[§fCurrent element§6] §f" + element);
						return true;
					case SHOCK:
						player.sendMessage(" §6[§fCurrent element§6] §e" + element);
						return true;
					case WATER:
						player.sendMessage(" §6[§fCurrent element§6] §9" + element);
						return true;
					case VOID:
						player.sendMessage(" §6[§fCurrent element§6] §5" + element);
						return true;
					default:
						player.sendMessage(" §4§lCRITICAL ERROR - line 92 PowerCommand.java");
						return true;
					}
				case "select":
					// tabComplete required here!
					MPlayer.setElement(player, args[1]);
				}
				return true;
				// sort
			case 2:
				if (args[0].equalsIgnoreCase("cast")) {
					if (!Maleficus.getInstance().getPowerMap().containsKey(args[1].toLowerCase())) {
						player.sendMessage(" §6[§fUh oh!§6]§f The power §a" + args[0] + " §fdoesn't exist!");
						return true;
					}
					// may implement that commandframework to allow for the tab
					// complete
					Power p = Maleficus.getInstance().getPowerMap().get(args[1].toLowerCase());
					if (MPlayer.hasEnoughAnima(player, p.getAnima())) {
						//error here
						Maleficus.getInstance().getPowerMap().get(args[1].toLowerCase()).run(player, "");
						// error on line 95
						if (MPlayer.getElement(player) == p.getElement()) {
							MPlayer.takeAnima(player, (int) Math.round(p.getAnima()));
							return true;
						} else if (MPlayer.getElement(player) != p.getElement() && MPlayer.getElement(player) != Element.NONE) {
							MPlayer.takeAnima(player, (int) Math.round(p.getAnima() * 3)); // tell												   // enough
							return true;
						} else if (MPlayer.getElement(player) == Element.NONE) {
							player.sendMessage(" §6[§fUh oh!§6]§f NONE element players cannot use powers!");
							return true;
						}

					} else {
						player.sendMessage(" §6[§fUh oh!§6]§f You need §a" + (p.getAnima() - MPlayer.getAnima(player)) + " §fmore anima to use that!");
						player.sendMessage("(Requires §a" + p.getAnima() + " §fanima to use)");
					}

					return true;
				}
				return true;
			}

			// } else if (args.length == 2) {
			// if (!Maleficus.getPowerMap().containsKey(args[0].toLowerCase()))
			// {
			// player.sendMessage(" §6[§fUh oh!§6]§f The power §a" + args[0] +
			// " §fdoesn't exist!");
			// return true;
			// }
			// if (!Maleficus.getSubMap().containsKey(args[1].toLowerCase())) {
			// player.sendMessage(" §6[§fUh oh!§6]§f The subelement §a" +
			// args[0] + " §fdoesn't exist!");
			// return true;
			// }
			// Power p = Maleficus.getPowerMap().get(args[0].toLowerCase());
			// if (MPlayer.hasEnoughAnima(player, p.getAnima()) &&
			// MPlayer.hasEnoughSubAnima(player, MPlayer.getLevel(player) *
			// 100)) {
			// Maleficus.getPowerMap().get(args[0].toLowerCase()).runSub(player,
			// args[1]);
			// MPlayer.setSubAnima(player, 0);
			// } else {
			// if (!MPlayer.hasEnoughAnima(player, p.getAnima())) {
			// player.sendMessage(" §6[§fUh oh!§6]§f You need §a" +
			// (p.getAnima() - MPlayer.getAnima(player)) +
			// " §fmore anima to use that!");
			// player.sendMessage("(Requires §a" + p.getAnima() +
			// " §fanima to use)");
			// }
			// if (!MPlayer.hasEnoughSubAnima(player, MPlayer.getLevel(player) *
			// 100)) {
			// player.sendMessage(" §6[§fUh oh!§6]§f You need §a" +
			// ((MPlayer.getSubLevel(player) * 100) -
			// MPlayer.getSubAnima(player)) + " §fmore sub anima to use that!");
			// }
			// }
			// }
			// return true;

		}
		return false;
	}

}
