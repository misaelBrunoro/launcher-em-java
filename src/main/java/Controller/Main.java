package Controller;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args)  {
        try {
            Updater updater = new Updater();
            updater.start();
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }

}
