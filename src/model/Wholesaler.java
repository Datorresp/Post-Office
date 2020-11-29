package model;

public class Wholesaler {
    private String name;
    private String id;

    public Wholesaler(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "name '" + name + '\'' +", id '" + id + '\'';
    }
}
