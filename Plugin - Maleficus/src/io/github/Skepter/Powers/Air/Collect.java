package io.github.Skepter.Powers.Air;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;
import io.github.Skepter.Tools.Sphere;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class Collect implements Power {

	@Override
	public String getName() {
		return "Collect"; // 
	}

	@Override
	public String getDescription() {
		return "Brings items to you!";
	}

	@Override
	public Element getElement() {
		return Element.AIR;
	}

	@Override
	public int getAnima() {
		return 100;
	}

	@Override
	public void run(Player player, String params) {
		Sphere sphere = new Sphere(player.getLocation(), 20);
		List<Entity> entities = player.getNearbyEntities(20, 20, 20);
		for (Entity entity : entities) {
			if (sphere.contains(entity.getLocation())) {
				if (entity instanceof Item) {
					if (player.getInventory().firstEmpty() == -1) {
						return;
					}
					player.getInventory().addItem(((Item) entity).getItemStack());
					entity.remove();
				}
			}
		}
	}
}
