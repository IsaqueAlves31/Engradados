package com.hubymc.engradados.objetos;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.google.common.collect.Lists;
import com.hubymc.cash.dados.Dados;
import com.hubymc.engradados.objetos.item.Item;
import com.hubymc.engradados.objetos.user.User;

public class Caixa {

	List<Item> itens = Lists.newArrayList(), highlights = Lists.newArrayList();
	List<String> desc = Lists.newArrayList();
	String name, displayname;
	Location location;
	int cost = 0;
	ItemStack fisic; 
	
	public Caixa(String name) {
		this.name = name;
	}
	
	public String getStar(String name) {
		if (name.equalsIgnoreCase("Hibrida")) {
			return "§e**§7***";
		} else if (name.equalsIgnoreCase("Boss")) {
			return "§e*****";
		} else if (name.equalsIgnoreCase("Espectral")) {
			return "§e****§7*";
		}
		return "§7*****";
	}
	
	public ItemStack getItemFisic() {
		
		ItemMeta fisicMeta = fisic.getItemMeta();
		fisicMeta.setDisplayName("§bCaixa Misteriosa " + this.displayname);
		List<String> lore = Lists.newArrayList();
		lore.add(getStar(this.name));
		lore.add("");
		lore.add("§fAo abrir esta caixa, você tem a chance");
		lore.add("§fde ganhar um dos seguintes itens em destaque:");
		lore.add("");
		for (Item item : highlights) {
			lore.add(" §7• " + item.getRarity().getDisplayName() + "§f " + item.getName());
		}
		fisicMeta.setLore(lore);
		fisic.setItemMeta(fisicMeta);
		
		return fisic;
	}
	
	public ItemStack getItemFisic(String player) {
		
		ItemMeta fisicMeta = fisic.getItemMeta();
		fisicMeta.setDisplayName("§bCaixa Misteriosa " + this.displayname);
		List<String> lore = Lists.newArrayList();
		lore.addAll(getDesc());
		lore.add("");
		lore.add("§eItens em destaque:");
		for (Item item : highlights) {
			lore.add(" §f• " + item.getRarity().getDisplayName() + " §f" + item.getName());
		}
		lore.add("");
		lore.add("§7Custo: §6✪§f" + cost);
		lore.add("");
		if (Dados.getCash(player) >= cost) {
			lore.add("§7Clique para comprar este item.");
		} else {
			lore.add("§cVocê não tem saldo suficiente.");
		}
		fisicMeta.setLore(lore);
		fisic.setItemMeta(fisicMeta);
		
		return fisic;
	}
	
	public ItemStack getItemFisic(String player, int cost, User user) {
		
		ItemMeta fisicMeta = fisic.getItemMeta();
		fisicMeta.setDisplayName("§bCaixa Misteriosa " + this.displayname);
		List<String> lore = Lists.newArrayList();
		lore.addAll(getDesc());
		lore.add("");
		lore.add("§eItens em destaque:");
		for (Item item : highlights) {
			lore.add(" §f• " + item.getRarity().getDisplayName() + " §f" + item.getName());
		}
		lore.add("");
		lore.add("§7Custo: §b" + cost + " fragmentos");
		lore.add("");
		if (user.hasFragments(cost)) {
			lore.add("§7Clique para comprar este item.");
		} else {
			lore.add("§cVocê não tem saldo suficiente.");
		}
		fisicMeta.setLore(lore);
		fisic.setItemMeta(fisicMeta);
		
		return fisic;
	}
	
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<Item> getHighlights() {
		return highlights;
	} 

	public void setHighlights(List<Item> highlights) {
		this.highlights = highlights;
	}

	public List<String> getDesc() {
		return desc;
	}

	public void setDesc(List<String> desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayname;
	}

	public void setDisplayName(String displayname) {
		this.displayname = displayname;
	}

	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public ItemStack getFisic() {
		return fisic;
	}

	public void setFisic(ItemStack fisic) {
		this.fisic = fisic;
	}
}
