package com.hubymc.engradados.managers;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hubymc.bosses.objetos.BossType;
import com.hubymc.engradados.HubyEngradados;
import com.hubymc.engradados.lib.ItemCreator;
import com.hubymc.engradados.objetos.Caixa;
import com.hubymc.engradados.objetos.enums.Rarity;
import com.hubymc.engradados.objetos.item.Item;
import com.hubymc.engradados.objetos.user.User;
import net.md_5.bungee.api.ChatColor;

public class CaixaManager {
	
	public List<Caixa> caixas = Lists.newArrayList();
	public HashMap<String, Hologram> cache = Maps.newHashMap();
	
	public void setupCaixas() {
		Location location = new Location(Bukkit.getWorld(HubyEngradados.getInstance().getConfig().getString("totem.world")), HubyEngradados.getInstance().getConfig().getDouble("totem.x"), HubyEngradados.getInstance().getConfig().getDouble("totem.y"), HubyEngradados.getInstance().getConfig().getDouble("totem.z"));
		
		Caixa híbrida = new Caixa("Hibrida");
		híbrida.setLocation(location);
		híbrida.setDisplayName("Híbrida");
		híbrida.setCost(550); 
		híbrida.setFisic(new ItemStack(Material.ENDER_CHEST));
		híbrida.setDesc(Arrays.asList("§7Consiga diversos itens com está Caixa Misteriosa", "§7como Armaduras, Espadas e muito mais!"));
		
		Caixa espectral = new Caixa("Espectral");
		espectral.setLocation(location);
		espectral.setDisplayName("Espectral");
		espectral.setCost(725);
		espectral.setFisic(new ItemStack(Material.SKULL_ITEM, 1, (short) 1));
		espectral.setDesc(Arrays.asList("§7Consiga diversos itens com está Caixa Misteriosa", "§7como Poder Máximo, Geradores de Proteção e muito mais!"));
		
		Caixa boss = new Caixa("Boss");
		boss.setLocation(location);
		boss.setDisplayName("Boss");
		boss.setCost(1000);
		boss.setFisic(new ItemStack(Material.MONSTER_EGG, 1, (short) 50));
		boss.setDesc(Arrays.asList("§7Consiga seus Bosses com está Caixa Misteriosa", "§7os Bosses disponíveis são Diablo, Besta de Gévaudan e muito mais!"));
		
		ItemStack poderMáximo = ItemCreator.createNewItemStack(Material.NETHER_STAR, "§6+1 de Poder Máximo", 1, 0, "§fAtivando este item você aumenta", "§f1 ponto em seu poder máximo.", "", "§f* Limite de poder máximo: 20");
		ItemStack poderInstantâneo = ItemCreator.createNewItemStack(Material.PAPER, "§6Poder Instantâneo", 1, 0, "§fAtivando este item você aumenta", "§f1 ponto em seu poder.");
//		ItemStack mineradora = ItemCreator.createNewItemStack(Material.FURNACE, "§6Mineradora", 1, 0, "§7Colete minérios de uma forma", "§7mais ágio e menos trabalhosa.", "", "§8* Assim que a Mineradora for", "§8colocada no chão, ela não", "§8poderá ser removida!", "");
		ItemStack restringirEfeitos = ItemCreator.createNewFireworkStar(1, Color.PURPLE, "§eChave de Ativação", "§fTipo: §7Restringir Efeito", "", "§7Utilize este item para liberar essa funcionalidade", "§7do seu sinalizador. Para isso, segure shift e", "§7clique com botão direito em seu Sinalizador.");
		ItemStack silkTouch = ItemCreator.createNewItemStack(Material.DIAMOND_PICKAXE, null, 1, 0, "§7Toque Suave II", "", "§7* Esta picareta tem a capacidade", "§7* de quebrar bedrocks", "");
		ItemStack bedrock = ItemCreator.createNewItemStack(Material.BEDROCK, "§eGerador de Rocha Matriz", 1, 0, "§7Utilize este item para gerar bedrock da", "§7camada onde foi colocada, até o chão.");
		ItemStack obsidian = ItemCreator.createNewItemStack(Material.OBSIDIAN, "§eGerador de Obisidiana", 1, 0, "§7Utilize este item para gerar obsidian da", "§7camada onde foi colocada, até o chão.");
		ItemStack endstone = ItemCreator.createNewItemStack(Material.ENDER_STONE, "§eGerador de Pedra do Fim", 1, 0, "§7Utilize este item para gerar endstone da", "§7camada onde foi colocada, até o chão.");
		
		ItemStack slimeblock = ItemCreator.createNewItemStack(Material.SLIME_BLOCK, "§cSinto muito.", 16, 0, "§7Parece que você não deu sorte!");
		ItemStack emerald = ItemCreator.createNewItemStack(Material.EMERALD_BLOCK, null, 32, 0);
		ItemStack diamond = ItemCreator.createNewItemStack(Material.DIAMOND_BLOCK, null, 32, 0);
		ItemStack iron = ItemCreator.createNewItemStack(Material.IRON_BLOCK, null, 16, 0);
		ItemStack gold = ItemCreator.createNewItemStack(Material.GOLD_BLOCK, null, 16, 0);
		ItemStack coal = ItemCreator.createNewItemStack(Material.COAL_BLOCK, null, 16, 0);
		ItemStack lapis = ItemCreator.createNewItemStack(Material.LAPIS_BLOCK, null, 16, 0);
		ItemStack redstone = ItemCreator.createNewItemStack(Material.REDSTONE_BLOCK, null, 16, 0);
		
		ItemStack helmet_p3 = ItemCreator.createNewItemStack(Material.DIAMOND_HELMET, null, 1, 0, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ItemStack chestplate_p3 = ItemCreator.createNewItemStack(Material.DIAMOND_CHESTPLATE, null, 1, 0, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ItemStack leggings_p3 = ItemCreator.createNewItemStack(Material.DIAMOND_LEGGINGS, null, 1, 0, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ItemStack boots_p3 = ItemCreator.createNewItemStack(Material.DIAMOND_BOOTS, null, 1, 0, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ItemStack sword_s3 = ItemCreator.createNewItemStack(Material.DIAMOND_SWORD, null, 1, 0, Enchantment.DAMAGE_ALL, 3);
		ItemStack axe_s3 = ItemCreator.createNewItemStack(Material.DIAMOND_AXE, null, 1, 0, Enchantment.DAMAGE_ALL, 3);
		ItemStack cap_8 = ItemCreator.createNewItemStack(Material.GOLDEN_APPLE, null, 8, 1);
		ItemStack cap_16 = ItemCreator.createNewItemStack(Material.GOLDEN_APPLE, null, 16, 1);
		ItemStack cap_32 = ItemCreator.createNewItemStack(Material.GOLDEN_APPLE, null, 32, 1);
		
		ItemStack poeira = ItemCreator.createNewItemStack(Material.NETHER_STALK, "§6Poeira de Boss", 1, 0, "§7Junte 30 Poeiras de Boss e", "§7escolha um Boss para você batalhar!", "", "§e* Poeiras de Boss são conseguidas", "§eapenas na §bCaixa Misteriosa Boss§e.");
		
		for (BossType bossType : BossType.values()) {
			boss.getItens().add(new Item(ChatColor.stripColor(bossType.getCustomName()) + "§7 (x1)", bossType.getItemFisic(), Rarity.Divino));
			boss.getHighlights().add(new Item(ChatColor.stripColor(bossType.getCustomName()) + "§7 (x1)", bossType.getItemFisic(), Rarity.Divino));
		}
		
		boss.getItens().add(new Item("Poeira de Boss", poeira, Rarity.Épico));
		boss.getHighlights().add(new Item("Poeira de Boss", poeira, Rarity.Épico));
		 
		híbrida.getItens().add(new Item("Maça Dourada §7(x8)", cap_8, Rarity.Divino));
		híbrida.getItens().add(new Item("Maça Dourada §7(x16)", cap_16, Rarity.Divino));
		híbrida.getItens().add(new Item("Capacete de Diamante §7(Proteção III)", helmet_p3, Rarity.Épico));
		híbrida.getItens().add(new Item("Peitoral de Diamante §7(Proteção III)", chestplate_p3, Rarity.Épico));
		híbrida.getItens().add(new Item("Calça de Diamante §7(Proteção III)", leggings_p3, Rarity.Épico));
		híbrida.getItens().add(new Item("Botas de Diamante §7(Proteção III)", boots_p3, Rarity.Épico));
		híbrida.getItens().add(new Item("Espada de Diamante §7(Afiação III)", sword_s3, Rarity.Raro));
		híbrida.getItens().add(new Item("Machado de Diamante §7(Afiação III)", axe_s3, Rarity.Raro));
		híbrida.getItens().add(new Item("Blocos de Esmeralda §7(x16)", emerald, Rarity.Comum));
		híbrida.getItens().add(new Item("Blocos de Diamante §7(x16)", diamond, Rarity.Comum));
		híbrida.getItens().add(new Item("Blocos de Ferro §7(x16)", iron, Rarity.Comum));
		híbrida.getItens().add(new Item("Blocos de Ouro §7(x16)", gold, Rarity.Comum));
		híbrida.getItens().add(new Item("Blocos de Carvão §7(x16)", coal, Rarity.Comum));
		híbrida.getItens().add(new Item("Blocos de Lápis-Lazúle §7(x16)", lapis, Rarity.Comum));
		híbrida.getItens().add(new Item("Blocos de Redstone §7(x16)", redstone, Rarity.Comum));
		híbrida.getItens().add(new Item("Blocos de Slime §7(x16)", slimeblock, Rarity.Comum));
		
		híbrida.getHighlights().add(new Item("Maça Dourada §7(x8)", cap_8, Rarity.Divino));
		híbrida.getHighlights().add(new Item("Maça Dourada §7(x16)", cap_16, Rarity.Divino));
		híbrida.getHighlights().add(new Item("Capacete de Diamante §7(Proteção III)", helmet_p3, Rarity.Épico));
		híbrida.getHighlights().add(new Item("Peitoral de Diamante §7(Proteção III)", chestplate_p3, Rarity.Épico));
		híbrida.getHighlights().add(new Item("Calça de Diamante §7(Proteção III)", leggings_p3, Rarity.Épico));
		híbrida.getHighlights().add(new Item("Botas de Diamante §7(Proteção III)", boots_p3, Rarity.Épico));
		
		espectral.getItens().add(new Item("Poder Máximo §7(x1)", poderMáximo, Rarity.Divino));
		espectral.getItens().add(new Item("Poder Instantâneo §7(x1)", poderInstantâneo, Rarity.Divino));
		espectral.getItens().add(new Item("Restringir Efeitos §7(Sinalizador)", restringirEfeitos, Rarity.Divino));
		espectral.getItens().add(new Item("Maça Dourada §7(x32)", cap_32, Rarity.Divino));
		espectral.getItens().add(new Item("Maça Dourada §7(x16)", cap_16, Rarity.Épico));
		espectral.getItens().add(new Item("Picareta de Diamante §7(Toque Suave II)", silkTouch, Rarity.Épico));
		espectral.getItens().add(new Item("Gerador de Proteção §7(Rocha Matriz)", bedrock, Rarity.Raro));
		espectral.getItens().add(new Item("Gerador de Proteção §7(Obisidiana)", obsidian, Rarity.Raro));
		espectral.getItens().add(new Item("Gerador de Proteção §7(Pedra do Fim)", endstone, Rarity.Raro));
		espectral.getItens().add(new Item("Blocos de Esmeralda §7(x16)", emerald, Rarity.Comum));
		espectral.getItens().add(new Item("Blocos de Diamante §7(x16)", diamond, Rarity.Comum));
		espectral.getItens().add(new Item("Blocos de Ferro §7(x16)", iron, Rarity.Comum));
		espectral.getItens().add(new Item("Blocos de Ouro §7(x16)", gold, Rarity.Comum));
		espectral.getItens().add(new Item("Blocos de Carvão §7(x16)", coal, Rarity.Comum));
		espectral.getItens().add(new Item("Blocos de Lápis-Lazúle §7(x16)", lapis, Rarity.Comum));
		espectral.getItens().add(new Item("Blocos de Redstone §7(x16)", redstone, Rarity.Comum));
		espectral.getItens().add(new Item("Blocos de Slime §7(x16)", slimeblock, Rarity.Comum));
		
		espectral.getHighlights().add(new Item("Poder Máximo §7(x1)", poderMáximo, Rarity.Divino));
		espectral.getHighlights().add(new Item("Poder Instantâneo §7(x1)", poderInstantâneo, Rarity.Divino));
		espectral.getHighlights().add(new Item("Restringir Efeitos §7(Sinalizador)", restringirEfeitos, Rarity.Divino));
		espectral.getHighlights().add(new Item("Maça Dourada §7(x32)", cap_32, Rarity.Divino));
		espectral.getHighlights().add(new Item("Maça Dourada §7(x16)", cap_16, Rarity.Épico));
		espectral.getHighlights().add(new Item("Picareta de Diamante §7(Toque Suave II)", silkTouch, Rarity.Épico));
		espectral.getHighlights().add(new Item("Gerador de Proteção §7(Rocha Matriz)", bedrock, Rarity.Raro));
		espectral.getHighlights().add(new Item("Gerador de Proteção §7(Obisidiana)", obsidian, Rarity.Raro));
		espectral.getHighlights().add(new Item("Gerador de Proteção §7(Pedra do Fim)", endstone, Rarity.Raro));
		
		caixas.add(híbrida);
		caixas.add(espectral);
		caixas.add(boss);
	}
	 
	public boolean existsCaixa(String name) {
		return getCaixa(name) != null;
	}
	
	public Caixa getCaixa(String name) {
		for (Caixa caixa : caixas) {
			if (caixa.getName().equalsIgnoreCase(name)) {
				return caixa;
			}
		}
		return null;
	}
	
	public boolean existsItem(String name) {
		return getItem(name) != null;
	}
	
	public Item getItem(String name) {
		for (Caixa caixa : caixas) {
			for (Item item : caixa.getItens()) {
				if (item.getName().equalsIgnoreCase(name)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public boolean existsTotem(Location location) {
		for (Caixa caixa : caixas) {
			if (caixa.getLocation().getX() == location.getX()) {
				if (caixa.getLocation().getY() == location.getY()) {
					if (caixa.getLocation().getZ() == location.getZ()) {
						if (caixa.getLocation().getWorld() == location.getWorld()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public void createHologram() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = HubyEngradados.getUserManager().getUser(player.getName());
			if (HubyEngradados.getInstance().getConfig().contains("totem")) {
				double x = HubyEngradados.getInstance().getConfig().getDouble("totem.x");
				double y = HubyEngradados.getInstance().getConfig().getDouble("totem.y");
				double z = HubyEngradados.getInstance().getConfig().getDouble("totem.z");
				String world = HubyEngradados.getInstance().getConfig().getString("totem.world");
				
				if (Bukkit.getWorld(world) != null) {
					Location location = new Location(Bukkit.getWorld(world), x, y, z);
					
					Hologram hologram = HologramsAPI.createHologram(HubyEngradados.getInstance(), location.clone().add(0.5D, 1.8D, 0.5D));
					hologram.setAllowPlaceholders(true);
					hologram.getVisibilityManager().setVisibleByDefault(false);
					
					HologramsAPI.registerPlaceholder(HubyEngradados.getInstance(), "{caixas:" + player.getName() + "}", 0.1D, new PlaceholderReplacer() {
						public String update() {
							return "§e§l" + new DecimalFormat("#,###.#").format(user.getCaixas().size());
						}
					});
					
					hologram.appendTextLine("{caixas:" + player.getName() + "} CAIXAS");
					hologram.appendTextLine("§bCaixa Misteriosa");
					hologram.appendTextLine("§eClique para comprar");
					
					hologram.getVisibilityManager().showTo(player);
					
					cache.put(player.getName(), hologram);
				}
			}
		}
	}
	
	public void createHologram(Player player) {
		User user = HubyEngradados.getUserManager().getUser(player.getName());
		
		if (HubyEngradados.getInstance().getConfig().contains("totem")) {
			double x = HubyEngradados.getInstance().getConfig().getDouble("totem.x");
			double y = HubyEngradados.getInstance().getConfig().getDouble("totem.y");
			double z = HubyEngradados.getInstance().getConfig().getDouble("totem.z");
			String world = HubyEngradados.getInstance().getConfig().getString("totem.world");
				
			if (Bukkit.getWorld(world) != null) {
				Location location = new Location(Bukkit.getWorld(world), x, y, z);
					
				Hologram hologram = HologramsAPI.createHologram(HubyEngradados.getInstance(), location.clone().add(0.5D, 1.8D, 0.5D));
				hologram.setAllowPlaceholders(true);
				hologram.getVisibilityManager().setVisibleByDefault(false);
					
				HologramsAPI.registerPlaceholder(HubyEngradados.getInstance(), "{caixas:" + player.getName() + "}", 0.1D, new PlaceholderReplacer() {
					public String update() {
						return "§e§l" + new DecimalFormat("#,###.#").format(user.getCaixas().size());
					}
				});
					
				hologram.appendTextLine("{caixas:" + player.getName() + "} CAIXAS");
				hologram.appendTextLine("§bCaixa Misteriosa");
				hologram.appendTextLine("§eClique para comprar");
					
				hologram.getVisibilityManager().showTo(player);
					
				cache.put(player.getName(), hologram);
			}
		}
	}
	
	public List<Caixa> getCaixas() {
		return caixas;
	}
}
