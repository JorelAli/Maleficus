package io.github.Skepter.Powers.Light;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AntiPenetrate implements Power{

	@Override
	public String getName() {
		return "Anti Penetrate";
	}

	@Override
	public String getDescription() {
		return "Become invincible!";
	}

	@Override
	public Element getElement() {
		return Element.LIGHT;
	}

	@Override
	public int getAnima() {
		return 5000;
	}

	@Override
	public void run(Player player, String params) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 20 * 30));
	}
}
