package com.lmph.be.enums;

/**
 * Gender Enums
 * @author Jhun Tiballa
 */
public enum Gender{
	M("Male"),
	F("Female"),
	U("Unknown");
	
	private final String label;
	
	
	Gender(String l) {
		this.label = l;
	}
	
	public String getLabel() {
		return this.label;
	}
}
