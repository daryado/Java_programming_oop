package ru.nsu.fit.daria.carfactory.gui;

import ru.nsu.fit.daria.carfactory.WorkerStatus;

public interface Updater {
    void update(ComponentName name, int value);

    void updateWorker(long worker_index, WorkerStatus status);
}
