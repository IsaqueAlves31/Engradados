package com.hubymc.engradados.inventorys;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.google.common.collect.Lists;
import com.hubymc.engradados.HubyEngradados;
import com.hubymc.engradados.objetos.Caixa;
import com.hubymc.engradados.objetos.user.User;

public class Fragments {
	
	public static void getNewInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 4 * 9, "§8Fabricar Caixas Misteriosas");
		User user = HubyEngradados.getUserManager().getUser(player.getName());
		
		int[] cost = new int[] { 150, 220, 310 };
		
		int current = 10, currentCost = 0;
		
		for (Caixa caixa : HubyEngradados.getCaixaManager().getCaixas()) {
			ItemStack fisic = caixa.getItemFisic(player.getName(), cost[currentCost], user);
			inventory.setItem(current, fisic);
			current++; currentCost++;
		}
		
		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemMeta arrowMeta = arrow.getItemMeta();
		arrowMeta.setDisplayName("§aVoltar");
		arrow.setItemMeta(arrowMeta);
		
		ItemStack book = new ItemStack(Material.BOOK);
		ItemMeta bookMeta = book.getItemMeta();
		bookMeta.setDisplayName("§aInformações");
		List<String> lore = Lists.newArrayList();
		lore.add("§fCaixas Misteriosas: §7" + user.getCaixas().size());
		lore.add("§fFragmentos Misteriosos: §7" + user.getFragments());
		lore.add("");
		lore.add("§fAdquira Cash acessando:");
		lore.add("§eloja.redehuby.com");
		lore.add("");
		bookMeta.setLore(lore);
		book.setItemMeta(bookMeta);
		
		ItemStack emerald = new ItemStack(Material.EMERALD);
		ItemMeta emeraldMeta = emerald.getItemMeta();
		emeraldMeta.setDisplayName("§aComprar Caixas Misteriosas");
		emeraldMeta.setLore(Arrays.asList("§7Clique para abrir o menu de compra de", "§bCaixas Misteriosas§7 utilizando Cash."));
		emerald.setItemMeta(emeraldMeta);
		
		inventory.setItem(32, arrow);
		inventory.setItem(31, book);
		inventory.setItem(30, emerald);
		
		player.openInventory(inventory);
	}
}
