package ru.nsu.fit.daria.carfactory.tasks;

import ru.nsu.fit.daria.carfactory.CarFactory;
import ru.nsu.fit.daria.carfactory.Storage;
import ru.nsu.fit.daria.carfactory.details.Car;
import ru.nsu.fit.daria.carfactory.gui.ComponentName;
import ru.nsu.fit.daria.carfactory.gui.Updater;
import ru.nsu.fit.daria.carfactory.threads.Task;

import java.util.concurrent.atomic.AtomicInteger;

public class SellCar  implements Task {
    private final long dealerID;
    private final CarFactory factory;
    private final Storage<Car> carStorage;
    private int delay;
    private int cars_sold = 0;

    private Updater view;

    public SellCar(CarFactory factory, int delay, Updater view){
        dealerID = factory.generateID();
        this.view = view;
        this.factory = factory;
        this.delay = delay;
        carStorage = factory.passCarStorageKey();
    }

    @Override
    public String getName() {
        return "Sell car. Dealer ID: " + dealerID;
    }

    @Override
    public void performWork() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()){
            Thread.sleep(delay);
            carStorage.get();
            view.update(ComponentName.soldCars, factory.getProducedCarCount());
        }
    }

}
