package Controller;

import Utils.Utils;
import org.gradle.util.VersionNumber;

public class Updater extends Utils {
    private String currentVersion;
    private String lastVersion;

    public Updater() {
        this.getTrayIconHelper().GenerateIcon();
        this.setCurrentVersion();
        this.setLastVersion();
    }

    public void start() {
        if (this.getCurrentVersion().compareTo(this.getLastVersion()) < 0) {
            this.getHttpHelper().downloadNewVersion();
        }
    }

    public void setCurrentVersion() {
        this.currentVersion = this.getFileHelper().getConfiguration("currentVersion");
    }

    public void setLastVersion() {
        this.lastVersion = this.getHttpHelper().readWebVersion();
    }

    public VersionNumber getCurrentVersion() {
        return VersionNumber.parse(this.currentVersion);
    }

    public VersionNumber getLastVersion() {
        return VersionNumber.parse(this.lastVersion);
    }
}
