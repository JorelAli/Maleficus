package io.github.skepter.listeners;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.skepter.other.MPlayer;
import io.github.skepter.powers.PowerType.Element;

public class PassivesListeners implements Listener {

	/*
	 * TDL:
	 * Research entityTouchEntityEvent or something similar to that name  for ShockElement*/
	
	/**
	 * @Element Earth
	 * @Element Fire
	 * @Element Water
	 */
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			if (MPlayer.getElement((Player) event.getEntity()).equals(Element.EARTH) && event.getCause().equals(DamageCause.FALL)) {
				event.setCancelled(true);
			} else if (MPlayer.getElement((Player) event.getEntity()).equals(Element.FIRE) && (event.getCause().equals(DamageCause.FIRE) || event.getCause().equals(DamageCause.FIRE_TICK))) {
				event.setCancelled(true);
			} else if (MPlayer.getElement((Player) event.getEntity()).equals(Element.WATER) && event.getCause().equals(DamageCause.DROWNING)) {
				event.setCancelled(true);
			}
		}
	}

	/**
	 * @Element Air
	 */
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if (MPlayer.getElement((Player) event.getPlayer()).equals(Element.AIR)) {
			event.getPlayer().setWalkSpeed(0.5F);
		} else {
			event.getPlayer().setWalkSpeed(0.2F);
		}
	}
	
	/**
	 * @Element Light
	 */
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if(MPlayer.getElement(event.getEntity()).equals(Element.LIGHT)) {
			Random random = new Random();
			if(random.nextBoolean()) {
				//TDL - Cancel event
			}
		}
	}
}
