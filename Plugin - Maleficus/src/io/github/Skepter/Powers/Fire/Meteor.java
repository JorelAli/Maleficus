package io.github.Skepter.Powers.Fire;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

public class Meteor implements Power{

	@Override
	public String getName() {
		return "Meteor";
	}

	@Override
	public String getDescription() {
		return "Fires a really powerful meteor at the target location!";
	}

	@Override
	public Element getElement() {
		return Element.FIRE;
	}

	@Override
	public int getAnima() {
		return 10000;
	}

	@Override
	public void run(Player player, String params) {		
		int distance = 30;
		float meteorPower = 4.0F;
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 126));
		World world = player.getWorld();
		Vector location = player.getLocation().toVector();
		Vector direction = player.getLocation().getDirection();
		BlockIterator it = new BlockIterator(world, location, direction, 0, 20);
		int i = 0;
		while(it.hasNext()) {
			Block b = it.next();
			if(i == distance) {
				break;
			} else {
				b.getWorld().createExplosion(b.getLocation(), meteorPower, true);
			}
		}
	}

}
