package com.hubymc.engradados.listeners;

import java.text.DecimalFormat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.hubymc.cash.dados.Dados;
import com.hubymc.cash.message.CashUpdate;
import com.hubymc.engradados.HubyEngradados;
import com.hubymc.engradados.commands.EngradadosCommands;
import com.hubymc.engradados.inventorys.Confirm;
import com.hubymc.engradados.inventorys.Fragments;
import com.hubymc.engradados.inventorys.Shop;
import com.hubymc.engradados.objetos.Caixa;
import com.hubymc.engradados.objetos.scroller.CaixaScroller;
import com.hubymc.engradados.objetos.user.User;

public class PlayerListeners implements Listener {
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!HubyEngradados.getUserManager().existsUser(player.getName())) {
			User user = new User(player.getName());
			HubyEngradados.getUserManager().getUsers().add(user);
		}
		
		HubyEngradados.getCaixaManager().createHologram(player);
	}
	 
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		for (String placeHolder : HologramsAPI.getRegisteredPlaceholders(HubyEngradados.getInstance())) {
			if (placeHolder.equalsIgnoreCase("{caixas:" + player.getName() + "}")) {
				HologramsAPI.unregisterPlaceholder(HubyEngradados.getInstance(), "{caixas:" + player.getName() + "}");
				HubyEngradados.getCaixaManager().cache.get(player.getName()).delete();
				HubyEngradados.getCaixaManager().cache.remove(player.getName());
				break;
			}
		}
	} 
	
	@EventHandler
	public void onPlayerKickEvent(PlayerKickEvent event) {
		Player player = event.getPlayer();
		
		for (String placeHolder : HologramsAPI.getRegisteredPlaceholders(HubyEngradados.getInstance())) {
			if (placeHolder.equalsIgnoreCase("{caixas:" + player.getName() + "}")) {
				HologramsAPI.unregisterPlaceholder(HubyEngradados.getInstance(), "{caixas:" + player.getName() + "}");
				HubyEngradados.getCaixaManager().cache.get(player.getName()).delete();
				HubyEngradados.getCaixaManager().cache.remove(player.getName());
				break;
			}
		}
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player) {
			Player player = (Player) event.getWhoClicked();
			if (event.getInventory().getName().equalsIgnoreCase("§8Caixa Misteriosa")) {
				event.setCancelled(true);
				
				User user = HubyEngradados.getUserManager().getUser(player.getName());
				
				if (event.getClick() == ClickType.RIGHT | event.getClick() == ClickType.LEFT) {
					int slot = event.getRawSlot();
					
					if (slot == 39) {
						Shop.getNewInventory(player);
					} else if (slot == 41) {
						Fragments.getNewInventory(player);
					} else if (slot == 44) {
						if (event.getCurrentItem() != null) {
							if (event.getCurrentItem().getType() != Material.AIR) {
								int scroller = HubyEngradados.getUserManager().scroller.get(player.getName());
								HubyEngradados.getUserManager().getNewInventory(player, (scroller + 1));
							}
						}
					} else if (slot == 36) {
						if (event.getCurrentItem() != null) {
							if (event.getCurrentItem().getType() != Material.AIR) {
								int scroller = HubyEngradados.getUserManager().scroller.get(player.getName());
								HubyEngradados.getUserManager().getNewInventory(player, scroller--);
							}
						}
					} else {
						ItemStack current = event.getCurrentItem();
						if (current != null && current.getType() != Material.AIR) {
							if (!user.getCaixas().isEmpty()) {
								for (Caixa caixa : user.getCaixas()) {
									if (caixa.getItemFisic().equals(current)) {
										player.closeInventory();
										
										CaixaScroller scroller = new CaixaScroller();
										scroller.runnable(player, caixa);
										
										break;
									}
								}
							}
						}
					}
				}
			} else if (event.getInventory().getName().equalsIgnoreCase("§8Confirmação§e§g§r§a§d§a§d§o§s")) {
				event.setCancelled(true);
				
				if (event.getClick() == ClickType.RIGHT | event.getClick() == ClickType.LEFT) {
					int slot = event.getRawSlot();
					
					if (slot == 24) {
						player.closeInventory();
						player.sendMessage("§cOperação cancelada.");
						if (Confirm.cache.containsKey(player.getName())) {
							Confirm.cache.remove(player.getName());
						}
					} else if (slot == 20) {
						if (Confirm.cache.containsKey(player.getName())) { 
							Caixa caixa = Confirm.cache.get(player.getName());
							if (caixa != null) {
								int cost = caixa.getCost();
								if (Dados.getCash(player.getName()) >= cost) {
										
									User user = HubyEngradados.getUserManager().getUser(player.getName());
									
									player.sendMessage("§aVocê comprou uma Caixa Misteriosa " + Confirm.cache.get(player.getName()).getDisplayName() + "§a.");
									player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 10.0F, 1.0F);
										
									user.getCaixas().add(caixa);
									
									player.closeInventory();
									Confirm.cache.remove(player.getName());
									
									Dados.removeCash(player.getName(), cost);
									CashUpdate.sendNewUpdate(player);
								} else {
									player.sendMessage("§cVocê precisa de " + new DecimalFormat("#,###.#").format(cost) + " cash para efetuar a compra.");
								}
							}
						} else {
							player.sendMessage("§cRefaça a operação! Você demorou mais que 15 segundos para concluir a operação.");
							player.closeInventory();
						}
					}
				}
			} else if (event.getInventory().getName().equalsIgnoreCase("§8Comprar Caixas Misteriosas")) {
				event.setCancelled(true);
				
				if (event.getClick() == ClickType.RIGHT | event.getClick() == ClickType.LEFT) {
					int slot = event.getRawSlot();
					
					if (slot == 30) {
						HubyEngradados.getUserManager().getNewInventory(player, 1);
					} else if (slot == 32) {
						Fragments.getNewInventory(player);
					} else {
						ItemStack current = event.getCurrentItem();
						if (current != null) {
							for (Caixa caixa : HubyEngradados.getCaixaManager().getCaixas()) {
								if (current.equals(caixa.getItemFisic(player.getName()))) {
									Confirm.getNewInventory(player, caixa);
									break;
								}
 							}
						}
					}
				}
			} else if (event.getInventory().getName().equalsIgnoreCase("§8Fabricar Caixas Misteriosas")) {
				event.setCancelled(true);
				
				if (event.getClick() == ClickType.RIGHT | event.getClick() == ClickType.LEFT) {
					int slot = event.getRawSlot();
					
					if (slot == 32) {
						HubyEngradados.getUserManager().getNewInventory(player, 1);
					} else if (slot == 30) {
						Shop.getNewInventory(player);
					} else {
						ItemStack current = event.getCurrentItem();
						User user = HubyEngradados.getUserManager().getUser(player.getName());
						int[] cost = new int[] { 150, 220, 310 };
						int currentNumber = 0;
						
						if (current != null) {
							for (Caixa caixa : HubyEngradados.getCaixaManager().getCaixas()) {
								if (current.equals(caixa.getItemFisic(player.getName(), cost[currentNumber], user))) {
									if (Dados.getCash(player.getName()) >= cost[currentNumber]) {
											
										player.sendMessage("§aVocê fabricou uma Caixa Misteriosa " + Confirm.cache.get(player.getName()).getDisplayName() + "§a.");
										player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 10.0F, 1.0F);
											
										user.getCaixas().add(caixa);
										player.closeInventory();
										
										Dados.removeCash(player.getName(), cost[currentNumber]);
										CashUpdate.sendNewUpdate(player);
									} else {
										player.sendMessage("§cVocê precisa de " + new DecimalFormat("#,###.#").format(cost[currentNumber]) + " fragmentos para efetuar a compra.");
									}
									break;
								}
								currentNumber++;
 							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		
		if (block != null) {
			if (block.getType() == Material.ENDER_CHEST) {
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (EngradadosCommands.totemInstall.contains(player.getName())) {
						event.setCancelled(true);
						
						HubyEngradados.getInstance().getConfig().set("totem.x", block.getX());
						HubyEngradados.getInstance().getConfig().set("totem.y", block.getY());
						HubyEngradados.getInstance().getConfig().set("totem.z", block.getZ());
						HubyEngradados.getInstance().getConfig().set("totem.world", block.getWorld().getName());
						HubyEngradados.getInstance().saveConfig();
						
						EngradadosCommands.totemInstall.remove(player.getName());
						for (Player allplayer : Bukkit.getOnlinePlayers()) {
							HubyEngradados.getCaixaManager().createHologram(allplayer);
						}
						
						player.sendMessage("§aLocalização do Totem de Caixas Misteriosas definida com sucesso!");
						player.playSound(player.getLocation(), Sound.ORB_PICKUP, 0.5F, 1.0F);
						
						for (Caixa caixa : HubyEngradados.getCaixaManager().getCaixas()) {
							caixa.setLocation(block.getLocation());
						}
						
					} else {
						if (HubyEngradados.getCaixaManager().existsTotem(block.getLocation())) {
							event.setCancelled(true);
							
							User user = HubyEngradados.getUserManager().getUser(player.getName());
							
							if (!user.getCaixas().isEmpty()) {
								HubyEngradados.getUserManager().getNewInventory(player, 1);
							} else {
								Shop.getNewInventory(player);
							}
						} 
					}
				} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if (HubyEngradados.getCaixaManager().existsTotem(block.getLocation())) {
						event.setCancelled(true);
						
						User user = HubyEngradados.getUserManager().getUser(player.getName());
						
						if (!user.getCaixas().isEmpty()) {
							HubyEngradados.getUserManager().getNewInventory(player, 1);
						} else {
							Shop.getNewInventory(player);
						}
					} 
				}
			}
		}
	}
}
