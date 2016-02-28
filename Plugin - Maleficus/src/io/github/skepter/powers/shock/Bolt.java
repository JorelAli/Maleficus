package io.github.skepter.powers.shock;

import org.bukkit.entity.Player;

import io.github.skepter.powers.Power;
import io.github.skepter.powers.PowerType.Element;
import io.github.skepter.tools.Utils;

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

	@Override
	public void run(Player player, String params) {
		player.getWorld().strikeLightning(Utils.getTargetBlock(player).getLocation());
	}

}
