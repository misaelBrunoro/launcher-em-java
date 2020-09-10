package Utils;

public final class Consts {

    public static final String programPath = System.getProperty("user.home") + "/.oncontabil";

    public static final String versionConfigPath = programPath + "/version.config";

    public static final String proxyConfigPath = programPath + "/proxy.config";

    private Consts() {
        throw new AssertionError();
    }
}
