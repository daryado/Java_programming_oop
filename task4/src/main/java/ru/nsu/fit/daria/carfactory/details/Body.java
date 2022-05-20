package ru.nsu.fit.daria.carfactory.details;

public class Body implements Detail {
    private static int ID = 2000;
    private long id;

    public Body() {
        id = ++ID;
    }

    @Override
    public long getID() {
        return id;
    }
}
