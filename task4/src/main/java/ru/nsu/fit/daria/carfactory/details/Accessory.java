package ru.nsu.fit.daria.carfactory.details;

public class Accessory implements Detail {
    private static int ID = 1000;
    private long id;

    public Accessory() {
        id = ++ID;
    }

    @Override
    public long getID() {
        return id;
    }
}
