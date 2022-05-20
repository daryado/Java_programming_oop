package ru.nsu.fit.daria.carfactory;

import ru.nsu.fit.daria.carfactory.details.Detail;
import ru.nsu.fit.daria.carfactory.threads.Task;

import java.util.function.Supplier;
import java.util.logging.Logger;

public class Supply<T extends Detail> implements Task {

    private static final Logger logger = Logger.getLogger(Supply.class.getName());

    private final CarFactory factory;
    private Storage<T> storage;
    private long supply_time;
    private final Supplier<T> supplier;
    private final Class<T> itemClass;

    Supply(CarFactory factory, Storage<T> storage, Supplier<T> supplier, long supply_time, Class<T> itemClass) {
        this.factory = factory;
        this.supplier = supplier;
        this.storage = storage;
        this.supply_time = supply_time;
        this.itemClass = itemClass;
    }

    @Override
    public void performWork() throws InterruptedException {
        while (true) {
            try {
                Thread.sleep(supply_time);
                long itemID = factory.generateID();
                var detail = supplier.get();
                storage.put(detail);
            } catch (InterruptedException ex) {
                logger.info(Thread.currentThread().getName() + " INTERRUPTED");
                return;
            }
        }
    }

    @Override
    public String getName() {
        return "Supply" + itemClass.getName();
    }

}
