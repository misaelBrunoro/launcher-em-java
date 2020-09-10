package Utils;

import Helper.FileHelper;
import Helper.HttpHelper;
import Helper.TrayIconHelper;

public class Utils {
    private FileHelper fileUtil;
    private HttpHelper httpUtil;
    private TrayIconHelper trayIconUtil;

    public FileHelper getFileHelper() {
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
}
