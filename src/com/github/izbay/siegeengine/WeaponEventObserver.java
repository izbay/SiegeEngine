package com.github.izbay.siegeengine;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class WeaponEventObserver implements Listener {
	
	private String getWeaponType(Entity entity){
		// Catch if the metadata isn't set.
		if(!entity.hasMetadata("Custom Entity")){return null;}
		
		return entity.getMetadata("Custom Entity").get(0).asString();
	}
	
	@EventHandler
	private void collideHandler(VehicleBlockCollisionEvent e) {
		String type = getWeaponType(e.getVehicle());
		//System.out.println(type);
		
		if(type != null && type.equals(Weapon.types.Ram.getName()))
			Ram.collide(e);
	}

	@EventHandler
	public void ramMoveHandler(VehicleMoveEvent e) {
		String type = getWeaponType(e.getVehicle());
		//System.out.println(type);
		
		if(type != null && type.equals(Weapon.types.Ram.getName()))
			Ram.move(e);
	}
	
	@EventHandler
	public void rightClickHandler(PlayerInteractEntityEvent e){
		String type = getWeaponType(e.getRightClicked());
		//System.out.println(type);
		
		if(type != null && type.equals(Weapon.types.Ram.getName()))
			Ram.click(e);
	}
}
