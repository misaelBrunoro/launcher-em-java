package Helper;

import Model.ProxyConfig;

import java.io.*;
import java.util.Properties;
import static Utils.Consts.*;

public class FileHelper {
    public FileHelper() {
        this.createDirectory();
        this.saveFirstProxyFile();
        this.saveFirstVersionFile();
    }

    // CRIA O DIRETORIO PADRÃO CASO INEXISTENTE
    private void createDirectory() {
        File file = new File(programPath);
        if (!file.exists()) {
            try {
                file.mkdir();
            }
            catch(SecurityException se){
               se.printStackTrace();
            }
        }
    }

    // ESCREVE A NOVA VERSAO NO ARQUIVO VERSION.CONFIG
    public String writeLocalVersion(String version) {
        Properties prop = new Properties();
        try {
            prop.setProperty("currentVersion", version);
            prop.store(new FileOutputStream(versionConfigPath), null);
            return "Versão salva com sucesso.";
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }

    // LE O ARQUIVO PROXY.CONFIG E RETORNA UMA CLASSE
    public ProxyConfig readProxyFile() {
        Properties prop = new Properties();
        try {
            FileInputStream is = new FileInputStream(proxyConfigPath);
            prop.load(is);
            if (prop.getProperty("active").equals("true")) {
                ProxyConfig proxy = new ProxyConfig();
                proxy.setHostname(prop.getProperty("hostname"));
                proxy.setPort(Integer.parseInt(prop.getProperty("port")));
                return proxy;
            }
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // CRIA O PRIMEIRO ARQUIVO PROXY.CONFIG
    public void saveFirstProxyFile() {
        File file = new File(proxyConfigPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                Properties prop = new Properties();
                try {
                    prop.setProperty("hostname", "");
                    prop.setProperty("port", "");
                    prop.setProperty("active", "false");
                    prop.store(new FileOutputStream(proxyConfigPath), null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            catch(SecurityException | IOException se){
                se.printStackTrace();
            }
        }
    }

    // CRIA O PRIMEIRO ARQUIVO VERSION.CONFIG
    public void saveFirstVersionFile() {
        File file = new File(versionConfigPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                Properties prop = new Properties();
                try {
                    prop.setProperty("currentVersion", "1.0.0");
                    prop.setProperty("consultVersion", "");
                    prop.setProperty("downloadVersion", "");
                    prop.store(new FileOutputStream(versionConfigPath), null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            catch(SecurityException | IOException se){
                se.printStackTrace();
            }
        }
    }

    // PEGA UMA PROPRIEDADE DO ARQUIVO VERSION.CONFIG
    public String getConfiguration(String name) {
        Properties prop = new Properties();
        try {
            FileInputStream is = new FileInputStream(versionConfigPath);
            prop.load(is);
            return prop.getProperty(name);
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
}
