package ru.nsu.fit.daria.carfactory.tasks;

import ru.nsu.fit.daria.carfactory.CarFactory;
import ru.nsu.fit.daria.carfactory.Storage;
import ru.nsu.fit.daria.carfactory.WorkerStatus;
import ru.nsu.fit.daria.carfactory.details.Accessory;
import ru.nsu.fit.daria.carfactory.details.Body;
import ru.nsu.fit.daria.carfactory.details.Car;
import ru.nsu.fit.daria.carfactory.details.Engine;
import ru.nsu.fit.daria.carfactory.gui.Updater;
import ru.nsu.fit.daria.carfactory.threads.Task;

import java.util.logging.Logger;

public class BuildCar implements Task {
    private final CarFactory carFactory;
    private final long workerID;
    private static final Logger logger = Logger.getLogger(BuildCar.class.getName());

    private Updater view;

    private final Storage<Engine> engineStorage;
    private final Storage<Body> carBodyStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    public BuildCar(CarFactory carFactory, Updater view){
        this.carFactory = carFactory;
        this.view = view;
        workerID = carFactory.generateID();
        engineStorage = carFactory.passEngineStorageKey();
        carBodyStorage = carFactory.passCarBodyStorageKey();
        accessoryStorage = carFactory.passWheelStorageKey();
        carStorage = carFactory.passCarStorageKey();
    }

    @Override
    public String getName() {
        return "Build car. Worker ID: " + workerID;
    }

    @Override
    public void performWork() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            view.updateWorker(workerID, WorkerStatus.details);
            Car currentCar = new Car(engineStorage.get(), accessoryStorage.get(),carBodyStorage.get(), carFactory.generateID());
            currentCar.finishBuild();
            logger.info("BUILD A CAR. ID: " + currentCar.getID());
            view.updateWorker(workerID, WorkerStatus.working);
            carStorage.put(currentCar.finishBuild());
            view.updateWorker(workerID, WorkerStatus.storage);
            carFactory.closeCarOrder();
        }
    }
}