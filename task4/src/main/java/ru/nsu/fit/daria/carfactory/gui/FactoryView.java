package ru.nsu.fit.daria.carfactory.gui;

import ru.nsu.fit.daria.carfactory.CarFactory;
import ru.nsu.fit.daria.carfactory.WorkerStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class FactoryView extends JFrame implements Updater {
    private final int BORDER_TOP = 15;
    private final int BORDER_LEFT = 15;
    private final int BORDER_RIGHT = 15;
    private final int BORDER_BOTTOM = 15;

    private final int PROGRESS_BARS = 5;

    private final int SET_SIZE_WIDTH = 500;
    private final int SET_SIZE_HEIGHT = 500;

    private int[] values;
    private String[] names;
    private JPanel content;
    private JProgressBar[] progressBars;
    private JLabel[] storages;
    private int[] capacities;
    private JLabel jCarsSold;
    private JLabel[] workerStatus;
    private CarFactory factory;
    private int n;

    public FactoryView(int[] values) {
        super("Factory");

        this.values = values;
        factory = new CarFactory(values, this);
        content = new JPanel();
        jCarsSold = new JLabel("0");
        n = values[Names.workers.ordinal()] + PROGRESS_BARS;
        workerStatus = new JLabel[n - PROGRESS_BARS];
        progressBars = new JProgressBar[PROGRESS_BARS - 1];
        capacities = new int[PROGRESS_BARS - 1];
        storages = new JLabel[PROGRESS_BARS - 1];
        content.setBorder(BorderFactory.createEmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));
        content.setLayout(new GridLayout(n, 1));

        this.initNames();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                factory.stopFactory();
                try {
                    new CarFactoryWindow();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initContent();
        this.add(content);
        this.setMinimumSize(new Dimension(SET_SIZE_WIDTH, SET_SIZE_HEIGHT));

        this.pack();
        this.setVisible(true);
    }

    public void startFactory() {
        factory.run();
    }

    private void initNames() {
        names = new String[n];
        names[0] = "Accessories";
        names[1] = "Bodies";
        names[2] = "Motors";
        names[3] = "Cars";
        names[4] = "Cars sold";
        for (int i = PROGRESS_BARS; i < n; i++) {
            names[i] = "Worker " + (i - PROGRESS_BARS + 1);
        }
    }

    private void initContent() {
        JPanel[] panels = new JPanel[n];
        for (int i = 0; i < n; i++) {
            panels[i] = new JPanel(new GridLayout(1, 2));
            content.add(panels[i]);
        }
        for (int i = 0; i < PROGRESS_BARS - 1; i++) {
            progressBars[i] = new JProgressBar(0, values[i]);
            capacities[i] = values[i];
            storages[i] = new JLabel(String.format("%s (%d/%d)", names[i], 0, capacities[i]));
            panels[i].add(storages[i]);
            panels[i].add(progressBars[i]);
        }
        panels[PROGRESS_BARS - 1].add(new JLabel(names[4]));
        panels[PROGRESS_BARS - 1].add(jCarsSold);
        for (int i = PROGRESS_BARS; i < n; i++) {
            workerStatus[i - PROGRESS_BARS] = new JLabel("Waiting");
            panels[i].add(new JLabel(names[i]));
            panels[i].add(workerStatus[i - PROGRESS_BARS]);
        }
    }


    @Override
    public synchronized void update(ComponentName name, int value) {
        if (name == ComponentName.soldCars) {
            jCarsSold.setText(String.valueOf(value));
        } else {
            storages[name.ordinal()].setText(String.format("%s (%d/%d)", names[name.ordinal()], value, capacities[name.ordinal()]));
            progressBars[name.ordinal()].setValue(value);
        }
    }

    @Override
    public synchronized void updateWorker(long worker_index, WorkerStatus status) {
        String text;
        switch (status) {
            case nojob:
                text = "Waiting for work";
                break;
            case storage:
                text = "Buiding car";
                break;
            case working:
                text = "Working";
                break;
            default:
            case details:
                text = "Waiting for details";
        }
        workerStatus[(int)worker_index].setText(text);
    }
}
