package io.github.Skepter;

import io.github.Skepter.Other.MPlayer;
import io.github.Skepter.Powers.Power;
import io.github.Skepter.Powers.PowerType.Element;
import io.github.Skepter.Powers.Air.Collect;
import io.github.Skepter.Powers.Air.Soar;
import io.github.Skepter.Powers.Dark.Annihilate;
import io.github.Skepter.Powers.Dark.Blinder;
import io.github.Skepter.Powers.Dark.VanishingWorld;
import io.github.Skepter.Powers.Fire.BurningWorld;
import io.github.Skepter.Powers.Fire.Fireball;
import io.github.Skepter.Powers.Fire.LavaCannon;
import io.github.Skepter.Powers.Fire.Meteor;
import io.github.Skepter.Powers.Light.AntiPenetrate;
import io.github.Skepter.Powers.Light.Burst;
import io.github.Skepter.Powers.Shock.Bolt;
import io.github.Skepter.Powers.Shock.Paralyse;
import io.github.Skepter.Powers.Shock.Teravolt;
import io.github.Skepter.Powers.Void.Disarm;
import io.github.Skepter.Powers.Void.Eradicate;
import io.github.Skepter.Powers.Water.FreezeCannon;
import io.github.Skepter.SubElements.AntiAnima;
import io.github.Skepter.SubElements.Beserker;
import io.github.Skepter.SubElements.Ender;
import io.github.Skepter.SubElements.Healer;
import io.github.Skepter.SubElements.SubElements;
import io.github.Skepter.commands.AnimaCommand;
import io.github.Skepter.commands.MaleficusCommand;
import io.github.Skepter.commands.PowerCommand;
import io.github.Skepter.listeners.PassivesListeners;
import io.github.Skepter.listeners.PlayerListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Maleficus extends JavaPlugin {

	/*
	 * Maleficus Means wizard in Latin
	 */

	/*
	 * Commands
	 * 
	 * 
	 * 
	 * /maleficus - displays plugin help
	 * 
	 * /power - displays power help /power list - displays all powers
	 * (elements), with green and red indicator /power current - displays
	 * current powers, how much damange/mana they do - what powers can be
	 * unlocked /power info <power> - learn info about a power /power select
	 * <power> - learn a power - drains anima to 0 and resets max to default
	 * /power bind <power> - bind a power to a wand /power unbind - unbind a
	 * power from a wand
	 * 
	 * modify the system so that everything is element based, and the powers are
	 * based on what element you choose. In other words, when they join the
	 * game, havw it so that they choose an element. This then lets them to use
	 * /power list to see the list of all powers which are specific to that
	 * element (add /power list all/<elementName>), defaulting /power list to
	 * the element they are If they are a specific element, using the
	 * power/killing mobs/players etc. allow for levelling up. After levelling
	 * up, they can use more powers (as they have more anima). Levels do not
	 * unlock new powers, only anima does. If a player wants to choose another
	 * element, it saves their progress of the element which they had before and
	 * sets it to the new element. If that new element is brand new (never been
	 * used by that player before), it will have 0/0 stats. Otherwise, it would
	 * load the old stats. If a player's element is NONE, they can use no
	 * powers. People from other powers can use those of the other power, except
	 * it requires 3 times more anima to execute.
	 * 
	 * /anima - see how much energy you have
	 * 
	 * /subelement - displays subelement help / /subelement select <subelement>
	 * - learn a subelement - drains subanima to 0 and resets max to default
	 * /combo <power> <subelement> - casts a combo attack - used when anima and
	 * subanima are 100%
	 */

	/*
	 * Permissions
	 * 
	 * power.learn power.use
	 * 
	 * power.fire power.water power.dark power.shock power.light power.earth
	 * power.air
	 * 
	 * power.ex power.z
	 */

	/*
	 * Elements: Light Dark Water Fire Earth Air EX Z Shock Ender??
	 */

	/*
	 * SubElements: Beserker - does extra damage with short range attacks Archer
	 * - does extra damage with long range attacks Explosions - causes
	 * explosions Healer - heals Scribe //?? Speed - less anima usage, weak
	 * attacks Strength - more anima usage, strong attacks AntiAnima - Only uses
	 * 50% anima for attack
	 */

	/*
	 * Element + SubElement combo powers: Use an element with a subelement to
	 * case a superattack e.g. FreezeCannon + Beserker does extra damange to
	 * enemies nearby, or archer goes further Used with /combo <power>
	 * <subelement>
	 */

	// needs more debug messGES SUCH AS you started flying or you collected <>
	// items

	private Map<String, Power> powerMap = new HashMap<String, Power>();
	private Map<String, SubElements> subMap = new HashMap<String, SubElements>();
	static Maleficus instance;

	
	//add wand recipe & add isWand() method
	public void onEnable() {
		instance = this;
		
		/* Powers */
		addToPowerMap(new Meteor());
		addToPowerMap(new VanishingWorld());
		addToPowerMap(new LavaCannon());
		addToPowerMap(new BurningWorld());
		addToPowerMap(new Fireball());
		addToPowerMap(new Burst());
		addToPowerMap(new Collect());
		addToPowerMap(new Soar());
		addToPowerMap(new FreezeCannon());
		addToPowerMap(new Blinder());
		addToPowerMap(new Teravolt());
		addToPowerMap(new Bolt());
		addToPowerMap(new Annihilate());
		addToPowerMap(new AntiPenetrate());
		addToPowerMap(new Eradicate());
		addToPowerMap(new Paralyse());
		addToPowerMap(new Disarm());
		
		/* Sub Powers */
		subMap.put("antianima", new AntiAnima());
		subMap.put("beserker", new Beserker());
		subMap.put("healer", new Healer());
		subMap.put("ender", new Ender());

		saveDefaultConfig();
		File dataFolder = new File(getDataFolder(), "Players");
		dataFolder.mkdirs();

		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new PassivesListeners(), this);

		getCommand("anima").setExecutor(new AnimaCommand(this));
		getCommand("maleficus").setExecutor(new MaleficusCommand());
		getCommand("power").setExecutor(new PowerCommand());

		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerListener.startClock(player);
		}

		getLogger().info("Maleficus " + getDescription().getVersion() + " activated!");
	}

	public static Maleficus getInstance() {
		return instance;
	}

	public void onDisable() {
		try {
			MPlayer.saveAllPlayers();
			Bukkit.getServer().getScheduler().cancelTasks(this);
			saveConfig();
			getLogger().info("Maleficus " + getDescription().getVersion() + " de-activated!");
		} catch (Exception e) {
		}
	}

	public Map<String, Power> getPowerMap() {
		return powerMap;
	}

	public List<String> getPowerMapValuesByElement() {
		List<String> s = new ArrayList<String>();
		for (Power p : powerMap.values()) {
			s.add(p.getElement().name() + Element.toChatColor(p.getElement()) + p.getName() + ChatColor.RESET + " - " + p.getDescription());
		}
		Collections.sort(s);
		List<String> s1 = new ArrayList<String>();
		for (String str : s) {
			for (Element e : Element.values()) {
				if(!str.contains(e.name())) {
					continue;
				}
				s1.add(str.replaceAll(e.name(), ""));
			}
		}
		return s1;
	}

	public Map<String, SubElements> getSubMap() {
		return subMap;
	}

	public void addToPowerMap(Power p) {
		getPowerMap().put(p.getName().toLowerCase().replaceAll(" ", ""), p);
	}
	
}
