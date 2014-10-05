package io.github.Skepter.Powers.Light;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;
import io.github.Skepter.Tools.ParticleEffect;
import io.github.Skepter.Tools.Sphere;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

public class Burst implements Power {

	@Override
	public String getName() {
		return "Burst";
	}

	@Override
	public String getDescription() {
		return "Make mobs fly away!";
	}

	@Override
	public Element getElement() {
		return Element.LIGHT;
	}

	@Override
	public int getAnima() {
		return 10000;
	}

	@Override
	public void run(Player player, String params) {
		Location loc1 = player.getLocation();
		Vector from = new Vector(loc1.getX(), loc1.getY(), loc1.getZ());
		Sphere sphere = new Sphere(player.getLocation(), 20);
		List<Entity> entities = player.getNearbyEntities(20, 20, 20);
		for (Entity entity : entities) {
			if (sphere.contains(entity.getLocation()) && entity instanceof LivingEntity) {
				Location loc2 = entity.getLocation().add(0, 3, 0);
				Vector to = new Vector(loc2.getX(), loc2.getY(), loc2.getZ());
				Vector v = to.subtract(from);
				BlockIterator it = new BlockIterator(entity.getWorld(), entity.getLocation().toVector(), v, 0, 20);
				while (it.hasNext()) {
					Block b = it.next();
					ParticleEffect.CLOUD.display(b.getLocation(), 0, 0, 0, 1, 1);
				}
				entity.setVelocity(v.multiply(2D));
				((LivingEntity) entity).damage(1);
			}
		}
	}
}
