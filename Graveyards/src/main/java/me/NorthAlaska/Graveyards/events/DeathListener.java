package me.NorthAlaska.Graveyards.events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.NorthAlaska.Graveyards.Graveyard;
import me.NorthAlaska.Graveyards.Main;
import me.NorthAlaska.Graveyards.utils.Utils;

public class DeathListener implements Listener{
	
	private Main plugin;
	
	private static HashMap<String, Location> locations;
	
	public DeathListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Bukkit.getConsoleSender().sendMessage("Player respawned");
		if(!p.hasPermission("graveyard.exempt")) {
			Graveyard closest = plugin.findClosest(locations.get(p.getName()));
			if (closest == null) {
				Bukkit.getConsoleSender().sendMessage("Closest is null");
				return;
			}
			e.setRespawnLocation(closest.getLocation());
			String message = plugin.getConfig().getString("respawnMessage").replaceAll("<name>", closest.getName());
			p.sendMessage(Utils.chat(message));
		}else {
			Bukkit.getConsoleSender().sendMessage(Utils.chat("&cExempt from Graveyards!"));
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (locations == null)
			locations = new HashMap<String, Location>();
		locations.put(p.getName(), p.getLocation());
	}
}
