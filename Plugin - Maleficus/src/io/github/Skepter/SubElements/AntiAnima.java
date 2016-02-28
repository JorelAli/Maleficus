package io.github.skepter.subelements;

import org.bukkit.entity.Player;

import io.github.skepter.other.MPlayer;

public class AntiAnima implements SubElements{

	@Override
	public String getName() {
		return "AntiAnima";
	}

	@Override
	public String getDescription() {
		return "Turns your SubAnima into Anima";
	}

	@Override
	public void run(Player player) {
		MPlayer.giveAnima(player, MPlayer.getSubAnima(player));
		MPlayer.setSubAnima(player, 0);
	}

	@Override
	public String getComboDescription() {
		return "Halves the amount of anima required for an attack";
	}

}
