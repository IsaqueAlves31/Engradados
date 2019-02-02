package com.hubymc.engradados.managers;

import java.util.Arrays;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.hubymc.engradados.lib.packets.LetterHead;
import com.hubymc.engradados.objetos.enums.Rarity;

public class RarityManager {
	 
	public ItemStack getHeadRarity(Rarity rarity) {
		if (rarity == Rarity.Comum) {
			ItemStack fisic = new LetterHead("§aItem", "490a78af-3143-43b4-888e-b9da0ab4c99b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTFhNWJkNmZjZDE3OTY4NTZiZDhhNGM2OTk4ODc5NGY4ZWM0NjY0ZTU1MDk4ZDhjZGU5YzdhNzg0MjNjNzFmIn19fQ==").getItemStack();
			ItemMeta fisicmeta = fisic.getItemMeta();
			fisicmeta.setLore(Arrays.asList("§7Sorteando..."));
			fisic.setItemMeta(fisicmeta);
			return fisic;
		} else if (rarity == Rarity.Raro) {
			ItemStack fisic = new LetterHead("§aItem", "d431f184-5344-42fd-b458-c5a2d1747a47", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFmZmFlZTcxMmM3ZDhkMTNjYjMxOWU3NTYxODk1YmJlNTU2YmM1MjE4ZDk1ZTM5MzQxODkzZmFkOWI0NGMxIn19fQ==").getItemStack();
			ItemMeta fisicmeta = fisic.getItemMeta();
			fisicmeta.setLore(Arrays.asList("§7Sorteando..."));
			fisic.setItemMeta(fisicmeta);
			return fisic;
		} else if (rarity == Rarity.Épico) {
			ItemStack fisic = new LetterHead("§aItem", "b7e2f209-03a5-4b06-be9e-1941917861de", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE5ZDI4YTg2MzJmYTRkODdjYTE5OWJiYzJlODhjZjM2OGRlZGQ1NTc0NzAxN2FlMzQ4NDM1NjlmN2E2MzRjNSJ9fX0=\\\"}]}}}").getItemStack();
			ItemMeta fisicmeta = fisic.getItemMeta();
			fisicmeta.setLore(Arrays.asList("§7Sorteando..."));
			fisic.setItemMeta(fisicmeta);
			return fisic;
		} else if (rarity == Rarity.Divino) {
			ItemStack fisic = new LetterHead("§aItem", "b5080391-089f-4714-b36b-42e9fad6c269", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWUyZTRjZGYyN2UzMmRjMThiNjA2NGNkZGNmZTFmZmIxZjM4ZDE0ODFiZDdjNDcyZWExMjMwNzZhMDc4NTAzZiJ9fX0=").getItemStack();
			ItemMeta fisicmeta = fisic.getItemMeta();
			fisicmeta.setLore(Arrays.asList("§7Sorteando..."));
			fisic.setItemMeta(fisicmeta);
			return fisic;
		}
		return null;
	}
	
	public ItemStack getHeadConclusion() {
		ItemStack fisic = new LetterHead("§aItem", "e4d4e44e-ab31-4d86-be60-5929a159daac", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRjNWQ3YTY3MzYxYmViYzQ5Zjg4YzcyNTUxM2MzNjM4YWQ1NTdhMTJhODY1ODU3YzI0MjI4NjMxMzI1NjUifX19").getItemStack();
		ItemMeta fisicmeta = fisic.getItemMeta();
		fisicmeta.setLore(Arrays.asList("§7Sorteando..."));
		fisic.setItemMeta(fisicmeta);
		return fisic;
	}
	
	public Rarity getRarity(String name) {
		for (Rarity rarity : Rarity.values()) {
			if (rarity.name().equalsIgnoreCase(name)) {
				return rarity;
			}
		}
		return null;
	}
}
