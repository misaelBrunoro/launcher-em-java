package Utils;

import Helper.FileHelper;
import Helper.HttpHelper;
import Helper.LogHelper;
import Helper.TrayIconHelper;

import java.io.IOException;

// ESSA CLASSE É RESPONSAVEL PELO SINGLETON DOS HELPERS
public class Utils {
    private FileHelper fileUtil;
    private HttpHelper httpUtil;
    private TrayIconHelper trayIconUtil;
    private LogHelper logHelper;

    public FileHelper getFileHelper() throws IOException {
        if (this.fileUtil == null){
            this.fileUtil = new FileHelper();
        }
        return fileUtil;
    }

    public HttpHelper getHttpHelper() {
        if (this.httpUtil == null){
            this.httpUtil = new HttpHelper();
        }
        return httpUtil;
    }

    public TrayIconHelper getTrayIconHelper() {
        if (this.trayIconUtil == null){
            this.trayIconUtil = new TrayIconHelper();
        }
        return trayIconUtil;
    }

    public LogHelper getLogHelper() {
        try {
            if (this.logHelper == null) {
                this.logHelper = new LogHelper();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return logHelper;
    }
}
