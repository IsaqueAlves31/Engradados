package com.hubymc.engradados.managers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hubymc.engradados.lib.InventarioAPI;
import com.hubymc.engradados.objetos.Caixa;
import com.hubymc.engradados.objetos.item.Item;
import com.hubymc.engradados.objetos.user.User;
 
public class UserManager {
	
	public List<User> users = Lists.newArrayList();
	public HashMap<String, Integer> scroller = Maps.newHashMap();
	
	public void getNewInventory(Player player, int page) {
		List<ItemStack> list = Lists.newArrayList();
		User user = getUser(player.getName());
		
		for (Caixa caixa : user.getCaixas()) {
			ItemStack fisic = caixa.getItemFisic();
			list.add(fisic);
		}
		
		getNewInventory(player, 7 * 2, page, 10, list);
		scroller.put(player.getName(), page);
	}
	
	public void getNewInventory(Player player, int division, int page, int index, List<ItemStack> list) {
		Inventory inventory = Bukkit.createInventory(player, 5 * 9, "§8Caixa Misteriosa");
		
		User user = getUser(player.getName());
        int starting = (page - 1) * division;
        int current = 0;
        
		if (starting > list.size()) return;

		List<ItemStack> sublist = list.subList(starting, list.size());

		for (ItemStack item : sublist) {
			while (InventarioAPI.isColumn(index, 1) | InventarioAPI.isColumn(index, 9)) {
				index++;
			}
			inventory.setItem(index, item);
			current++;
			index++;
			if (current == division) break;
		}
		
		if (page > 1) {
			ItemStack arrow = new ItemStack(Material.ARROW);
			ItemMeta arrowmeta = arrow.getItemMeta();
			arrowmeta.setDisplayName("§c« Página anterior");
			arrow.setItemMeta(arrowmeta);
			inventory.setItem(36, arrow);
		}
		
		if (sublist.size() > division) {
			ItemStack arrow = new ItemStack(Material.ARROW);
			ItemMeta arrowMeta = arrow.getItemMeta();
			arrowMeta.setDisplayName("§aPróxima página »");
			arrow.setItemMeta(arrowMeta);
			inventory.setItem(44, arrow);
		}
		
		ItemStack emerald = new ItemStack(Material.EMERALD);
		ItemMeta emeraldMeta = emerald.getItemMeta();
		emeraldMeta.setDisplayName("§aComprar Caixas Misteriosas");
		emeraldMeta.setLore(Arrays.asList("§7Clique para abrir o menu de compra de", "§bCaixas Misteriosas§7 utilizando Cash."));
		emerald.setItemMeta(emeraldMeta);
		
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
		anvilMeta.setLore(Arrays.asList("§7Ao completar missões, você", "§7recebe §bFragmentos Misteriosos§7 que podem ser", "§7usados para fabricar caixas ainda mais valiosas.", "", "§fFragmentos Misteriosos: §7" + user.getFragments(), ""));
		anvil.setItemMeta(anvilMeta);
		
		inventory.setItem(39, emerald);
		inventory.setItem(40, book);
		inventory.setItem(41, anvil);
		
		player.openInventory(inventory);
	}
	
	public boolean existsUser(String name) {
		return getUser(name) != null;
	}
	
	public User getUser(String name) {
		for (User user : users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> getUsers() {
		return users;
	}
}
