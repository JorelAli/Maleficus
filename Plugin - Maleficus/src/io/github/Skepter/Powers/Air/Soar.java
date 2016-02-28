package io.github.skepter.powers.air;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import io.github.skepter.Maleficus;
import io.github.skepter.powers.Power;
import io.github.skepter.powers.PowerType.Element;

public class Soar implements Power{
	
	Map<String, Integer> map = new HashMap<String, Integer>();
	int taskID;

	@Override
	public String getName() {
		return "Soar";
	}

	@Override
	public String getDescription() {
		return "Fly!";
	}

	@Override
	public Element getElement() {
		return Element.AIR;
	}

	@Override
	public int getAnima() {
		return 100;
	}

	@Override
	public void run(final Player player, String params) {
		if(player.getGameMode().equals(GameMode.CREATIVE)) {
			return;
		}
		player.setAllowFlight(true);
		if(!map.containsKey(player.getName())) {
			map.put(player.getName(), 60);
		} else {
			int x = map.get(player.getName()); 
			map.put(player.getName(), x + 60);
		}
		taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Maleficus.getInstance(), new Runnable() {
			@Override
			public void run() {
				map.put(player.getName(), map.get(player.getName()) - 1);
				player.sendMessage("Time left: " + map.get(player.getName()));
				if(map.get(player.getName()) == 0) {
					Bukkit.getScheduler().cancelTask(taskID);
				}
			}
		}, 0, 20);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Maleficus.getInstance(), new Runnable() {
			@Override
			public void run() {
				player.setAllowFlight(false);
				map.put(player.getName(), 0);
			}
		}, 20 * (map.get(player.getName()) == 0 ? 60 : map.get(player.getName())));
	}

}
