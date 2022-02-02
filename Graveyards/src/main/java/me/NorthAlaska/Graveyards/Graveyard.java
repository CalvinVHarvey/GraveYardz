package me.NorthAlaska.Graveyards;

import org.bukkit.Location;

public class Graveyard {
	
	//Cords for the spawn point of the grave-yard
	private int x;
	private int y;
	private int z;
	
	private String world;
	
	private Location loc;
	
	//General Information for the Grave Yard
	private String name;
	private int id;
	
	public Graveyard(String name, int id, Location loc) {
		this.loc = loc;
		this.x = loc.getBlockX();
		this.y = loc.getBlockY();
		this.z = loc.getBlockZ();
		this.name = name;
		this.id = id;
		this.world = loc.getWorld().getName();
	}
	
	//Getter Methods
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public String getName() {
		return name;
	}
	
	public String getWorld() {
		return world;
	}
	
	public int getID() {
		return id;
	}
	
	//Setter Methods
	public void setLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setLocation(Location loc) {
		this.loc = loc;
		setLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		setWorld(loc.getWorld().getName());
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public void setWorld(String name) {
		world = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	//Setter Methods
	
	
	@Override
	public String toString() {
		return name + " Grave Yard\nID: " +id + "\nAt Location: " +x +", " +y +", " +z; 
	}
	
}
