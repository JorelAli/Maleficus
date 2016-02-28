package io.github.skepter.powers.thevoid;

import io.github.skepter.powers.Power;
import io.github.skepter.powers.PowerType.Element;
import io.github.skepter.tools.Utils;

import org.bukkit.entity.Player;

public class Discharge implements Power{

	@Override
	public String getName() {
		return "Discharge";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public Element getElement() {
		return Element.VOID;
	}

	@Override
	public int getAnima() {
		return 0;
	}

	@Override
	public void run(Player player, String params) {
		//adds anima?
		//does extra damage to Void attacks?
		//IDK!
		player.getWorld().strikeLightning(Utils.getTargetBlock(player).getLocation());
	}

}
