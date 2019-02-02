package com.hubymc.engradados.inventorys;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import com.google.common.collect.Maps;
import com.hubymc.engradados.HubyEngradados;
import com.hubymc.engradados.lib.ItemCreator;
import com.hubymc.engradados.objetos.Caixa;

public class Confirm {
	
	public static HashMap<String, Caixa> cache = Maps.newHashMap();
	
	public static void getNewInventory(Player player, Caixa caixa) {
		Inventory inventory = Bukkit.createInventory(player, 4 * 9, "§8Confirmação§e§g§r§a§d§a§d§o§s");
		
		ItemStack accept = ItemCreator.createNewItemStack(Material.WOOL, "§aAceitar (Leia abaixo)", 1, 5, "§7Tem certeza que deseja comprar este item?");
		ItemStack deny = ItemCreator.createNewItemStack(Material.WOOL, "§cNegar", 1, 14, "§7Cancelar esta operação.");
		
		inventory.setItem(13, caixa.getItemFisic());
		inventory.setItem(20, accept);
		inventory.setItem(24, deny);
		
		player.openInventory(inventory);
		cache.put(player.getName(), caixa);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (cache.containsKey(player.getName())) {
					cache.remove(player.getName());
				}
			}
		}.runTaskLaterAsynchronously(HubyEngradados.getInstance(), 20 * 15);
	}
}
