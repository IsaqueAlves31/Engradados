package com.hubymc.engradados.lib;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class GroupController {
	
	@SuppressWarnings("deprecation")
	public static String getGroupColor(String player) {
		for (PermissionGroup group : PermissionsEx.getUser(player).getGroups()) {
			if (group.getName().equalsIgnoreCase("Master")) {
				return "§6";
			} else if (group.getName().equalsIgnoreCase("Gerente") | group.getName().equalsIgnoreCase("Huby")) {
				return "§4";
			} else if (group.getName().equalsIgnoreCase("Administrador") | group.getName().equalsIgnoreCase("Hunter") | group.getName().equalsIgnoreCase("YouTuber")) {
				return "§c";
			} else if (group.getName().equalsIgnoreCase("Desenvolvedor") | group.getName().equalsIgnoreCase("Ajudante")) {
				return "§3";
			} else if (group.getName().equalsIgnoreCase("Moderador")) {
				return "§2";
			} else if (group.getName().equalsIgnoreCase("Hermes")) {
				return "§a";
			} else if (group.getName().equalsIgnoreCase("Medusa")) {
				return "§3";
			}
		}
		return "§7";
	}
}
