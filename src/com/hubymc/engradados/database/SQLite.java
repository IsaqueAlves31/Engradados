package com.hubymc.engradados.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.bukkit.Bukkit;

public class SQLite {

	String folder = "HubyEngradados";
	String ULR = "jdbc:sqlite:plugins/" + folder + "/database.db";
	Connection connection;

	public SQLite() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection(this.ULR);
			PreparedStatement prepareStatement = this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS engradados (name TEXT, caixas TEXT, fragmentos INTEGER, lastItens TEXT);");
			prepareStatement.executeUpdate();
			prepareStatement.close();
			this.connection.close();
			Bukkit.getConsoleSender().sendMessage("§b[HubyEngradados] » SQLite conectado com sucesso.");
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§c[HubyEngradados] » Não foi possível conectar ao SQLite.");
		}
	}
	
	public Connection getConnection() throws SQLException {
		if (this.connection.isClosed()) {
			this.connection = DriverManager.getConnection(this.ULR);
		}
		return connection;
	}
}
