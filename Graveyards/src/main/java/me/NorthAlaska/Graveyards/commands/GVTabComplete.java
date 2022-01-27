package me.NorthAlaska.Graveyards.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class GVTabComplete implements TabCompleter {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("create");
		list.add("del");
		list.add("reload");
		list.add("tp");
		list.add("list");
		list.add("help");
		list.add("info");
		return list;
	}
	
}
