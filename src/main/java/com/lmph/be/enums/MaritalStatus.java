package com.lmph.be.enums;

/**
 * Marital Status Enums
 * @author Jhun Tiballa
 */
public enum MaritalStatus {
	
	S("Single"), M("Married"), D("Divorced"), W("Widowed"), U("Unknown");
	
	private String label;
	
	MaritalStatus(String l) {
		this.label = l;
	}
	
	public String getLabel() {
		return this.label;
	}
}
