package domain.user;

import domain.controller.Logger;
import domain.database.DatabaseDriver;
import io.swagger.model.*;

public class Usermanager {

    DatabaseDriver database;

    public Usermanager() {
        database = DatabaseDriver.getInstance();
    }

    public User validate(String username, String password) {
        try {
            Logger.get().log(Logger.LogType.INFO, "Bruger " + database.getLogin(username, password).getUsername() + " er logget ind");
            return database.getLogin(username, password);
        }
        catch (NullPointerException e) {
            Logger.get().log(Logger.LogType.WARNING, "Der gik noget galt under login af " + username);
            return null;
        }
    }
}
