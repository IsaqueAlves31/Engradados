package com.hubymc.engradados.objetos.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hubymc.engradados.objetos.Caixa;
import com.hubymc.engradados.objetos.item.Item;

public class User {

	String name;
	int fragments = 0;
	List<Caixa> caixas = Lists.newArrayList();
	HashMap<Item, Integer> lastItensMap = Maps.newHashMap();
	List<Entry<Item, Integer>> lastItens = Lists.newArrayList();

	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFragments() {
		return fragments;
	}
	
	public boolean hasFragments(int amount) {
		if (getFragments() >= amount) {
			return true;
		}
		return false;
	}

	public void setFragments(int fragments) {
		this.fragments = fragments;
	}

	public HashMap<Item, Integer> getLastItensMap() {
		return lastItensMap;
	}

	public List<Caixa> getCaixas() {
		return caixas;
	}
	
	public void setCaixas(List<Caixa> caixas) {
		this.caixas = caixas;
	}
	
	public void setLastItensMap(HashMap<Item, Integer> lastItensMap) {
		this.lastItensMap = lastItensMap;
	}

	public List<Entry<Item, Integer>> getLastItens() {
		return lastItens;
	}

	public void setLastItens(List<Entry<Item, Integer>> lastItens) {
		this.lastItens = lastItens;
	}
}
