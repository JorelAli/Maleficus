package io.github.Skepter.Powers.Shock;

import io.github.Skepter.Maleficus;
import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;
import io.github.Skepter.Tools.Sphere;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Teravolt implements Power{

	@Override
	public String getName() {
		return "Teravolt";
	}

	@Override
	public String getDescription() {
		return "Smites all enemies around you";
	}

	@Override
	public Element getElement() {
		return Element.SHOCK;
	}

	@Override
	public int getAnima() {
		return 1000;
	}

	@Override
	public void run(final Player player, String params) {
		final int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Maleficus.getInstance(), new Runnable() {
			@Override
			public void run() {
				Sphere sphere = new Sphere(player.getLocation(), 20);
				List<Entity> entities = player.getNearbyEntities(20, 20, 20);
				for(Entity entity : entities) {
					if(sphere.contains(entity.getLocation()) && entity instanceof LivingEntity) {
						player.getWorld().strikeLightning(entity.getLocation());
					}
				}
			}
		}, 0, 5);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Maleficus.getInstance(), new Runnable() {
			@Override
			public void run() {
				Bukkit.getScheduler().cancelTask(taskID);
			}
		}, 60);
	}

}
