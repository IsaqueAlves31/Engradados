package com.hubymc.engradados.objetos.enums;

public enum Rarity {
	Divino("�b[Divino]"), �pico("�6[�pico]"), Raro("�d[Raro]"), Comum("�a[Comum]");
	
	String displayName;
	
	Rarity(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
