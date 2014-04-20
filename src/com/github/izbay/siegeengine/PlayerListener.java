package com.github.izbay.siegeengine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.izbay.util.Util;

public class PlayerListener implements Listener {

	/**
	 * @param e A player blockplace event.
	 * 
	 * Post-condition: Checks for completion of a weapon recipe.
	 * 	If sufficient permissions apply, creates a weapon.
	 */
	@EventHandler
	private void spawnWeapon(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			Location target = e.getClickedBlock().getLocation().add(0, 0, 0);

			/** Battering Ram */
			if (e.getClickedBlock().getType() == Material.ANVIL
				&& Util.getBlockBelow(e.getClickedBlock()).getType() == Material.CAULDRON
				&& p.getItemInHand().getType() == Material.FLINT_AND_STEEL)
			{
				e.getClickedBlock().setType(Material.AIR);
				e.getClickedBlock().getLocation().add(0, -1, 0).getBlock().setType(Material.AIR);
				Ram.spawn(target);
				
			}// Ram
		}// if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
	}// spawnWeapon method
}// listener class
