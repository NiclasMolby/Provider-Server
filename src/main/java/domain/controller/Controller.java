package domain.controller;

import common.*;
import domain.bulletinboard.*;
import domain.page.*;
import domain.user.*;
import database.*;
import io.swagger.model.*;
import domain.security.RSA;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller implements IController {

    private static IController instance;
    public static final Object updateLock = new Object();

    public static IController getController() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() { }



    public File getPDF(int productID) {
        return new File(DatabaseFacade.getInstance().getPDFFilePath(productID));
    }

    public boolean requestUpdate() {
        synchronized (updateLock) {
            try {
                updateLock.wait();
            }
            catch(InterruptedException e) {
                Logger.log(LogType.WARNING, "Update-låsen blev afbrudt\n" + e);
            }
            return true;
        }
    }

    public PublicKey getPublicKey() {
        return RSA.getRSA().getPublicKey();
    }
}
