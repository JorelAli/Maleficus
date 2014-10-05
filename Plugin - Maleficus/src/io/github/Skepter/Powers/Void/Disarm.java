package io.github.Skepter.Powers.Void;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;
import io.github.Skepter.Tools.Sphere;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Disarm implements Power{

	@Override
	public String getName() {
		return "Disarm";
	}

	@Override
	public String getDescription() {
		return "Drops the item in player's hands";
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
		Sphere sphere = new Sphere(player.getLocation(), 20);
		List<Entity> entities = player.getNearbyEntities(20, 20, 20);
		for(Entity entity : entities) {
			if(sphere.contains(entity.getLocation()) && entity instanceof Player) {
				Player t = ((Player) entity);
				t.getWorld().dropItemNaturally(t.getLocation(), t.getItemInHand());
			}
		}
	}

}
