package controller;

public class Main {

    public static void main(String[] args)  {
        Updater updater = new Updater();
        Thread threadUpdater = new Thread(updater);
        threadUpdater.start();
    }

}
