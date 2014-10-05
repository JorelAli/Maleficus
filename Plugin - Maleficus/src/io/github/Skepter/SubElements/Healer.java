package io.github.Skepter.SubElements;

import io.github.Skepter.Other.MPlayer;

import org.bukkit.entity.Player;

public class Healer implements SubElements{

	@Override
	public String getName() {
		return "Healer";
	}

	@Override
	public String getDescription() {
		return "Regenerates full health";
	}

	@Override
	public void run(Player player) {
		player.setHealth(player.getMaxHealth());
		MPlayer.setSubAnima(player, 0);
	}

	@Override
	public String getComboDescription() {
		return "Heals others or yourself";
	}
}
