package com.hubymc.engradados;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.hubymc.engradados.commands.EngradadosCommands;
import com.hubymc.engradados.database.SQLite;
import com.hubymc.engradados.lib.DatabaseFactory;
import com.hubymc.engradados.listeners.PlayerListeners;
import com.hubymc.engradados.managers.CaixaManager;
import com.hubymc.engradados.managers.RarityManager;
import com.hubymc.engradados.managers.UserManager;
import com.hubymc.engradados.objetos.user.User;

public class HubyEngradados extends JavaPlugin {
	
	public static HubyEngradados instance;
	public static RarityManager rarityManager;
	public static UserManager userManager;
	public static CaixaManager caixaManager;
	public static SQLite SQLite;
	
	public void onLoad() {
		File file = new File("plugins/HubyEngradados");
		if (!file.exists()) {
			file.mkdirs();
		}
		saveDefaultConfig();
	}
	 
	public void onEnable() {
		instance = this;
		SQLite = new SQLite();
		
		rarityManager = new RarityManager();
		userManager = new UserManager();
		
		caixaManager = new CaixaManager();
		caixaManager.setupCaixas();
		
		this.getCommand("engradados").setExecutor(new EngradadosCommands());
		this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
		
		DatabaseFactory.loadRegisters();
	}
	
	public void onDisable() {
		for (Hologram hologram : HologramsAPI.getHolograms(this)) {
			hologram.delete();
		}
		
		for (User user : userManager.getUsers()) {
			DatabaseFactory.register(user);
		}
	}
	
	public static SQLite getSQLite() {
		return SQLite;
	}
	
	public static CaixaManager getCaixaManager() {
		return caixaManager;
	}
	
	public static UserManager getUserManager() {
		return userManager;
	}
	
	public static RarityManager getRarityManager() {
		return rarityManager;
	}
	
	public static HubyEngradados getInstance() {
		return instance;
	}
}
