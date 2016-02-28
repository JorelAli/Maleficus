package io.github.skepter.powers;

import org.bukkit.entity.Player;

public abstract interface Power {
	
	public abstract String getName();
	
	public abstract String getDescription();
	
	public abstract PowerType.Element getElement();
	
	public abstract int getAnima(); //how much energy the power uses
		
	public abstract void run(Player player, String params);
}
