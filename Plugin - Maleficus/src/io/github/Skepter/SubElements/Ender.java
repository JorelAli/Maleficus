package io.github.Skepter.SubElements;

import io.github.Skepter.Other.MPlayer;

import org.bukkit.entity.Player;

public class Ender implements SubElements {

	@Override
	public String getName() {
		return "Ender";
	}

	@Override
	public String getDescription() {
		return "Specialises in teleporting";
	}

	@Override
	public void run(Player player) {
		MPlayer.giveAnima(player, MPlayer.getSubAnima(player));
		MPlayer.setSubAnima(player, 0);
		// teleport to a random location, gives high resistance? (to cancel out
		// damage from powerful attacks
	}

	@Override
	public String getComboDescription() {
		return "Teleports to a random location";
	}

}
