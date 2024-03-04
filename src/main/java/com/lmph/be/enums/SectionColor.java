package com.lmph.be.enums;

/**
 * Section Color Enums
 * @author Ryan Valmoria
 */
public enum SectionColor {
	
	/*
	 * Blue - means the section has to be customized / reviewed per team
	 * Green - means the section is generic, common for all roles and can be used by all teams
	 * Yellow - means the section is managed or co-managed by the practice lead
	 * 
	 */
	B("Blue"), 
	G("Green"),
	Y("Yellow");
	
	private String label;
	
	SectionColor(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * Method to get the label of the color for the Section
	 * @param color
	 * @return String
	 */
	public static String getLabelOfColor(String color) {
		for (SectionColor sc : values()) {
			if (sc.name().equals(color)) {
				return sc.label;
			}
		}
		return null;
	}
	
	
}
