package com.hubymc.engradados.lib;

import java.util.Map.Entry;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventarioAPI {
	
	public static boolean contains(Inventory inventory, ItemStack item, int amount) {
		return getTotalAmount(inventory, item) >= amount;
	}
	
	public static boolean containsDisplayName(Inventory inventory, ItemStack item, int amount) {
		return getTotalAmountDisplayName(inventory, item) >= amount;
	}
	
	public static boolean isColumn(int index, int colunm) {
		return getColumn(index) == colunm;
	}
	
	public static int getColumn(int index) {
		if (index < 9) {
			return index + 1;
		}
		return (index % 9) + 1;
	}
	
	public static int getTotalAmount(Inventory inventory, ItemStack item) {
		int amount = 0;
		for (ItemStack id : inventory.all(item.getType()).values()) {
			if (id.isSimilar(item)) {
				amount += id.getAmount();
			}
		}
		return amount;
	}
	
	public static int getTotalAmountDisplayName(Inventory inventory, ItemStack item) {
		int amount = 0;
		
		for (ItemStack id : inventory.all(item.getType()).values()) {
			if (id.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
				amount += id.getAmount();
			}
		}
		return amount;
	}
	
	public static boolean isFull(Inventory inventory) {
		return inventory.firstEmpty() == -1;
	}
	
	public static void removeItem(Inventory inventory, ItemStack item, int amount) {
		for (Entry<Integer, ? extends ItemStack> map : inventory.all(item.getType()).entrySet()) {
			if (map.getValue().isSimilar(item)) {
				ItemStack currentItem = map.getValue();
				if (currentItem.getAmount() <= amount) {
					amount -= currentItem.getAmount();
					inventory.clear(map.getKey());
				} else {
					currentItem.setAmount(currentItem.getAmount() - amount);
					amount = 0;
				}

			}
			if (amount == 0)
				break;
		}
	}
	
	public static void removeItemDisplayName(Inventory inventory, ItemStack item, int amount) {
		for (Entry<Integer, ? extends ItemStack> map : inventory.all(item.getType()).entrySet()) {
			if (map.getValue().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
				ItemStack currentItem = map.getValue();
				if (currentItem.getAmount() <= amount) {
					amount -= currentItem.getAmount();
					inventory.clear(map.getKey());
				} else {
					currentItem.setAmount(currentItem.getAmount() - amount);
					amount = 0;
				}

			}
			if (amount == 0)
				break;
		}
	}
	
	public static boolean isEmpty(Inventory inventory) {

		for (ItemStack item : inventory.getContents()) {
			if (item != null) {
				return false;
			}

		}
		return true;
	}
}
