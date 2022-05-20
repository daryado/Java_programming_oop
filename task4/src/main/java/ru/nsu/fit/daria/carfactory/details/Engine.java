package ru.nsu.fit.daria.carfactory.details;

public class Engine implements Detail {
    private static int ID = 3000;
    private long id;

    public Engine() {
        id = ++ID;
    }

    @Override
    public long getID() {
        return id;
    }
}

