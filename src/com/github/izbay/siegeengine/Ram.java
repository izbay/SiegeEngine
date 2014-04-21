package com.github.izbay.siegeengine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import com.github.izbay.util.Util;

public class Ram extends Weapon {
	
	private static Material graphic = Material.ANVIL;
	private static Integer offset = 5;
	private static Sound spawnSound = Sound.IRONGOLEM_DEATH;

	/**
	 * @param loc The location at which the new Ram should spawn.
	 * 
	 * Post-condition: A ram is spawned in the world at the passed in location.
	 */
	static void spawn(Location loc) {
		loc.setYaw(0);
		Minecart minecart = (Minecart) loc.getWorld().spawnEntity(loc,
				EntityType.MINECART);
		setData(minecart, graphic, offset);
		loc.getWorld().playSound(loc, spawnSound, 1, 1);
	}// spawn method
	
	/**
	 * @param e The move event forwarded from the WEO
	 * 
	 * Post-condition: Rails are placed on up/downhill inclines
	 */
	static void move(VehicleMoveEvent e) {
		if (e.getVehicle().getType() == SiegeEnginePlugin.RAM_VEHICLE_ENTITY) {
			final BlockVector vFrom = Util.getBlockVector(e.getFrom());
			final BlockVector vTo = Util.getBlockVector(e.getTo());

			assert (vFrom.getX() == vTo.getX() || vFrom.getZ() == vTo.getZ());

			if (vFrom.getY() == vTo.getY()
					&& (vFrom.getX() == vTo.getX() || vFrom.getZ() == vTo
							.getZ())
					&& !Util.equal(vFrom, vTo)
					&& Util.isSolid(Util.getBlockBelow(vFrom, e.getVehicle().getWorld()))
					&& !Util.isSolid(Util.getBlockBelow(vTo, e.getVehicle().getWorld()))
					&& Util.isSolid(Util.getBlockBelow(Util.getBlockBelow(vTo, e.getVehicle().getWorld())))) {
				//reg.alter(vFrom, Material.RAILS);
				//reg.alter(Util.getBlockBelow(vTo), Material.RAILS);
				
			}// if
		}// if vehicle is Ram
	}// move method

	/**
	 * @param e The collision event forwarded from the WEO
	 * 
	 * Post-condition: A damage area is computed and passed to RegEngine for destruction.
	 */
	static void collide(VehicleBlockCollisionEvent e) {
		// TODO: Minecart/Weapon Type differentiation.
		if (e.getVehicle().getType() == EntityType.MINECART) {
			Location v = e.getVehicle().getLocation();
			Location upLoc = Util.add(e.getBlock().getLocation(), 0, 1, 0);
			if (v.getBlock().getType().isSolid()) {
				e.getVehicle().teleport(v.add(0, 1, 0));
			}
			if (e.getBlock().getLocation().getBlock().getType().isSolid()) {
				if (!upLoc.getBlock().getType().isSolid()) {
					reg.alter(SiegeEnginePlugin.getInstance(), v, Material.RAILS);
					reg.alter(SiegeEnginePlugin.getInstance(), upLoc, Material.RAILS);
				} else {
					// Generate a small cone. We'll worry about multipliers and
					// non-right angles later.
					Double yaw = Util.toRadians(v.getYaw());
					int sin = (int) Math.round(Math.sin(yaw));
					int cos = (int) Math.round(Math.cos(yaw));
					Vector[] vec = {
							new Vector(0, 0, 0),
							// Up Down
							new Vector(0, 1, 0),
							new Vector(0, -1, 0),
							// Left Right
							new Vector(0, 0, -sin), new Vector(-cos, 0, 0),
							new Vector(cos, 0, 0), new Vector(0, 0, sin),
							// Forward
							new Vector(sin, 0, 0), new Vector(sin * 2, 0, 0),
							new Vector(0, 0, cos), new Vector(0, 0, cos * 2) };

					// TODO: Add a delay and sounds.
					for (int i = 0; i < vec.length; i++) {
						Location test = new Location(upLoc.getWorld(),
								upLoc.getX(), upLoc.getY(), upLoc.getZ());
						test = test.add(vec[i]);
						breakSound(test);
						reg.alter(SiegeEnginePlugin.getInstance(), test, Material.AIR);

					}// for-loop to destroy area
				}// else (not a slope for rails)
			}// if is solid block
		}// if is minecart
	}// collide method

	static void click(PlayerInteractEntityEvent e) {
		e.setCancelled(true);
		// TODO: Move the climb slope code to here. Take it out of collision for controllability.
	}// click method
}// ram class
