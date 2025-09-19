package com.galaxy_swords.mvc.model;

public enum Rarity {
    COMUM("Comum"),
    INCOMUM("Incomum"),
    RARO("Raro"),
    EPICO("Épico"),
    MITICO("Mítico"),
    LENDARIO("Lendário"),
    GALATICO("Galático");

    private final String label;

    Rarity(String label) {
        this.label = label;
    }

    public String getLabel(String label) {
        return label;
    }

}
