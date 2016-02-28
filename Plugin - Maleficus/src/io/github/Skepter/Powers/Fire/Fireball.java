package io.github.skepter.powers.fire;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import io.github.skepter.powers.Power;
import io.github.skepter.powers.PowerType;
import io.github.skepter.powers.PowerType.Element;

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
