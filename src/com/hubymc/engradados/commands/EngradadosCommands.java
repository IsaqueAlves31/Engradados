package com.hubymc.engradados.commands;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.google.common.collect.Lists;
import com.hubymc.engradados.HubyEngradados;
import com.hubymc.engradados.objetos.Caixa;
import com.hubymc.engradados.objetos.user.User;

public class EngradadosCommands implements CommandExecutor {
	
	public static List<String> totemInstall = Lists.newArrayList();
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//		if (sender instanceof Player) {
			if (command.getName().equalsIgnoreCase("engradados")) {
				if (sender.hasPermission("hubymc.engradados.admin")) {
					if (args.length == 0) {
						sender.sendMessage("");
						sender.sendMessage("  §9/engradados add <jogador> <caixa misteriosa> <quantidade> §f- §7Adicione uma Caixa Misteriosa para um jogador.");
						sender.sendMessage("  §9/engradados totem §f- §7Redefina o local do Totem das Caixas Misteriosas.");
						sender.sendMessage("  §9/engradados listar §f- §7Veja a lista de Caixas Misteriosas no servidor.");
						sender.sendMessage("");
					} else {
						if (args[0].equalsIgnoreCase("add")) {
							if (args.length <= 3) {
								sender.sendMessage("§cPor favor, use /engradados add <jogador> <caixa misteriosa> <quantidade>");
							} else {
								String target = args[1];
								if (HubyEngradados.getUserManager().existsUser(target)) {
									User user = HubyEngradados.getUserManager().getUser(target);
									if (HubyEngradados.getCaixaManager().existsCaixa(args[2])) {
										Caixa caixa = HubyEngradados.getCaixaManager().getCaixa(args[2]);
										try {
											int amount = Integer.parseInt(args[3]);
											sender.sendMessage("§aAdicionados §fx" + amount + "§a Caixas Misteriosas " + caixa.getDisplayName() + "§a para §f" + target + "§a!");
											
											for (int i = 1; i <= amount; i++) {
												user.getCaixas().add(caixa);
											}
										} catch (Exception e) {
											sender.sendMessage("§cPor favor, use apenas números.");
										}
									} else {
										sender.sendMessage("§cA Caixa Misteriosa \"" + args[2] + "\" não foi encontrada.");
									}
								} else {
									sender.sendMessage("§cEste usuário está offline.");
								}
							}
						} else if (args[0].equalsIgnoreCase("totem")) {
							if (!totemInstall.contains(sender.getName())) {
								totemInstall.add(sender.getName());
								sender.sendMessage("§aBata em um Baú do Fim para que o totem seja instalado!");
							} else {
								sender.sendMessage("§cVocê cancelou a criação de um novo totem!");
								totemInstall.remove(sender.getName());
							}
						} else if (args[0].equalsIgnoreCase("listar")) {
							sender.sendMessage("");
							sender.sendMessage(" §7Lista de Caixas Misteriosas:");
							sender.sendMessage("");
							for (Caixa caixa : HubyEngradados.getCaixaManager().getCaixas()) {
								sender.sendMessage(" §f• §7Caixa Misteriosa " + caixa.getDisplayName());
							}
							sender.sendMessage("");
						} else {
							sender.sendMessage("");
							sender.sendMessage("  §9/engradados add <jogador> <caixa misteriosa> <quantidade> §f- §7Adicione uma Caixa Misteriosa para um jogador.");
							sender.sendMessage("  §9/engradados totem §f- §7Redefina o local do Totem das Caixas Misteriosas.");
							sender.sendMessage("  §9/engradados listar §f- §7Veja a lista de Caixas Misteriosas no servidor.");
							sender.sendMessage("");
						}
					}
				} else {
					sender.sendMessage("§cVocê precisa do cargo §4Gerente§c ou superior para executar este comando.");
				}
			}
//		}
		return false;
	}
}
