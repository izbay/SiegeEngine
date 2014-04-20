package com.github.izbay.siegeengine;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class WeaponEventObserver implements Listener {

	@EventHandler
	private void collideHandler(VehicleBlockCollisionEvent e) {
		//TODO: Check permissions and weapon type in the future and route to the correct event.
		Ram.collide(e);
	}

	@EventHandler
	public void ramMoveHandler(VehicleMoveEvent e) {
		//TODO: Check permissions and weapon type in the future and route to the correct event.
		Ram.move(e);
	}
	
	@EventHandler
	public void rightClickHandler(PlayerInteractEntityEvent e){
		//TODO: Check permissions and weapon type in the future and route to the correct event.
		if (e.getRightClicked().getType() == EntityType.MINECART)
			Ram.click(e);
	}
}
