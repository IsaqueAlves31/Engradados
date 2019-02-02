package com.hubymc.engradados.objetos.item;

import org.bukkit.inventory.ItemStack;
import com.hubymc.engradados.objetos.enums.Rarity;

public class Item {

	String name;
	ItemStack fisic;
	Rarity rarity;
	
	public Item(String name) {
		this.name = name;
	}
	
	public Item(String name, ItemStack fisic, Rarity rarity) {
		this.name = name;
		this.fisic = fisic;
		this.rarity = rarity;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemStack getFisic() {
		return fisic;
	}

	public void setFisic(ItemStack fisic) {
		this.fisic = fisic;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}
}
