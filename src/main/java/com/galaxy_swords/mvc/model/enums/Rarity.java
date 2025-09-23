package com.galaxy_swords.mvc.model.enums;

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

    @Override
    public String toString() {
        return label;
    }

    public String getLabel() {
        return this.label;
    }
}
