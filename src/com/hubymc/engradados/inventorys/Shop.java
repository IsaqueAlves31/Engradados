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
import com.hubymc.engradados.objetos.item.Item;
import com.hubymc.engradados.objetos.user.User;

public class Shop {
	
	public static void getNewInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 4 * 9, "§8Comprar Caixas Misteriosas");
		User user = HubyEngradados.getUserManager().getUser(player.getName());
		
		int current = 10;
		
		for (Caixa caixa : HubyEngradados.getCaixaManager().getCaixas()) {
			ItemStack fisic = caixa.getItemFisic(player.getName());
			inventory.setItem(current, fisic);
			current++;
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
		if (!user.getLastItensMap().isEmpty()) {
			lore.add("§aSeus últimos 5 itens encontrados:");
			for (int i = 0; i <= 4; i++) {
				Item item = user.getLastItens().get(i).getKey();
				lore.add(" §7" + (i + 1) + ". " + item.getRarity().getDisplayName() + "§f " + item.getName());
			}
		} else {
			lore.add("§cNão há itens anteriores.");
		}
		lore.add("");
		bookMeta.setLore(lore);
		book.setItemMeta(bookMeta);
		
		ItemStack anvil = new ItemStack(Material.ANVIL);
		ItemMeta anvilMeta = anvil.getItemMeta();
		anvilMeta.setDisplayName("§aFabricar Caixas Misteriosas");
		anvilMeta.setLore(Arrays.asList("§7Ao completar missões, você poderá", "§7receber §bFragmentos Misteriosos§7 que podem ser", "§7usados para fabricar caixas ainda mais valiosas.", "", "§fFragmentos Misteriosos: §7" + user.getFragments(), ""));
		anvil.setItemMeta(anvilMeta);
		
		inventory.setItem(30, arrow);
		inventory.setItem(31, book);
		inventory.setItem(32, anvil);
		
		player.openInventory(inventory);
	}
}
