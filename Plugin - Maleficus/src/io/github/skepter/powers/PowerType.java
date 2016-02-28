package io.github.skepter.powers;

import org.bukkit.ChatColor;

public class PowerType{

	public static enum Element {
		FIRE, WATER, EARTH, AIR, LIGHT, DARK, SHOCK, VOID, NONE;

		public static ChatColor toChatColor(Element e) {
			switch (e) {
			case AIR:
				return ChatColor.GRAY;
			case DARK:
				return ChatColor.BLACK;
			case EARTH:
				return ChatColor.GOLD;
			case FIRE:
				return ChatColor.RED;
			case LIGHT:
				return ChatColor.AQUA;
			case NONE:
				return ChatColor.DARK_GRAY;
			case SHOCK:
				return ChatColor.YELLOW;
			case VOID:
				return ChatColor.DARK_PURPLE;
			case WATER:
				return ChatColor.BLUE;
			default:
				return ChatColor.WHITE;
			}
		}
	}
}
