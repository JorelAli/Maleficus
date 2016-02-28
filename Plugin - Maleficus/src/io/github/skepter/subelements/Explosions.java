package io.github.skepter.subelements;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.skepter.other.MPlayer;

public class Explosions implements SubElements{

	@Override
	public String getName() {
		return "Explosions";
	}

	@Override
	public String getDescription() {
		return "Creates an explosion at your location";
	}

	@Override
	public void run(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 126));
		player.getWorld().createExplosion(player.getLocation(), 6.0F);
		MPlayer.setSubAnima(player, 0);
	}

	@Override
	public String getComboDescription() {
		return "Creates explosions";
	}

}
