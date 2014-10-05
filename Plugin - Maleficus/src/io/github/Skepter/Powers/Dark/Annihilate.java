package io.github.Skepter.Powers.Dark;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;
import io.github.Skepter.Tools.Sphere;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Annihilate implements Power {

	@Override
	public String getName() {
		return "Annihilate";
	}

	@Override
	public String getDescription() {
		return "Kills all around you!";
	}

	@Override
	public Element getElement() {
		return Element.DARK;
	}

	@Override
	public int getAnima() {
		return 5000;
	}

	@Override
	public void run(Player player, String params) {
		Sphere sphere = new Sphere(player.getLocation(), 20);
		List<Entity> entities = player.getNearbyEntities(20, 20, 20);
		player.setHealth(0);
		for (Entity entity : entities) {
			if (entity instanceof LivingEntity && sphere.contains(entity.getLocation())) {
				((LivingEntity) entity).setHealth(0);
			}
		}
	}

}
