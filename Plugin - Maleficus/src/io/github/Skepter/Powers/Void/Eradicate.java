package io.github.Skepter.Powers.Void;

import io.github.Skepter.Maleficus;
import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;
import io.github.Skepter.Tools.Line;
import io.github.Skepter.Tools.ParticleEffect;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Eradicate implements Power{

	@Override
	public String getName() {
		return "Eradicate";
	}

	@Override
	public String getDescription() {
		return "IDK";
	}

	@Override
	public Element getElement() {
		return Element.VOID;
	}

	@Override
	public int getAnima() {
		return 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(final Player player, String params) {
		//adds anima?
		//does extra damage to Void attacks?
		//IDK!
		Line line = new Line(player.getLocation(), player.getTargetBlock(null, 256).getLocation());
		final Iterator<Block> blockIterator = line.iterator();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Maleficus.getInstance(), new Runnable() {

			@Override
			public void run() {
				//possibly create like a spirally sort of particle effect. May make references to the EffectLib
				//player.getLocation().getChunk().
				if(blockIterator.hasNext()) {
					ParticleEffect.PORTAL.display(blockIterator.next().getLocation(), 0, 0, 0, 2, 50);
				}
			}
			
		}, 0, 5);
		player.sendMessage("Eradicate successful");
	}

}
