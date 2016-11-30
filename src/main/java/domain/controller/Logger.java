package domain.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    
    public static enum LogType {
        INFO,
        WARNING
    }
    private static Logger instance;
    private final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public static Logger get() {
        if(instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    private Logger() { }
    
    public void log(LogType type, String message) {
        String msg = FORMAT.format(new Date()) + " [" + type.toString() + "] " + message;
        System.out.println(msg);
        logFile(msg);
    }

    private void logFile(String message) {
        File file = new File("Logs/" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".txt");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(message);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
