package io.github.skepter.powers.dark;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.skepter.powers.Power;
import io.github.skepter.powers.PowerType.Element;
import io.github.skepter.tools.Sphere;

public class Blinder implements Power{

	@Override
	public String getName() {
		return "Blinder";
	}

	@Override
	public String getDescription() {
		return "Blinds enemies around you!";
	}

	@Override
	public Element getElement() {
		return Element.DARK;
	}

	@Override
	public int getAnima() {
		return 1000;
	}

	@Override
	public void run(Player player, String params) {
		Sphere sphere = new Sphere(player.getLocation(), 20);
		List<Entity> entities = player.getNearbyEntities(20, 20, 20);
		for(Entity entity : entities) {
			if(sphere.contains(entity.getLocation()) && entity instanceof LivingEntity) {
				((LivingEntity)entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 120, 1)); //lasts 2 mins
			}
		}
	}

}
