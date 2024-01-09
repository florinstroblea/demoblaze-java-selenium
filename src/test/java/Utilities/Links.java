package Utilities;

public enum Links {
    BASE_URL ("https://www.demoblaze.com/");

    private final String name;

    Links(String name) {this.name = name;}

    public String getName() {return name;}
}
