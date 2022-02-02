package me.NorthAlaska.Graveyards;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.NorthAlaska.Graveyards.commands.*;
import me.NorthAlaska.Graveyards.events.*;
import me.NorthAlaska.Graveyards.utils.*;

public class Main extends JavaPlugin{
	private Configuration graveyards;
	private FileConfiguration graves;
	private ArrayList<Graveyard> graveyard;
	
	
	@Override
	public void onEnable() {
		new GVCommand(this);
		graveyards = new Configuration(this, this.getDataFolder(), "graveyards", true, true);
		graveyards.reload();
		graves = graveyards.getConfig();
		saveDefaultConfig();
		new DeathListener(this);
		this.getCommand("gv").setTabCompleter(new GVTabComplete());
		
		graveyard = new ArrayList<Graveyard>();
		
		for (String key : graves.getKeys(false)) {
			Bukkit.getConsoleSender().sendMessage(key);
			String name = graves.getConfigurationSection(key).getString("name");
			int id = graves.getConfigurationSection(key).getInt("ID");
			ConfigurationSection cur = graves.getConfigurationSection(key).getConfigurationSection("location");
			Location world = new Location(this.getServer().getWorld(cur.getString("world")), cur.getDouble("x"), cur.getDouble("y"), cur.getDouble("z"));
			world.setPitch((float)cur.getDouble("pitch"));
			world.setYaw((float)cur.getDouble("yaw"));
			Graveyard temp = new Graveyard(name, id, world);
			graveyard.add(temp);
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	//Getter Methods
	public ArrayList<Graveyard> getGraveyards(){
		return graveyard;
	}
	
	public boolean containsName(String name) {
		if (graveyard.size() <= 0 ) {
			Bukkit.getConsoleSender().sendMessage("There are no graveyards!");
			return false;
		}
		for (int i = 0; i < graveyard.size(); i++) {
			if (graveyard.get(i).getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false; 
	}
	
	public int findName(String name) {
		int temp = -1;
		for (int i = 0; i < graveyard.size(); i++) {
			if (graveyard.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return temp;
	}
	
	public int findWorldName(String world) {
		for (int i = 0; i < graveyard.size(); i++) {
			if (graveyard.get(i).getWorld().equalsIgnoreCase(world)) {
				return i;
			}
		}
		return -1;
	}
	
	public Graveyard findClosest(Player p) {
		Location loc = p.getLocation();
		int min = findWorldName(loc.getWorld().getName());
		if (min == -1) {
			return null;
		}
		for (int i = 0; i < graveyard.size(); i++) {
			Graveyard cur = graveyard.get(i);
			Graveyard minimum = graveyard.get(min);
			if (cur.getWorld().equalsIgnoreCase(loc.getWorld().getName())) {
				Bukkit.getConsoleSender().sendMessage("" + Utils.distance((int)loc.getX(), (int)loc.getY(), cur.getX(), cur.getZ()) + " " + Utils.distance((int)loc.getX(), (int)loc.getZ(), minimum.getX(), minimum.getZ()));
				if (Utils.distance((int)loc.getX(), (int)loc.getZ(), cur.getX(), cur.getZ()) < Utils.distance((int)loc.getX(), (int)loc.getZ(), minimum.getX(), minimum.getZ())) {
					min = i;
				}
			}
		}
		return graveyard.get(min);
	}
	
	//Overload of Function to find closest to take in a Location Class Parameter
	public Graveyard findClosest(Location loc) {
		int min = findWorldName(loc.getWorld().getName());
		if (min == -1) {
			return null;
		}
		for (int i = 0; i < graveyard.size(); i++) {
			Graveyard cur = graveyard.get(i);
			Graveyard minimum = graveyard.get(min);
			if (cur.getWorld().equalsIgnoreCase(loc.getWorld().getName())) {
				Bukkit.getConsoleSender().sendMessage("" + Utils.distance((int)loc.getX(), (int)loc.getY(), cur.getX(), cur.getZ()) + " " + Utils.distance((int)loc.getX(), (int)loc.getZ(), minimum.getX(), minimum.getZ()));
				if (Utils.distance((int)loc.getX(), (int)loc.getZ(), cur.getX(), cur.getZ()) < Utils.distance((int)loc.getX(), (int)loc.getZ(), minimum.getX(), minimum.getZ())) {
					min = i;
				}
			}
		}
		return graveyard.get(min);
	}
	
	public FileConfiguration getGraves() {
		return graves;
	}
	
	public Configuration getBaseData() {
		return graveyards;
	}
	
}
