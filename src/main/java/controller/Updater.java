package controller;

import Utils.Utils;
import org.gradle.util.VersionNumber;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;

public class Updater extends Utils implements Runnable {
    private String currentVersion;
    private String lastVersion;

    public Updater() {
        try {
            this.getTrayIconHelper().GenerateIcon();
            this.setCurrentVersion();
            this.setLastVersion();
        } catch (IOException | AWTException e) {
            this.getLogHelper().getLogger().log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void run() {
        do {
            if (this.getCurrentVersion().compareTo(this.getLastVersion()) < 0) {
                try {
                    this.getHttpHelper().downloadNewVersion(this.getTrayIconHelper(), this.getFileHelper(), this.getLogHelper());
                    this.getFileHelper().writeLocalVersion(this.lastVersion);
                } catch (IOException e) {
                    this.getLogHelper().getLogger().log(Level.SEVERE, e.getMessage());
                }
            }
            try {
                Thread.sleep(360000);
            } catch (InterruptedException e) {
                this.getLogHelper().getLogger().log(Level.SEVERE, e.getMessage());
            }
        } while (true);
    }

    // PEGA VERSÃO ATUAL DO ARQUIVO CONFIG
    public void setCurrentVersion() throws IOException {
        this.currentVersion = this.getFileHelper().getConfiguration("currentVersion");
    }

    // PEGA A ULTIMA VERSÃO WEB
    public void setLastVersion() throws IOException {
        this.lastVersion = this.getHttpHelper().readWebVersion(this.getTrayIconHelper(), this.getFileHelper(), this.getLogHelper());
    }

    // CONVERTE A VERSÃO E RETORNA
    public VersionNumber getCurrentVersion() {
        return VersionNumber.parse(this.currentVersion);
    }

    // CONVERTE A VERSÃO E RETORNA
    public VersionNumber getLastVersion() {
        return VersionNumber.parse(this.lastVersion);
    }
}
