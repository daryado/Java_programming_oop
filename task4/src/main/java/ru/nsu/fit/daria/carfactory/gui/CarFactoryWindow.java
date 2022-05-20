package ru.nsu.fit.daria.carfactory.gui;

import ru.nsu.fit.daria.carfactory.CarFactory;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class CarFactoryWindow extends JFrame {

    private CarFactory factory;
    private FactoryView factoryView;

    private JPanel content;
    private JPanel main_panel;
    private int[] values;
    private String[] names;
    private JSpinner[] values_spinners;
    private Properties properties;

    private JButton startButton;
    private static final Logger logger = Logger.getLogger(CarFactoryWindow.class.getName());

    private final int BORDER_TOP = 15;
    private final int BORDER_LEFT = 15;
    private final int BORDER_RIGHT = 15;
    private final int BORDER_BOTTOM = 15;

    private final int DEFAULT_STORAGE_SIZE = 100;
    private final int DEFAULT_WORKERS = 2;
    private final int DEFAULT_TIME = 1000;

    private final int SPINNERS_STORAGE_MIN = 5;
    private final int SPINNERS_WORKERS_MIN = 1;
    private final int SPINNERS_TIME_MIN = 100;

    private final int SPINNERS_STORAGE_MAX = 5000;
    private final int SPINNERS_WORKERS_MAX = 16;
    private final int SPINNERS_TIME_MAX = 60000;

    private final int SPINNERS_STORAGE_STEP = 5;
    private final int SPINNERS_WORKERS_STEP = 1;
    private final int SPINNERS_TIME_STEP = 50;

    private final int MENU_SETTINGS = 12;
    private final int SET_SIZE_WIDTH = 500;
    private final int SET_SIZE_HEIGHT = 500;

    public CarFactoryWindow() throws IOException {
        super("Car factory");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = new JPanel();
        main_panel =new JPanel();
        values = new int[MENU_SETTINGS];
        names = new String[MENU_SETTINGS];
        properties = new Properties();
        values_spinners = new JSpinner[MENU_SETTINGS];
        startButton = new JButton("START");

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));
        this.initValues();
        if (!createConfig()) {
            this.assignValues();
        }
        this.initSpinners();
        this.initMainMenuPanel();
        this.initListeners();

        this.setSize(SET_SIZE_WIDTH, SET_SIZE_HEIGHT);
        content.add(main_panel);
        this.add(content);
        this.setVisible(true);

    }

    private void initListeners() {
        startButton.addActionListener(e -> {
            this.saveProperties();
            this.assignValues();
            factoryView = new FactoryView(values);
            this.dispose();
            factoryView.startFactory();
        });
    }

    public void saveProperties() {
        try (FileOutputStream out = new FileOutputStream("config.properties")) {
            for (int i = 0; i < MENU_SETTINGS - 1; i++) {
                properties.setProperty(capitalize(names[i]), values_spinners[i].getValue().toString());
            }
            properties.store(out, null);
        } catch (IOException ignored) {
        }
    }

    public void initMainMenuPanel() {
        main_panel.setLayout(new GridLayout(13, 1));
        JPanel[] panels = new JPanel[MENU_SETTINGS];
        for (int i = 0; i < MENU_SETTINGS; i++) {
            panels[i] = new JPanel(new GridLayout(1, 2));
            panels[i].add(new JLabel(addSpaces(names[i])));
            panels[i].add(values_spinners[i]);
        }

        for (var panel : panels) {
            main_panel.add(panel);
        }

        main_panel.add(startButton);
    }

    public void initSpinners() {
        for (int i = 0; i < 4; i++) {
            values_spinners[i] = new JSpinner(new SpinnerNumberModel(values[i], SPINNERS_STORAGE_MIN, SPINNERS_STORAGE_MAX, SPINNERS_STORAGE_STEP));
        }
        for (int i = 4; i < 7; i++) {
            values_spinners[i] = new JSpinner(new SpinnerNumberModel(values[i], SPINNERS_WORKERS_MIN, SPINNERS_WORKERS_MAX, SPINNERS_WORKERS_STEP));
        }
        for (int i = 7; i < MENU_SETTINGS; i++) {
            values_spinners[i] = new JSpinner(new SpinnerNumberModel(values[i], SPINNERS_TIME_MIN, SPINNERS_TIME_MAX, SPINNERS_TIME_STEP));
        }
    }

    private void initValues() {

        names[0] = "accessoryStorageSize";
        names[1] = "bodyStorageSize";
        names[2] = "motorStorageSize";
        names[3] = "carStorageSize";
        names[4] = "suppliers";
        names[5] = "workers";
        names[6] = "dealers";
        names[7] = "supplyAccessoryTime";
        names[8] = "supplyBodyTime";
        names[9] = "supplyMotorTime";
        names[10] = "workerTime";
        names[11] = "dealerTime";

        values[0] = values[1] = values[2] = values[3] = DEFAULT_STORAGE_SIZE;
        values[4] = values[5] = values[6] = DEFAULT_WORKERS;
        values[7] = values[8] = values[9] = values[10] = values[11] = DEFAULT_TIME;
    }

    private void assignValues() {
        try (InputStream in = new FileInputStream("config.properties")) {
            properties.load(in);
            for (int i = 0; i < MENU_SETTINGS; i++) {
                values[i] = Integer.parseInt(properties.getProperty(capitalize(names[i])));
            }
            logger.info("VALUES ASSIGNED");
        } catch (Throwable ignored) {
        }
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static String addSpaces(String str){
        return Character.toUpperCase(str.charAt(0)) +
                str.substring(1).replaceAll("(?<!_)(?=[A-Z])", " ");
    }

    private boolean createConfig() {
        File config = new File("config.properties");
        if (!config.exists()) {
            try (FileOutputStream out = new FileOutputStream(config)) {
                for (int i = 0; i < MENU_SETTINGS; i++) {
                    properties.setProperty(capitalize(names[i]), String.valueOf(values[i]));
                }
                properties.store(out, null);
            } catch (IOException ignored) {
            }
            return true;
        } else {
            return false;
        }
    }
}
