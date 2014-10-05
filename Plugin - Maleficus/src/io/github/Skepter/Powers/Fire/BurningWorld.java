package io.github.Skepter.Powers.Fire;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType;
import io.github.Skepter.Powers.PowerType.Element;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class BurningWorld implements Power{

	@Override
	public String getName() {
		return "Burning World";
	}

	@Override
	public String getDescription() {
		return "Burns everything around you!";
	}

	@Override
	public int getAnima() {
		return 250;
	}

	@Override
	public void run(Player player, String params) {
		int radius = 20;
		Block block = player.getLocation().getBlock();
	    List<Block> bList = new ArrayList<Block>();
	    for (int x = -radius; x <= radius; x++) {
	      for (int y = -radius; y <= radius; y++) {
	        for (int z = -radius; z <= radius; z++) {
	          Block b = block.getRelative(x, y, z);

	          BlockBreakEvent e = new BlockBreakEvent(b, player);
	          Bukkit.getPluginManager().callEvent(e);
	          if (e.isCancelled()) {
	            return;
	          }
	          if ((b.getRelative(BlockFace.DOWN).getType() == Material.AIR) || (b.getType() != Material.AIR) || (b.getLocation().distance(block.getLocation()) <= 2.0D) || (b.getLocation().distance(block.getLocation()) >= radius)) {
	            continue;
	          }
	          bList.add(b);
	        }
	      }
	    }

	    for (Block b : bList) {
	      b.setType(Material.FIRE);
	    }
	}

	@Override
	public Element getElement() {
		return PowerType.Element.FIRE;
	}
}
