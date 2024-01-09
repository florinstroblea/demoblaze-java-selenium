package Utilities;

public enum CardItems {
    SAMSUNG_GALAXY_PHONE ("Samsung galaxy s6"),
    NOKIA_LUMIA_1520_PHONE ("Nokia lumia 1520");

    public final String name;
    CardItems(String name) {
        this.name = name;
    }
    public String getName() {return name;}
}
