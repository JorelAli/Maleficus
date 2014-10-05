package io.github.Skepter.Powers.Dark;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;
import io.github.Skepter.Tools.ParticleEffect;
import io.github.Skepter.Tools.Sphere;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class VanishingWorld implements Power {

	@Override
	public String getName() {
		return "Vanishing World";
	}

	@Override
	public String getDescription() {
		return "Destroys the world. Proceed with caution!";
	}

	@Override
	public Element getElement() {
		return Element.DARK;
	}

	@Override
	public int getAnima() {
		return 10000;
	}

	@Override
	public void run(Player player, String params) {
		Sphere sphere = new Sphere(player.getLocation(), 15);
		ArrayList<Block> blocks = sphere.getBlocks();
		Material[] exemptMat = { Material.BEDROCK, Material.LOG };
		List<Material> exempt = Arrays.asList(exemptMat);
		for (Block b : blocks) {
			Random random = new Random();
			if (exempt.contains(b.getType()) || !(random.nextInt(3) == 1)) {
				continue;
			}
			b.setType(Material.AIR);
		}
		for (Block b : sphere.getOuterLayerBlocks()) {
			ParticleEffect.PORTAL.display(b.getLocation(), 0, 0, 0, 0, 5);
		}
		return;
	}

}
