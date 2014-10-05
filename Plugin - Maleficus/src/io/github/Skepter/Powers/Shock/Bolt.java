package io.github.Skepter.Powers.Shock;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;

import org.bukkit.entity.Player;

public class Bolt implements Power{

	@Override
	public String getName() {
		return "Bolt";
	}

	@Override
	public String getDescription() {
		return "Strikes lightning";
	}

	@Override
	public Element getElement() {
		return Element.SHOCK;
	}

	@Override
	public int getAnima() {
		return 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(Player player, String params) {
		player.getWorld().strikeLightning(player.getTargetBlock(null, 256).getLocation());
	}

}
