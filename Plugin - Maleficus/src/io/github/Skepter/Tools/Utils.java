package io.github.Skepter.Tools;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {

	public static String capitalize(String s) {
		String f = s.substring(0, 1);
		String f1 = f.toUpperCase();
		String s1 = s.substring(1, s.length());
		String s2 = f1 + s1;
		return s2;
	}
	
	public static String join(String[] arr, String seperator) {
		String s = "";
		for (int i = 0; i < arr.length; i++) {
			s = s + arr[i] + seperator;
		}
		return s;
	}

	public static boolean commandCheck(CommandSender sender, Command command, String cmd) {
		if (sender instanceof Player && command.getName().equalsIgnoreCase(cmd)) {
			return true;
		}
		return false;
	}

	public static boolean commandCheck(CommandSender sender, Command command, String cmd, String perm) {
		if (commandCheck(sender, command, cmd) && (sender.hasPermission(perm) || sender.isOp())) {
			return true;
		}
		return false;
	}

	public static String center(String text) {
	    int spaces = (int) Math.round(( 80 - 1.4 * ChatColor.stripColor(text).length()) / 2);
	    String s = "";
	    for(int i = 0; i < spaces; i++) {
	    	s = s + " ";
	    }
	    return s + text;
	}
	
	public static int EAnima(int getAnima) {
		return( int) Math.round(getAnima * 0.9);
	}
}
