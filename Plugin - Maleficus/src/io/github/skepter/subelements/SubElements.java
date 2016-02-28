package io.github.skepter.subelements;

import org.bukkit.entity.Player;

public abstract interface SubElements {

	public abstract String getName();
	
	public abstract String getDescription();
	
	public abstract String getComboDescription();
		
	public abstract void run(Player player);
}
