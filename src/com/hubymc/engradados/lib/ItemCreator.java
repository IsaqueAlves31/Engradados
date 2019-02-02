package com.hubymc.engradados.lib;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCreator {
	
	public static ItemStack createNewItemStack(Material material, String displayname, int amount, int data) {
		ItemStack is = new ItemStack(material, amount, (short) data);
		ItemMeta ismeta = is.getItemMeta();
		ismeta.setDisplayName(displayname);
		is.setItemMeta(ismeta);
		return is;
	}

	public static ItemStack createNewItemStack(Material material, String displayname, int amount, int data, Enchantment enchantment, int level, String... lore) {
		ItemStack is = new ItemStack(material, amount, (short) data);
		is.addUnsafeEnchantment(enchantment, level);
		
		ItemMeta ismeta = is.getItemMeta();
		if (displayname != null) {
			ismeta.setDisplayName(displayname);
		}
		if (lore != null) {
			ismeta.setLore(Arrays.asList(lore));
		}
		is.setItemMeta(ismeta);
		return is;
	}
	
	public static ItemStack createNewItemStack(Material material, String displayname, int amount, int data, String... lore) {
		ItemStack is = new ItemStack(material, amount, (short) data);
		ItemMeta ismeta = is.getItemMeta();
		if (displayname != null) {
			ismeta.setDisplayName(displayname);
		}
		if (lore != null) {
			ismeta.setLore(Arrays.asList(lore));
		}
		ismeta.addItemFlags(ItemFlag.values());
		is.setItemMeta(ismeta);
		return is;
	}

	public static ItemStack createNewItemStack(Material material, String displayname, int amount, int data,
			List<String> lore) {
		ItemStack is = new ItemStack(material, amount, (short) data);
		ItemMeta ismeta = is.getItemMeta();
		if (displayname != null) {
			ismeta.setDisplayName(displayname);
		}
		if (lore != null) {
			ismeta.setLore(lore);
		}
		ismeta.addItemFlags(ItemFlag.values());
		is.setItemMeta(ismeta);
		return is;
	}

	public static ItemStack createNewFireworkStar(int amount, Color color, String displayname, String... lore) {
		ItemStack fisic = new ItemStack(Material.FIREWORK_CHARGE, amount);
		ItemMeta fisicmeta = fisic.getItemMeta();
		FireworkEffectMeta fireworkmeta = (FireworkEffectMeta) fisicmeta;
		FireworkEffect firework = FireworkEffect.builder().withColor(color).build();
		fireworkmeta.setEffect(firework);
		fireworkmeta.setDisplayName(displayname);
		fireworkmeta.setLore(Arrays.asList(lore));
		fireworkmeta.addItemFlags(ItemFlag.values());
		fisic.setItemMeta(fireworkmeta);
		return fisic;
	}
}