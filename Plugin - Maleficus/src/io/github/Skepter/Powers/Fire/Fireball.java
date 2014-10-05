package io.github.Skepter.Powers.Fire;

import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType;
import io.github.Skepter.Powers.PowerType.Element;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Fireball implements Power, Listener {

	@Override
	public String getName() {
		return "Fireball";
	}

	@Override
	public String getDescription() {
		return "Fire fireballs like ghasts do!";
	}

	@Override
	public int getAnima() {
		return 100;
	}
	
	@Override
	public Element getElement() {
		return PowerType.Element.FIRE;
	}

	@Override
	public void run(Player player, String params) {
		player.launchProjectile(org.bukkit.entity.Fireball.class).setVelocity(player.getLocation().getDirection());
		/*case "beserker":
			MPlayer.takeAnima(player, getAnima());
			org.bukkit.entity.Fireball fireball = player.launchProjectile(org.bukkit.entity.Fireball.class);
			fireball.setVelocity(player.getLocation().getDirection().multiply(2));
			fireball.setYield(8F);*/
		return;
	}

}
