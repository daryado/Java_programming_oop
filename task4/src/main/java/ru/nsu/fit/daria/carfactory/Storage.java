package ru.nsu.fit.daria.carfactory;


import ru.nsu.fit.daria.carfactory.details.Detail;
import ru.nsu.fit.daria.carfactory.gui.ComponentName;
import ru.nsu.fit.daria.carfactory.gui.Updater;

import java.util.ArrayDeque;
import java.util.logging.Logger;

public class Storage<T extends Detail> {
    private final ComponentName name;
    private ArrayDeque<T> storage = new ArrayDeque<>();
    private int capacity;
    private Updater view;
    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    public int getCapacity() {
        return capacity;
    }

    public Storage(int capacity, ComponentName name, Updater view) {
        this.capacity = capacity;
        this.name = name;
        this.view = view;

        logger.info(name + " CREATED");
    }

    public synchronized int size() {
        return storage.size();
    }

    public synchronized void put(T newItem) throws InterruptedException {
        if (storage.size() >= capacity)
            try {
            logger.info("STORAGE " + name + " IS FULL");
            this.wait();
        }catch (InterruptedException e) {
            logger.info(name + " INTERRUPTED IN WAIT");
            throw e;
        }
        logger.info(name + " GOT NEW ITEM " + newItem.toString());
        storage.add(newItem);
        view.update(name, storage.size());
        this.notify();
    }

    public synchronized T get() throws InterruptedException {
        while (true) {
            try{
                if (storage.isEmpty()){
                    logger.info(name + " WAITING FOR A SPARE");
                    this.wait();
                    logger.info(name + " WOKE UP");
                } else{
                    T detail = storage.poll();
                    view.update(name, storage.size());
                    this.notify();
                    logger.info(name + " PASSING PRODUCT");
                    return detail;
                }
            }catch (InterruptedException e) {
                logger.info(name + " INTERRUPTED IN WAIT");
                throw e;
            }
        }
    }
}
