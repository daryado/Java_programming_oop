package ru.nsu.fit.daria.carfactory.threads;

public interface Task {
    String getName();
    void performWork() throws InterruptedException;
   // void changeParams(int newParam);
}