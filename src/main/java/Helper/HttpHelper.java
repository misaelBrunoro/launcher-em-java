package Helper;


import Model.ProxyConfig;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.logging.Level;

import static Utils.Consts.*;

public class HttpHelper {

    // REQUEST PARA RETORNAR VERSÃO DO SERVIDOR
    public String readWebVersion(TrayIconHelper trayIconHelper, FileHelper fileHelper,  LogHelper logHelper) throws IOException {
        trayIconHelper.setTooltip("Verificando atualizacoes...");
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(fileHelper.getConfiguration("consultVersion"));
        ProxyConfig proxyFile = fileHelper.readProxyFile();
        if (proxyFile != null) {
            HttpHost proxy = new HttpHost(proxyFile.getHostname(), proxyFile.getPort(), proxyFile.getSchema());
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
            } else {
                logHelper.getLogger().log(Level.WARNING, status.getStatusCode()+" "+status.getReasonPhrase());
            }
            return null;
        } finally {
            httpget.releaseConnection();;
        }
    }

    // REQUEST PARA BAIXAR ULTIMA VERSÃO DO SERVIDOR E SALVAR
    public void downloadNewVersion(TrayIconHelper trayIconHelper, FileHelper fileHelper, LogHelper logHelper) throws IOException {
        trayIconHelper.setIcon("amarelo");
        trayIconHelper.setTooltip("Efetuando download de nova versao...");
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(fileHelper.getConfiguration("downloadVersion"));
        ProxyConfig proxyFile = fileHelper.readProxyFile();
        if (proxyFile != null) {
            HttpHost proxy = new HttpHost(proxyFile.getHostname(), proxyFile.getPort(), proxyFile.getSchema());
            RequestConfig config = RequestConfig.custom().setProxy( proxy ).build();
            httpget.setConfig(config);
        }
        try {
            HttpResponse response = httpclient.execute(httpget);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    File file = new File(downloadPath);
                    file.createNewFile();
                    BufferedInputStream bufferedInput = new BufferedInputStream(entity.getContent());
                    BufferedOutputStream bufferedOutput = new BufferedOutputStream(new FileOutputStream(file));
                    int inByte;
                    while ((inByte = bufferedInput.read()) != -1) {
                        bufferedOutput.write(inByte);
                    }
                    bufferedInput.close();
                    bufferedOutput.close();
                }
            } else {
                logHelper.getLogger().log(Level.WARNING, status.getStatusCode()+" "+status.getReasonPhrase());
            }
        } finally {
            trayIconHelper.setIcon("azul");
            httpget.releaseConnection();
        }
    }
}
