package me.jayden.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	private boolean chatDisabled = false;
	public ArrayList<String> worlds = new ArrayList<String>();
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		World w = p.getWorld();
		String world = w.toString();
		
		if (((chatDisabled) || (worlds.contains(world))) && (!p.hasPermission("chat.toegang"))) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Sorry, chat staat uit A.T.M");
			return;
		}
		
		
	}
	
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		String noPerms = ChatColor.RED + "" + ChatColor.BOLD + "Sorry je hebt geen permissie! ;)";
		
		if (cmd.getName().equalsIgnoreCase("chatuit")) {
			if (s.hasPermission("chat.uit")) {
				chatDisabled = true;
				
				s.sendMessage(ChatColor.BLUE + "De chat staat met succes uit!");
				Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Chat staat uit door een Staff Lid!");
				return true;
			} else {
				s.sendMessage(ChatColor.RED + noPerms);
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("chataan")) {
			if (s.hasPermission("chat.aan")) {
			worlds.removeAll(worlds);
			chatDisabled = false;
			s.sendMessage(ChatColor.BLUE + "De chat staat met Succes aan!");
			Bukkit.getServer().broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "De chat staat weer aan door een Staff Lid!");
			return true;
			}else {
				s.sendMessage(ChatColor.RED + noPerms);
				return true;
		}
		}
		if (cmd.getName().equalsIgnoreCase("chatclear")) {
			if (s.hasPermission("chat.clear")) {
				for (int i = 0; i<54; i++) {
					Bukkit.broadcastMessage(" ");
					
				}
			} else {
				s.sendMessage(ChatColor.RED + noPerms);
				return true;
			}
		}
		return false;
	}

}
