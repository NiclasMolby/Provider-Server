package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final File LOG_FOLDER = new File("logs");

    public static void log(LogType type, String message) {
        String msg = FORMAT.format(new Date()) + " [" + type.toString() + "] " + message;
        System.out.println(msg);
        saveToLogFile(msg);
    }

    private static void saveToLogFile(String message) {
        LOG_FOLDER.mkdirs();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FOLDER + "/" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".txt", true))) {
            writer.append(message).append("\r\n");
        }
        catch(IOException e) {
            System.out.println(FORMAT.format(new Date()) + " [" + LogType.WARNING.toString() + "] unable to log the message: " + message);
        }
    }
}
