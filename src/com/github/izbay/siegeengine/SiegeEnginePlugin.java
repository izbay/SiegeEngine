package com.github.izbay.siegeengine;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class SiegeEnginePlugin extends JavaPlugin {
	private static SiegeEnginePlugin instance;
	
	/**
	 * @return the RegEnginePlugin singleton
	 */
	public static SiegeEnginePlugin getInstance() {
		assert(instance != null);
		return instance; 
	}
	
	public static final Material RAM_LOWER_MATERIAL = Material.MINECART;
	// TODO: Change to differentiate rams from reg'lar carts:
	public static final EntityType RAM_VEHICLE_ENTITY = EntityType.MINECART;
	
	/**
	 * Default constructor; sets class singleton ref.
	 */
	public SiegeEnginePlugin()
	{
		super();
		instance = this;
	}// ctor
	
	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new WeaponEventObserver(), this);
	}
}
