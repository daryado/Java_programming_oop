package ru.nsu.fit.daria.carfactory;

import ru.nsu.fit.daria.carfactory.gui.CarFactoryWindow;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try{
            var window = new CarFactoryWindow();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
