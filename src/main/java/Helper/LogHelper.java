package Helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.*;

import static Utils.Consts.*;

public class LogHelper {
    private Logger LOGGER;

    public LogHelper () throws IOException {
        File folder = new File(logFolderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(logFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileHandler handler = new FileHandler(logFilePath, true);
        handler.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage()
                );
            }
        });
        this.LOGGER = Logger.getLogger(LogHelper.class.getName());
        this.LOGGER.addHandler(handler);
    }

    public Logger getLogger() {
        return this.LOGGER;
    }
}
