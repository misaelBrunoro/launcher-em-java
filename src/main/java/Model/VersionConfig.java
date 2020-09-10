package Model;

public class VersionConfig {
    private String currentVersion;
    private String consultVersion;
    private String downloadVersion;

    public VersionConfig() {

    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getConsultVersion() {
        return consultVersion;
    }

    public void setConsultVersion(String consultVersion) {
        this.consultVersion = consultVersion;
    }

    public String getDownloadVersion() {
        return downloadVersion;
    }

    public void setDownloadVersion(String downloadVersion) {
        this.downloadVersion = downloadVersion;
    }
}
