package me.NorthAlaska.Graveyards.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.NorthAlaska.Graveyards.Graveyard;
import me.NorthAlaska.Graveyards.Main;
import me.NorthAlaska.Graveyards.utils.Utils;

public class GVCommand implements CommandExecutor{
	
	private Main plugin;
	
	public GVCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("gv").setExecutor(this);
	}
	
	/* graveyard.admin - Gives access to all commands
	 * graveyard.create - Access to create command 
	 * graveyard.tp - teleport to given grave yards
	 * graveyard.set - Access to the set command which updates a value in a Grave-yard 
	 * graveyard.list - Access to the /gv list command for grave-yards
	 * graveyard.reload - reloads the config file for the plugin
	 */
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player)sender;
		if (sender instanceof Player == false) {
			Bukkit.getConsoleSender().sendMessage(Utils.chat("&cOnly Players can Execute this!"));
			return false;
		}
		if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
			p.sendMessage(Utils.chat(
			"---GraveYardz Help---" +
			"\n/gv create (name) (id) - creates a new grave-yard" +
			"\n/gv tp - Teleports you to the nearest graveyard" +
			"\n/gv set (name) - updates location information for a specific Graveyard" +
			"\n/gv del (name) - Deletes the name of the graveyard thats inputed" + 
			"\n/gv list - lists all the grave yards" +
			"\n/gv reload - reloads the config file for the plugin"));
			return true;
		}
		if (args[0].equalsIgnoreCase("info")) {
			p.sendMessage(Utils.chat("&7GraveYardz Plugin v1.0 - Players who don't have exemption spawn at nearest GraveYard when they die"));
			return true;
		}
		if (p.hasPermission("graveyard.admin")) {
			if (args[0].equalsIgnoreCase("create")) {
				createGraveYard(p, args);
				return true;
			}else if(args[0].equalsIgnoreCase("del")) {
				delGrave(p, args);
				return true;
			}else if(args[0].equalsIgnoreCase("set")) {
				setGrave(p, args);
				return true;
			}else if(args[0].equalsIgnoreCase("tp")) {
				tp(p);
				return true;
			}else if(args[0].equalsIgnoreCase("list")) {
				listGraves(p, args);
				return true;
			}else if(args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
				plugin.saveDefaultConfig();
			}
		}else if(p.hasPermission("graveyard.create") && args[0].equalsIgnoreCase("create")) {
			createGraveYard(p, args);
			return true;
		}else if(p.hasPermission("graveyard.set") && args[0].equalsIgnoreCase("set")) {
			setGrave(p, args);
			return true;
		}else if(p.hasPermission("graveyard.tp") && args[0].equalsIgnoreCase("tp")) {
			tp(p);
			return true;
		}else if(p.hasPermission("graveyard.del") && args[0].equalsIgnoreCase("del")) {
			delGrave(p, args);
			return true;
		}else if(p.hasPermission("graveyard.reload") && args[0].equalsIgnoreCase("reload")) {
			plugin.reloadConfig();
			plugin.saveDefaultConfig();
		}else {
			p.sendMessage(Utils.chat("&cInsufficient Permissions"));
		}
		
		return false;
	}
	
	public void tp(Player p) {
		Graveyard closest = plugin.findClosest(p);
		if (closest == null) {
			p.sendMessage(Utils.chat("&cThere are no grave yards in this world!"));
			return;
		}
		Location loc = new Location(plugin.getServer().getWorld(closest.getWorld()), (double)closest.getX(), (double)closest.getY(), (double)closest.getZ());
		p.teleport(loc);
		String message = plugin.getConfig().getString("teleportMessage").replaceAll("<name>", closest.getName());
		p.sendMessage(Utils.chat(message));
	}
	
	public void createGraveYard(Player p, String[] args) {
		Location loc = p.getLocation();
		String name;
		int id = -1;
		if (args.length >= 3) {
			name = args[1];
			id = Integer.parseInt(args[2]);
			if (plugin.containsName(name)) {
				p.sendMessage(Utils.chat("&cThat Graveyard already exists!"));
				return;
			}
			Graveyard temp = new Graveyard(name, loc.getWorld().getName(), id, (int)loc.getX(), (int)loc.getY(), (int)loc.getZ());
			
			//Submitting Data to grave-yards file in order to save it 
			plugin.getGraves().createSection(name);
			plugin.getGraves().createPath(plugin.getGraves().getConfigurationSection(name), "name");
			plugin.getGraves().createPath(plugin.getGraves().getConfigurationSection(name), "world");
			plugin.getGraves().createPath(plugin.getGraves().getConfigurationSection(name), "ID");
			plugin.getGraves().createPath(plugin.getGraves().getConfigurationSection(name), "x");
			plugin.getGraves().createPath(plugin.getGraves().getConfigurationSection(name), "y");
			plugin.getGraves().createPath(plugin.getGraves().getConfigurationSection(name), "z");
			plugin.getGraves().getConfigurationSection(name).set("name", temp.getName());
			plugin.getGraves().getConfigurationSection(name).set("world", temp.getWorld());
			plugin.getGraves().getConfigurationSection(name).set("ID", temp.getID());;
			plugin.getGraves().getConfigurationSection(name).set("x", temp.getX());
			plugin.getGraves().getConfigurationSection(name).set("y", temp.getY());
			plugin.getGraves().getConfigurationSection(name).set("z", temp.getZ());
			plugin.getBaseData().save();
			
			plugin.getGraveyards().add(temp);
			p.sendMessage(Utils.chat("&aSuccessfully Created GraveYard!"));
		}else {
			p.sendMessage(Utils.chat("&cUsage: /gv create (name) (id)"));
		}
	}
	
	public void setGrave(Player p, String[] args) {
		Location loc = p.getLocation();
		if (args.length >= 2) {
			String name = args[1];
			int index = plugin.findName(name);
			if (index != -1) {
				plugin.getGraves().getConfigurationSection(plugin.getGraveyards().get(index).getName()).set("world", loc.getWorld().getName());
				plugin.getGraves().getConfigurationSection(plugin.getGraveyards().get(index).getName()).set("x", (int)loc.getX());
				plugin.getGraves().getConfigurationSection(plugin.getGraveyards().get(index).getName()).set("y", (int)loc.getY());
				plugin.getGraves().getConfigurationSection(plugin.getGraveyards().get(index).getName()).set("z", (int)loc.getZ());
				plugin.getGraveyards().get(index).setLocation((int)loc.getX(), (int)loc.getY(), (int)loc.getZ());
				plugin.getBaseData().save();
				p.sendMessage(Utils.chat("&aSuccessfully Updated Grave Yard Location!"));
			}else {
				p.sendMessage(Utils.chat("&cInvalid Grave yard."));
			}
		}else {
			p.sendMessage(Utils.chat("&cUsage: /gv set (name)"));
		}
	}
	
	public void listGraves(Player p, String[] args) {
		ArrayList<Graveyard> graveyards = plugin.getGraveyards();
		if (graveyards.size() == 0) {
			p.sendMessage(Utils.chat("&cThere are no GraveYards"));
		}
		for (int i = 0; i < graveyards.size(); i++) {
			p.sendMessage(Utils.chat(graveyards.get(i).toString()));
		}
	}
	
	public void delGrave(Player p, String[] args) {
		if (args.length >= 2) {
			String name = args[1];
			if (plugin.containsName(name)) {
				int index = plugin.findName(name);
				plugin.getGraves().set(plugin.getGraveyards().get(index).getName(), null);
				plugin.getGraveyards().remove(index);
				p.sendMessage(Utils.chat("&aSuccessfully Removed Graveyard!"));
				plugin.getBaseData().save();
			}else {
				p.sendMessage(Utils.chat("&cGrave yard does not exist."));
			}
		}else {
			p.sendMessage(Utils.chat("&cUsage: /gv del (name)"));
		}
	}
	
}
