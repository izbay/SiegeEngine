//Brandon's test#2 - if you can see this, message me with something awesome! it means I've figured out this whole push/commit business B]
// --You succeeded.  --JJ-S
package com.github.izbay.siegeengine;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;


public class SiegeEnginePlugin extends JavaPlugin {
	public static final Material RAM_LOWER_MATERIAL = Material.MINECART;
	// TODO: Change to differentiate rams from reg'lar carts:
	public static final EntityType RAM_VEHICLE_ENTITY = EntityType.MINECART; 
	
	@Override
	public void onDisable() {
		
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new WeaponListener(), this);
		getServer().getPluginManager().registerEvents(new RamMoveListener(), this);
	}
	
}
