package io.github.skepter.powers.thevoid;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import io.github.skepter.Maleficus;
import io.github.skepter.powers.Power;
import io.github.skepter.powers.PowerType.Element;
import io.github.skepter.tools.Line;
import io.github.skepter.tools.ParticleEffect;
import io.github.skepter.tools.Utils;

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

	@Override
	public void run(final Player player, String params) {
		//adds anima?
		//does extra damage to Void attacks?
		//IDK!
		Line line = new Line(player.getLocation(), Utils.getTargetBlock(player).getLocation());
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
