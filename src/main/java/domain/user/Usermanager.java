package domain.user;

import common.*;
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
            Logger.log(LogType.INFO, "Bruger " + database.getLogin(username, password).getUsername() + " er logget ind");
            return database.getLogin(username, password);
        }
        catch (NullPointerException e) {
            Logger.log(LogType.WARNING, "Der gik noget galt under login af " + username);
            return null;
        }
    }
}
