package io.github.Skepter.SubElements;

import io.github.Skepter.Other.MPlayer;
import io.github.Skepter.Tools.Sphere;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Beserker implements SubElements{

	@Override
	public String getName() {
		return "Beserker";
	}

	@Override
	public String getDescription() {
		return "Does instant kill damage to mobs around";
	}

	@Override
	public void run(Player player) {
		Sphere sphere = new Sphere(player.getLocation(), 20);
		List<Entity> entities = player.getNearbyEntities(20, 20, 20);
		for(Entity entity : entities) {
			if(sphere.contains(entity.getLocation()) && entity instanceof LivingEntity) {
				LivingEntity e = (LivingEntity) entity;
				e.damage(100, player);
			}
		}
		MPlayer.setSubAnima(player, 0);
	}

	@Override
	public String getComboDescription() {
		return "Deals extra damage to attacks";
	}

}
