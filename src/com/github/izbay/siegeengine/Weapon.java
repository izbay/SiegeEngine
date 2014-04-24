package com.github.izbay.siegeengine;

/*import net.minecraft.server.v1_7_R3.EntityMinecartAbstract;
import net.minecraft.server.v1_7_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftMinecart;*/
import net.minecraft.server.v1_7_R1.EntityMinecartAbstract;
import net.minecraft.server.v1_7_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftMinecart;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Minecart;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;

import com.github.izbay.regengine.RegEnginePlugin;

abstract class Weapon {
	static RegEnginePlugin reg = (RegEnginePlugin) Bukkit.getServer().getPluginManager().getPlugin("RegEngine");

	//TODO: Create Wheelbarrow with (Material.SAPLING, 0, Sound.ZOMBIE_WOOD).

	/** Abstract Methods */
	static void spawn(Location loc) { }
	static void move(VehicleMoveEvent e) { }

	static void collide(VehicleBlockCollisionEvent e) { }

	static void click(PlayerInteractEntityEvent e) { }

	/**
	 * @param minecart The cart entity to change.
	 * @param graphic The block material type to place within the cart.
	 * @param offset How high the block inside the cart should be offset upwards.
	 * 
	 * Post-condition: The cart passed in has it's NBT data altered to change the
	 *  material within the cart to the specified graphic material.
	 */
	@SuppressWarnings("deprecation")
	static protected void setData(Minecart minecart, Material graphic,
			Integer offset) {
		EntityMinecartAbstract rawMinecart = ((CraftMinecart) minecart)
				.getHandle();
		NBTTagCompound minecartTag = getCompound(rawMinecart);

		minecartTag.setByte("CustomDisplayTile", (byte) 1);
		minecartTag.setInt("DisplayTile", graphic.getId());
		minecartTag.setInt("DisplayOffset", offset);
		rawMinecart.f(minecartTag);
	}
	
	/**
	 * @param target The cart to grab NBT data from.
	 * @return The NBT data of the passed in cart.
	 */
	static private NBTTagCompound getCompound(EntityMinecartAbstract target) {
		NBTTagCompound tag = new NBTTagCompound();
		target.c(tag);
		return tag;
	}
	
	/**
	 * @param loc The location at which the generated sounds should eminate from.
	 */
	static protected void breakSound(Location loc) {
		Material mat = loc.getBlock().getType();
		if (mat == Material.AIR)
			return;
		String str = mat.toString();
		Sound sound = Sound.ITEM_BREAK;

		//TODO: Find a more efficient way to do this!
		
		if (loc.getBlock().isLiquid())
			sound = Sound.SPLASH;
		else if (str.contains("WOOD") || str.contains("LOG"))
			sound = Sound.ZOMBIE_WOODBREAK;
		else if (str.contains("STONE") || str.contains("SMOOTH")
				|| str.contains("ORE") || str.contains("BRICK")
				|| str.contains("ROCK")) {
			loc.getWorld().playSound(loc, Sound.EXPLODE, 1, 1);
			sound = Sound.FUSE;
		} else if (str.contains("GLASS"))
			sound = Sound.GLASS;
		else if (str.contains("GRASS") || str.contains("DIRT")
				|| str.contains("SAND") || str.contains("GRAVEL"))
			sound = Sound.FUSE;
		else if (str.contains("RAIL") || str.contains("ANVIL")
				|| str.contains("HOPPER") || str.contains("CAULDRON")
				|| str.contains("IRON") || str.contains("GOLD")
				|| str.contains("DIAMOND") || str.contains("ICE"))
			sound = Sound.ANVIL_LAND;
		else if (str.contains("FENCE") || str.contains("TRAP_DOOR")
				|| str.contains("WORKBENCH") || str.contains("SAPLING")
				|| str.contains("CHEST") || str.contains("BUSH")
				|| str.contains("BOOK") || str.contains("SIGN")
				|| str.contains("LADDER"))
			sound = Sound.ZOMBIE_WOODBREAK;
		else if (str.contains("QUARTZ") || str.contains("MONSTER")
				|| str.contains("RACK") || str.contains("FURNACE")
				|| str.contains("OBSIDIAN") || str.contains("EMERALD")
				|| str.contains("LAPIS") || str.contains("DISPENSER")
				|| str.contains("DROPPER") || str.contains("PISTON")
				|| str.contains("STEP")) {
			loc.getWorld().playSound(loc, Sound.EXPLODE, 1, 1);
			sound = Sound.FUSE;
		} else if (str.contains("SUGAR") || str.contains("VINE")
				|| str.contains("SNOW") || str.contains("SOIL")
				|| str.contains("MYCEL") || str.contains("CROPS")
				|| str.contains("FLOWER") || str.contains("ROSE")
				|| str.contains("MUSHROOM") || str.contains("LEAVES"))
			sound = Sound.FUSE;
		else if (str.contains("CACTUS") || str.contains("WOOL")
				|| str.contains("HAY") || str.contains("CARPET")
				|| str.contains("HAY") || str.contains("WARTS")
				|| str.contains("REDSTONE") || str.contains("DIODE")
				|| str.contains("CLAY") || str.contains("CAKE")
				|| str.contains("PUMPKIN") || str.contains("JACK")
				|| str.contains("MELON") || str.contains("LILY")
				|| str.contains("SPONGE"))
			sound = Sound.CREEPER_DEATH;

		loc.getWorld().playSound(loc, sound, 1, 1);
	}
}
