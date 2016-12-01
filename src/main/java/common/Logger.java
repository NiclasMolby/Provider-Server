package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class Logger {

    public static enum LogType {
        INFO,
        WARNING
    }
    private static Logger instance;
    private final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final File LOG_FOLDER = new File("logs");

    public static Logger get() {
        if(instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private Logger() {
    }

    public void log(LogType type, String message) {
        String msg = FORMAT.format(new Date()) + " [" + type.toString() + "] " + message;
        System.out.println(msg);
        saveToLogFile(msg);
    }

    private void saveToLogFile(String message) {
        LOG_FOLDER.mkdirs();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FOLDER + "/" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".txt", true))) {
            writer.append(message + "\r\n");
        }
        catch(IOException e) {
            System.out.println(FORMAT.format(new Date()) + " [" + LogType.WARNING.toString() + "] unable to log the message: " + message);
        }
    }
}
