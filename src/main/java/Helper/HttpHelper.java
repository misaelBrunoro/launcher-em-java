package Helper;


import Model.ProxyConfig;
import Utils.Utils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static Utils.Consts.*;

public class HttpHelper extends Utils {

    // REQUEST PARA RETORNAR VERSÃO DO SERVIDOR
    public String readWebVersion() {
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(this.getFileHelper().getConfiguration("consultVersion"));
        ProxyConfig proxyFile = this.getFileHelper().readProxyFile();
        if (proxyFile != null) {
            HttpHost proxy = new HttpHost(proxyFile.getHostname(), proxyFile.getPort(), "http");
            RequestConfig config = RequestConfig.custom().setProxy( proxy ).build();
            httpget.setConfig(config);
        }
        try {
            HttpResponse response = httpclient.execute(httpget);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                return content;
            }
            return null;
        } catch (IOException e) {
            return e.getMessage();
        } finally {
            httpget.releaseConnection();;
        }
    }

    // BAIXAR ULTIMA VERSÃO DO SERVIDOR
    public void downloadNewVersion() {
        File myFile = new File(programPath);
        CloseableHttpClient client = HttpClients.createDefault();
        try (CloseableHttpResponse response = client.execute(new HttpGet(this.getFileHelper().getConfiguration("downloadVersion")))) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try (FileOutputStream outstream = new FileOutputStream(myFile)) {
                    entity.writeTo(outstream);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
