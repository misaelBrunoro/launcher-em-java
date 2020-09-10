package Helper;

import Model.ProxyConfig;
import Model.VersionConfig;

import java.io.*;
import java.util.Properties;
import static Utils.Consts.*;

public class FileHelper {
    public FileHelper() throws IOException {
        this.createDirectory();
        this.saveFirstProxyFile();
        this.saveFirstVersionFile();
    }

    // CRIA O DIRETORIO PADRÃO CASO INEXISTENTE
    private void createDirectory() throws SecurityException {
        File file = new File(programPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    // ESCREVE A NOVA VERSAO NO ARQUIVO VERSION.CONFIG
    public String writeLocalVersion(String version) throws IOException {
        Properties prop = new Properties();
        VersionConfig versionC = this.readVersionFile();
        versionC.setDownloadVersion(getConfiguration("downloadVersion"));
        versionC.setConsultVersion(getConfiguration("consultVersion"));
        prop.setProperty("currentVersion", version);
        prop.setProperty("downloadVersion", versionC.getDownloadVersion());
        prop.setProperty("consultVersion", versionC.getConsultVersion());
        prop.store(new FileOutputStream(versionConfigPath), null);
        return "Versão salva com sucesso.";
    }

    // LE O ARQUIVO PROXY.CONFIG E RETORNA UMA CLASSE
    public ProxyConfig readProxyFile() throws IOException {
        Properties prop = new Properties();
        FileInputStream is = new FileInputStream(proxyConfigPath);
        prop.load(is);
        if (prop.getProperty("active").equals("true")) {
            ProxyConfig proxy = new ProxyConfig();
            proxy.setHostname(prop.getProperty("hostname"));
            proxy.setPort(Integer.parseInt(prop.getProperty("port")));
            proxy.setSchema(prop.getProperty("schema"));
            return proxy;
        }
        return null;
    }

    // LE O ARQUIVO VERSION.CONFIG E RETORNA UMA CLASSE
    public VersionConfig readVersionFile() throws IOException {
        Properties prop = new Properties();
        FileInputStream is = new FileInputStream(versionConfigPath);
        prop.load(is);
        VersionConfig version = new VersionConfig();
        version.setConsultVersion(prop.getProperty("consultVersion"));
        version.setCurrentVersion(prop.getProperty("currentVersion"));
        version.setDownloadVersion(prop.getProperty("downloadVersion"));
        return version;
    }

    // CRIA O PRIMEIRO ARQUIVO PROXY.CONFIG
    public void saveFirstProxyFile() throws IOException{
        File file = new File(proxyConfigPath);
        if (!file.exists()) {
            file.createNewFile();
            Properties prop = new Properties();
            prop.setProperty("hostname", "");
            prop.setProperty("port", "");
            prop.setProperty("schema", "http");
            prop.setProperty("active", "false");
            prop.store(new FileOutputStream(proxyConfigPath), null);
        }
    }

    // CRIA O PRIMEIRO ARQUIVO VERSION.CONFIG
    public void saveFirstVersionFile() throws IOException {
        File file = new File(versionConfigPath);
        if (!file.exists()) {
            file.createNewFile();
            Properties prop = new Properties();
            prop.setProperty("currentVersion", "1.0.0-snapshot");
            prop.setProperty("consultVersion", "http://192.168.15.14/versao");
            prop.setProperty("downloadVersion", "http://192.168.15.14/gestor-1.0.6-SNAPSHOT.jar");
            prop.store(new FileOutputStream(versionConfigPath), null);
        }
    }

    // PEGA UMA PROPRIEDADE DO ARQUIVO VERSION.CONFIG
    public String getConfiguration(String name) throws IOException {
        Properties prop = new Properties();
        FileInputStream is = new FileInputStream(versionConfigPath);
        prop.load(is);
        return prop.getProperty(name);
    }
}
