package domain.controller;

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
        System.out.println(FORMAT.format(new Date()) + " [" + type.toString() + "] " + message);
    }
    
}
