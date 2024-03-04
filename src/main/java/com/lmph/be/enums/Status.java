package com.lmph.be.enums;

public enum Status {

    P ("Pending"),
    O ("Ongoing"),
    D ("Done");

    private String label;

    Status(String l) {
    }

    public static String getLabelOfStatus(String color) {
        for (Status s : values()) {
            if (s.name().equals(color)) {
                return s.label;
            }
        }
        return null;
    }

}
