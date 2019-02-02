package com.hubymc.engradados.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import com.hubymc.engradados.HubyEngradados;
import com.hubymc.engradados.objetos.Caixa;
import com.hubymc.engradados.objetos.item.Item;
import com.hubymc.engradados.objetos.user.User;

public class DatabaseFactory {
	
	public static void register(User user) {
		try {
			String name = user.getName();
			int current = 0; StringBuilder builder = new StringBuilder();
			int currentLastItens = 0; StringBuilder builderLastItens = new StringBuilder();
			List<Caixa> caixas = user.getCaixas();
			HashMap<Item, Integer> lastItens = user.getLastItensMap();
			
			if (!caixas.isEmpty()) {
				for (Caixa caixa : caixas) {
					current++;
					builder.append(caixa.getName());
						
					if (current < caixas.size()) {
						builder.append("@");
					} else {
						break;
					}
				}
			} else {
				builder.append("Nenhuma");
			}
			
			if (!lastItens.isEmpty()) {
				for (Entry<Item, Integer> entry : lastItens.entrySet()) {
					Item item = entry.getKey();
					int position = entry.getValue();
					
					currentLastItens++;
					builderLastItens.append(item.getName() + ":" + position);
						
					if (currentLastItens < lastItens.size()) {
						builderLastItens.append("@");
					} else {
						break;
					}
				}
			} else {
				builderLastItens.append("Nenhuma");
			}
			
			if (existsRegister(user)) {
				Connection connection = HubyEngradados.getSQLite().getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement("UPDATE engradados SET caixas = '" + builder.toString() + "', lastItens = '" + builderLastItens.toString() + "', fragmentos = '" + user.getFragments() + "' WHERE name = '" + name + "';");
				prepareStatement.executeUpdate();
				prepareStatement.close();
				connection.close();
			} else {
				Connection connection = HubyEngradados.getSQLite().getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO engradados (name, caixas, fragmentos, lastItens) VALUES ('" + name + "', '" + builder.toString() + "', '" + user.getFragments() + "', '" + builderLastItens.toString() + "');");
				prepareStatement.executeUpdate();
				prepareStatement.close();
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadRegisters() {
		try {
			int current = 0;
			Connection connection = HubyEngradados.getSQLite().getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM engradados;");
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String caixas = resultSet.getString("caixas");
				String lastItens = resultSet.getString("lastItens");
				
				User user = new User(name); 
				  
				if (!caixas.equalsIgnoreCase("Nenhuma")) {
					if (caixas.contains("@")) {  
						for (String allcaixa : caixas.split("@")) {
							Caixa caixa = HubyEngradados.getCaixaManager().getCaixa(allcaixa);
							if (caixa != null) {
								user.getCaixas().add(caixa);
							} 
						}
					} else {
						Caixa caixa = HubyEngradados.getCaixaManager().getCaixa(caixas);
						if (caixa != null) {
							user.getCaixas().add(caixa);
						} 
					}
				}
				
				if (!lastItens.equalsIgnoreCase("Nenhuma")) {
					if (lastItens.contains("@")) {   
						for (String allLastItens : lastItens.split("@")) {
							Item item = HubyEngradados.getCaixaManager().getItem(allLastItens.split(":")[0]);
							if (item != null) {
								user.getLastItensMap().put(item, Integer.parseInt(allLastItens.split(":")[1]));
							} 
						}
					} else {
						Item item = HubyEngradados.getCaixaManager().getItem(lastItens.split(":")[0]);
						if (item != null) {
							user.getLastItensMap().put(item, Integer.parseInt(lastItens.split(":")[1]));
						}  
					}
				}
				
				HubyEngradados.getUserManager().getUsers().add(user);
				current++;
			}
			
			Bukkit.getConsoleSender().sendMessage("§b[HubyEngradados] » Foram carregados " + current + " usuários.");
			resultSet.close();
			prepareStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void desregister(User user) {
		try {
			String name = user.getName();
			Connection connection = HubyEngradados.getSQLite().getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM engradados WHERE name = '" + name + "';");
			prepareStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean existsRegister(User user) {
		try {
			String name = user.getName();
			Connection connection = HubyEngradados.getSQLite().getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM engradados WHERE name = '" + name + "';");
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} 
			resultSet.close();
			prepareStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
