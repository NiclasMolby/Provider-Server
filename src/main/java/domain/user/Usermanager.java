package domain.user;

import common.*;
import database.DatabaseFacade;
import database.IDatabaseFacade;
import io.swagger.model.*;

public class Usermanager implements IUsermanager {

    private IDatabaseFacade database;

    public Usermanager() {
        database = DatabaseFacade.getInstance();
    }

    /**
     * Calls the database and finds a user if the login is valid.
     * @param username the user Name.
     * @param password The password.
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
