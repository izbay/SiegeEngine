/**
 * 
 */
package com.github.izbay.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

/**
 * @author jdjs
 *
 */
/**
 * @author J. Jakes-Schauer
 *
 */
public class Util 
{
		public static double toRadians(double yaw){
			return (270-yaw) * Math.PI / 180;
		}
		
		public static BlockVector getBlockVector(final Location l) {
			return new BlockVector((int) l.getX(), (int) l.getY(),
					(int) l.getZ());
		}// getBlockVector()

		/**
		 * @param n1
		 * @param n2
		 * @return  ∈ {-1,0,1}
		 */
		public static int compare(final double n1, final double n2) {
			if (n1 == n2)
				return 0;
			else if (n1 < n2)
				return -1;
			else
				return 1;
		}// compare()

		/**
		 * Imposes a monotonic order on Vectors, with the Y-axis taking highest priority, then the Z- (row-major).
		 * @param n1
		 * @param n2
		 * @return  ∈ {-1,0,1}
		 */
		public static int compare(final Vector v1, final Vector v2) {
			final int y = compare(v1.getY(), v2.getY());
			if (y == 0) {
                final int z = compare(v1.getZ(), v2.getZ());
                if (z == 0)
					return compare(v1.getX(), v2.getX());
				else
					return z;
			}// if
			else
				return y;
		}// compare()
		
		public static boolean equal(final Vector v1, final Vector v2)
		{	return compare(v1,v2) == 0; }// equal()
		
		public static boolean isSolid(final Block b)
		{	return b.getType().isSolid(); }// isSolid()
		
		public static World getCurrentWorld()
		{ return Bukkit.getServer().getWorlds().get(0); }
		
		public static Block getBlockAt(final Location l)
		{ return getCurrentWorld().getBlockAt(l); }

		public static Block getBlockAt(final Vector v)
		{	return getCurrentWorld().getBlockAt( v.getBlockX(), v.getBlockY(), v.getBlockZ()); }
		
		public static Location getLocation(final Vector v)
		{ 	return v.toLocation(getCurrentWorld()); }

		public static Location getLocation(final Block b)
		{ 	return b.getLocation(); }
		
		public static Location normalizeLocation(final Location l)
		{	return new Location(l.getWorld(), Math.round(l.getX()), Math.round(l.getY()), Math.round(l.getZ()));}
		
		/**
		 * Non-mutating Vector addition.
		 * @return 
		 */
		public static Vector add(final Vector v, final double x, final double y, final double z)
		{	return new Vector(v.getX()+x, v.getY()+y, v.getZ()+z); }

		public static BlockVector add(final BlockVector v, final int x, final int y, final int z)
		{	return new BlockVector(v.getX()+x, v.getY()+y, v.getZ()+z); }
		
		public static Block getBlockBelow(final Vector v)
		{ return getBlockAt(Util.add(v, 0, -1, 0)); }
		
		public static Block getBlockBelow(final Block b)
        { return getBlockAt(new Vector(b.getX(), b.getY()-1, b.getZ())); }
		
		private Util() {}
}// Util
