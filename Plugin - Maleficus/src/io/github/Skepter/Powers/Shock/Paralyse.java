package io.github.skepter.powers.shock;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.skepter.powers.Power;
import io.github.skepter.powers.PowerType.Element;
import io.github.skepter.tools.Sphere;

public class Paralyse implements Power{

	@Override
	public String getName() {
		return "Paralyse";
	}

	@Override
	public String getDescription() {
		return "Freezes all players around you";
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
		Sphere sphere = new Sphere(player.getLocation(), 20);
		List<Entity> entities = player.getNearbyEntities(20, 20, 20);
		for(Entity entity : entities) {
			if(sphere.contains(entity.getLocation()) && entity instanceof LivingEntity) {
				player.getWorld().strikeLightningEffect(entity.getLocation());
				((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 10, 10000));
				((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 10, -10000));
			}
		}
	}

}
