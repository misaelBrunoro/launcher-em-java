package Utils;

// APENAS PARA CONSTANTES
public final class Consts {

    public static final String programPath = System.getProperty("user.home") + "/.oncontabil";

    public static final String versionConfigPath = programPath + "/version.config";

    public static final String proxyConfigPath = programPath + "/proxy.config";

    public static final String downloadPath = programPath + "/oncontabilService.jar";

    private Consts() {
        throw new AssertionError();
    }
}
