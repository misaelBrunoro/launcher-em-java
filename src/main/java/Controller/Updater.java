package Controller;

import Utils.Utils;
import org.gradle.util.VersionNumber;

import java.awt.*;
import java.io.IOException;

public class Updater extends Utils {
    private String currentVersion;
    private String lastVersion;

    public Updater() throws IOException, AWTException {
        this.getTrayIconHelper().GenerateIcon();
        this.setCurrentVersion();
        this.setLastVersion();
    }

    // INICIA ATUALIZAÇÕES
    public void start() throws IOException {
        if (this.getCurrentVersion().compareTo(this.getLastVersion()) < 0) {
            this.getHttpHelper().downloadNewVersion(this.getTrayIconHelper(), this.getFileHelper());
            this.getFileHelper().writeLocalVersion(this.lastVersion);
        }

    }

    // PEGA VERSÃO ATUAL DO ARQUIVO CONFIG
    public void setCurrentVersion() throws IOException {
        this.currentVersion = this.getFileHelper().getConfiguration("currentVersion");
    }

    // PEGA A ULTIMA VERSÃO WEB
    public void setLastVersion() throws IOException {
        this.lastVersion = this.getHttpHelper().readWebVersion(this.getTrayIconHelper(), this.getFileHelper());
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
