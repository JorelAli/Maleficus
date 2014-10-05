package io.github.Skepter.Powers.Void;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;

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

	@SuppressWarnings("deprecation")
	@Override
	public void run(Player player, String params) {
		//adds anima?
		//does extra damage to Void attacks?
		//IDK!
		player.getWorld().strikeLightning(player.getTargetBlock(null, 256).getLocation());
	}

}
