package ru.nsu.fit.daria.carfactory.details;

import java.util.logging.Logger;

public class Car implements Detail {
    private final Engine engine;
    private final Accessory accessory;
    private final Body body;
    private long id;

    private static final Logger logger = Logger.getLogger(Car.class.getName());

    public Car(Engine engine, Accessory accessory, Body body, long Id) {
        this.accessory = accessory;
        this.body = body;
        this.engine = engine;
        id = Id;
    }

    @Override
    public long getID() {
        return id;
    }

    public Car finishBuild(){
        logger.info("BUILD A CAR. ID:" + this.getID() + " (Body: " + body.getID() + ", Engine: " +  engine.getID() +", Accessory:" + accessory.getID() + ")");
        return this;
    }
}
