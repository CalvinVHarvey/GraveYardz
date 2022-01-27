package me.NorthAlaska.Graveyards.utils;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import me.NorthAlaska.Graveyards.Graveyard;

public class Utils 
{
	public Utils()
	{
		
	}
	public static String chat(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static int nextInt(int min, int max)
	{
		return (int)(Math.random() * ((max-min)+1)) + min;
	}
	
	public static void saveGraves(ArrayList<Graveyard> graves, FileConfiguration saveFile) {
		for (int i = 0; i < graves.size(); i++) {
			
		}
	}
	
	public static double distance(int x1, int y1, int x2, int y2) {
		double dist = Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2);
		return Math.sqrt(dist);
	}
}
