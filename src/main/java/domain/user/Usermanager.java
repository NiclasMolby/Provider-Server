package domain.user;

import common.Logger;
import domain.database.DatabaseDriver;
import io.swagger.model.*;

public class Usermanager {

    private DatabaseDriver database;

    public Usermanager() {
        database = DatabaseDriver.getInstance();
    }

    /**
     * calls the database and findes a user if the login is valid.
     * @param username
     * @param password
     * @return The user that matches the login.
     */
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
