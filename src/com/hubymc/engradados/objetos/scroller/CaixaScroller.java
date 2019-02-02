package com.hubymc.engradados.objetos.scroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hubymc.engradados.HubyEngradados;
import com.hubymc.engradados.lib.GroupController;
import com.hubymc.engradados.lib.packets.ParticleEffect;
import com.hubymc.engradados.lib.support.FancyMessage;
import com.hubymc.engradados.objetos.Caixa;
import com.hubymc.engradados.objetos.enums.Rarity;
import com.hubymc.engradados.objetos.item.Item;
import com.hubymc.engradados.objetos.user.User;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockAction;

public class CaixaScroller {
	
	public static boolean totem = true;
	public HashMap<Integer, ArmorStand> current = new HashMap<>();
	public List<Location> locations = new ArrayList<>();
	public List<Item> rewards = Lists.newArrayList();
	public int score = 15;
	
	public CaixaScroller() {
		
	}
	
	public void runnable(Player player, Caixa caixa) {
		if (totem) {
			totem = false;
			
			User user = HubyEngradados.getUserManager().getUser(player.getName());
			user.getCaixas().remove(caixa);
			
			changeEnderChestState(caixa.getLocation(), true);
			HashMap<String, Item> reward = Maps.newHashMap();
			
			for (Hologram hologram : HologramsAPI.getHolograms(HubyEngradados.getInstance())) {
				hologram.delete();
			}
			
			spawn(caixa.getLocation().clone().add(0.5D, 0.0D, 0.5D).subtract(0.0D, 0.5D, 0.0D), 1.3D, 7, caixa);
			
			new BukkitRunnable() {
				public void run() {
					HashMap<Integer, ArmorStand> clone = new HashMap<>();
					for (Entry<Integer, ArmorStand> entry : current.entrySet()) {
						ArmorStand stand = entry.getValue();
						int value = (entry.getKey() + 1);
						if (value == locations.size()) {
							value = 0;
						} 
						clone.put(value, stand);
						 
						stand.getWorld().playSound(stand.getLocation(), Sound.ORB_PICKUP, 0.5F, 1.0F);
						stand.teleport(locations.get(value));
					}
					  
					current = clone;
					score--;
					
					if (score <= 0) {
						cancel();
						
						reward.put(player.getName(), rewards.get(0));
						
						new BukkitRunnable() {
							int scoreCurrent = 1;
							public void run() {
								if (scoreCurrent == 1) {
									for (Entry<Integer, ArmorStand> entry : current.entrySet()) {
										ArmorStand stand = entry.getValue();
										int value = entry.getKey();
										 
										if (value == 4) {
											ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.0F, 15, stand.getLocation(), player);
											
											stand.getWorld().playSound(stand.getLocation(), Sound.FIZZ, 0.5F, 1.0F);
											stand.remove();
										}
									} 
								} else if (scoreCurrent == 2) {
									for (Entry<Integer, ArmorStand> entry : current.entrySet()) {
										ArmorStand stand = entry.getValue();
										int value = entry.getKey();
										
										if (value == 6) {
											ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.0F, 15, stand.getLocation(), player);
											
											stand.getWorld().playSound(stand.getLocation(), Sound.FIZZ, 0.5F, 1.0F);
											stand.remove();
										}
									}
								} else if (scoreCurrent == 3) {
									for (Entry<Integer, ArmorStand> entry : current.entrySet()) {
										ArmorStand stand = entry.getValue();
										int value = entry.getKey();
										
										if (value == 3) {
											ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.0F, 15, stand.getLocation(), player);
											 
											stand.getWorld().playSound(stand.getLocation(), Sound.FIZZ, 0.5F, 1.0F);
											stand.remove();
										}
									}
								} else if (scoreCurrent == 4) {
									for (Entry<Integer, ArmorStand> entry : current.entrySet()) {
										ArmorStand stand = entry.getValue();
										int value = entry.getKey();
										 
										if (value == 5) {
											ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.0F, 15, stand.getLocation(), player);
											
											stand.getWorld().playSound(stand.getLocation(), Sound.FIZZ, 0.5F, 1.0F);
											stand.remove();
										}
									}
								} else if (scoreCurrent == 5) {
									for (Entry<Integer, ArmorStand> entry : current.entrySet()) {
										ArmorStand stand = entry.getValue();
										int value = entry.getKey();
										 
										if (value == 2) {
											ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.0F, 15, stand.getLocation(), player);
											
											stand.getWorld().playSound(stand.getLocation(), Sound.FIZZ, 0.5F, 1.0F);
											stand.remove();
										}
									}
								} else if (scoreCurrent == 6) {
									for (Entry<Integer, ArmorStand> entry : current.entrySet()) {
										ArmorStand stand = entry.getValue();
										int value = entry.getKey();
										 
										if (value == 1) {
											ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.0F, 15, stand.getLocation(), player);
											
											stand.getWorld().playSound(stand.getLocation(), Sound.FIZZ, 0.5F, 1.0F);
											stand.remove();
										}
									}
								} else if (scoreCurrent == 7) {
									if (current.containsKey(0)) {
										ArmorStand armorStand = current.get(0);
										
										armorStand.setCustomName("§7§k                       ");
										armorStand.setCustomNameVisible(true);
										armorStand.teleport(new Location(armorStand.getWorld(), armorStand.getLocation().getX(), armorStand.getLocation().getY() - 0.6D, armorStand.getLocation().getZ()));
										
										reward.put(player.getName(), rewards.get(6));
									}
								} 
								
								if (scoreCurrent >= 10) {
									if (current.containsKey(0)) {
										ArmorStand stand = current.get(0);
										stand.setCustomName(reward.get(player.getName()).getRarity().getDisplayName() + " §f" + reward.get(player.getName()).getName());
										stand.setCustomNameVisible(true);
										 
										stand.setHelmet(HubyEngradados.getRarityManager().getHeadConclusion());
										
										for (int i = 0; i <= 5; i++) {
											ParticleEffect.LAVA.display(i, i, i, 0.0F, 15, stand.getLocation(), player);
										}
									}
									
									caixa.getLocation().getWorld().playSound(caixa.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
									
									if (reward.get(player.getName()).getRarity() == Rarity.Comum) {
										FancyMessage broadcast = new FancyMessage("§b[Caixas Misteriosas] §fO jogador " + GroupController.getGroupColor(player.getName()) + player.getName() + "§f ganhou §fum §aItem §aComum§f.").tooltip("§eItens adquiridos:", "", " §f" + reward.get(player.getName()).getName(), "", "§7Data do ocorrido: §f" + new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()) + "§7 às §f" + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
										broadcast.send(Bukkit.getOnlinePlayers());
									}
									
									if (reward.get(player.getName()).getRarity() == Rarity.Raro) {
										FancyMessage broadcast = new FancyMessage("§b[Caixas Misteriosas] §fO jogador " + GroupController.getGroupColor(player.getName()) + player.getName() + "§f ganhou §fum §dItem §dRaro§f.").tooltip("§eItens adquiridos:", "", " §f" + reward.get(player.getName()).getName(), "", "§7Data do ocorrido: §f" + new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()) + "§7 às §f" + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
										broadcast.send(Bukkit.getOnlinePlayers());
									}
									if (reward.get(player.getName()).getRarity() == Rarity.Épico) {
										FancyMessage broadcast = new FancyMessage("§b[Caixas Misteriosas] §fO jogador " + GroupController.getGroupColor(player.getName()) + player.getName() + "§f ganhou §fum §6Item §6Épico§f.").tooltip("§eItens adquiridos:", "", " §f" + reward.get(player.getName()).getName(), "", "§7Data do ocorrido: §f" + new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()) + "§7 às §f" + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
										broadcast.send(Bukkit.getOnlinePlayers());
									}
									if (reward.get(player.getName()).getRarity() == Rarity.Divino) {
										FancyMessage broadcast = new FancyMessage("§b[Caixas Misteriosas] §fO jogador " + GroupController.getGroupColor(player.getName()) + player.getName() + "§f ganhou §fum §bItem §bDivino§f.").tooltip("§eItens adquiridos:", "", " §f" + reward.get(player.getName()).getName(), "", "§7Data do ocorrido: §f" + new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()) + "§7 às §f" + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
										broadcast.send(Bukkit.getOnlinePlayers());
									}
									
									player.sendMessage(reward.get(player.getName()).getRarity().getDisplayName() + " §7Você ganhou \"§f" + reward.get(player.getName()).getName() + "§7\" para utilizar no \"§fFactions Retrô§7\".");
									
									new BukkitRunnable() {
										
										@Override
										public void run() {
											for (Entry<Integer, ArmorStand> entry : current.entrySet()) {
												ArmorStand armorStand = entry.getValue();
												armorStand.remove();
											}
											
											changeEnderChestState(caixa.getLocation(), false);
											HubyEngradados.getCaixaManager().createHologram();
											
											Item item = reward.get(player.getName());
											if (item.getFisic() != null) {
												player.getInventory().addItem(item.getFisic());
											}
											
											current.clear();
											locations.clear();
											rewards.clear();
											
											totem = true;
										}
									}.runTaskLater(HubyEngradados.getInstance(), 20 * 4);
									cancel();
								}
								scoreCurrent++;
							}
						}.runTaskTimer(HubyEngradados.getInstance(), 7, 7);
					}				
				}
			}.runTaskTimer(HubyEngradados.getInstance(), 7, 7);
		} else {
			player.sendMessage("§cEste totem já está sendo utilizado por outro jogador.");
		}
	} 
	
	public ArrayList<Location> spawn(Location center, double radius, int amount, Caixa caixa) {
		World world = center.getWorld();
		double increment = (2 * Math.PI) / amount;
		ArrayList<Location> locations = new ArrayList<Location>();
		
		for (int i = 0; i < amount; i++) {
			double angle = i * increment;
			double y = center.getY() + (radius * Math.cos(angle));
			double z = center.getZ() + (radius * Math.sin(angle));
			Location location = null;
			
			location = new Location(world, center.getX(), y, z);
			locations.add(location);
			
			ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);
			armorStand.setArms(false);
			armorStand.setBasePlate(false);
			armorStand.setGravity(false);
			armorStand.setSmall(true);
			
			Item item = caixa.getItens().get(new Random().nextInt(caixa.getItens().size()));
			ItemStack fisic = HubyEngradados.getRarityManager().getHeadRarity(item.getRarity());
			
			armorStand.setHelmet(fisic);
			
			armorStand.setVisible(false);
			this.current.put(i, armorStand);
			
			this.locations.add(location);
			this.rewards.add(item);
		}
		return locations;
	}
	
	@SuppressWarnings("deprecation")
	public void changeEnderChestState(Location location, boolean open) {
		Block block = location.getBlock();
		if (block != null) {
			if (block.getType() == Material.ENDER_CHEST | block.getType() == Material.CHEST) {
				byte dataByte = (open) ? (byte) 1 : 0;
				
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.playNote(location, (byte) 1, dataByte);
					
					BlockPosition blockPosition = new BlockPosition(block.getX(), block.getY(), block.getZ());
					PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(blockPosition, net.minecraft.server.v1_8_R3.Block.getById(block.getTypeId()), (byte) 1, dataByte);
					
					((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
				}
			}
		}
	}
}
