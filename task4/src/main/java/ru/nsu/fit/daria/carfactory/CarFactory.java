package ru.nsu.fit.daria.carfactory;

import ru.nsu.fit.daria.carfactory.details.Accessory;
import ru.nsu.fit.daria.carfactory.details.Body;
import ru.nsu.fit.daria.carfactory.details.Car;
import ru.nsu.fit.daria.carfactory.details.Engine;
import ru.nsu.fit.daria.carfactory.gui.ComponentName;
import ru.nsu.fit.daria.carfactory.gui.Names;
import ru.nsu.fit.daria.carfactory.gui.Updater;
import ru.nsu.fit.daria.carfactory.tasks.BuildCar;
import ru.nsu.fit.daria.carfactory.tasks.SellCar;
import ru.nsu.fit.daria.carfactory.threads.ThreadPool;
import ru.nsu.fit.daria.carfactory.threads.Task;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
public class CarFactory {
    private int[] values;
    private ArrayList<Thread> threads;
    private Updater view;
    private static final Logger logger = Logger.getLogger(CarFactory.class.getName());

    private final Storage<Engine> engineStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    private final ThreadPool workerThreadPool;
    private final ThreadPool supplierThreadPool;
    private final ThreadPool dealerThreadPool;

    Task supplyAccessory;
    Task supplyEngine;
    Task supplyCarBody;
    Task sellingOrder;
    Task buildingOrder;

    private final IdGenerator idGenerator = new IdGenerator();
    private AtomicInteger producedCarCount;

    public long generateID(){
        return idGenerator.get();
    }

    public CarFactory(int[] values, Updater view) {
        logger.info("CAR FACTORY");
        this.values = values;
        this.view = view;

        threads = new ArrayList<>();
        bodyStorage = new Storage<>(values[Names.bodyStorageSize.ordinal()], ComponentName.body, view);
        accessoryStorage = new Storage<>(values[Names.accessoryStorageSize.ordinal()], ComponentName.accessory, view);
        engineStorage = new Storage<>(values[Names.motorStorageSize.ordinal()], ComponentName.engine, view);
        carStorage = new Storage<>(values[Names.carStorageSize.ordinal()], ComponentName.car, view);

        producedCarCount = new AtomicInteger(0);
        var supplierDelay = values[4];
        var dealerDelay = values[6];

        supplierThreadPool = new ThreadPool(supplierDelay * 3);
        workerThreadPool = new ThreadPool(values[5]);
        dealerThreadPool = new ThreadPool(values[6]);

        supplyAccessory = new Supply<>(this, accessoryStorage, Accessory::new, supplierDelay, Accessory.class);
        supplyEngine = new Supply<>(this, engineStorage, Engine::new,  supplierDelay, Engine.class);
        supplyCarBody = new Supply<>(this, bodyStorage, Body::new, supplierDelay, Body.class);

        buildingOrder = new BuildCar(this, view);

        sellingOrder = new SellCar(this, dealerDelay, view);

    }


    public void run() {
        Thread buildcar = new Thread(() -> {
            while (carStorage.size() < carStorage.getCapacity()){
                supplierThreadPool.addTask(supplyAccessory);
                supplierThreadPool.addTask(supplyEngine);
                supplierThreadPool.addTask(supplyCarBody);
                workerThreadPool.addTask(buildingOrder);
                dealerThreadPool.addTask(sellingOrder);
            }
        });
            buildcar.start();
    }

  public void stopFactory(){
      logger.info("FACTORY :: STOPPING WORKING");

      workerThreadPool.shutdown();
      supplierThreadPool.shutdown();
      dealerThreadPool.shutdown();
      logger.info("FACTORY :: FINISHED WORKING");
  }

    public Storage<Engine> passEngineStorageKey(){
        return engineStorage;
    }
    public Storage<Body> passCarBodyStorageKey(){
        return bodyStorage;
    }
    public Storage<Accessory> passWheelStorageKey(){
        return accessoryStorage;
    }
    public Storage<Car> passCarStorageKey(){
        return carStorage;
    }
    public int getProducedCarCount() {
        return producedCarCount.get();
    }
    public void closeCarOrder(){
        producedCarCount.getAndIncrement();
    }
}
