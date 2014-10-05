package io.github.Skepter.Powers.Fire;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType;
import io.github.Skepter.Powers.PowerType.Element;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

public class LavaCannon implements Power{

	@Override
	public String getName() {
		return "LavaCannon";
	}

	@Override
	public String getDescription() {
		return "Fire a line of lava";
	}

	@Override
	public int getAnima() {
		return 500;
	}
	
	@Override
	public Element getElement() {
		return PowerType.Element.FIRE;
	}

	@Override
	public void run(Player player, String params) {
		World world = player.getWorld();
		Vector location = player.getLocation().toVector();
		Vector direction = player.getLocation().getDirection();
		BlockIterator it = new BlockIterator(world, location, direction, 0, 10);
		int i = 0;
		while(it.hasNext()) {
			Block b = it.next();
			i++;
			if(!(b.getType() == Material.AIR)) {
				break;
			} else {
				if(i > 2) {
					b.setType(Material.LAVA);
				}
			}
		}
	}

}
