package domain.user;

import common.*;
import database.DatabaseFacade;
import database.IDatabaseFacade;
import domain.security.Hash;
import io.swagger.model.*;

public class UserManager implements IUserManager {

    private IDatabaseFacade database;
    private Hash hash;

    public UserManager() {
        database = DatabaseFacade.getInstance();
        hash = new Hash();
    }

    /**
     * Calls the database and finds a user if the login is valid.
     * @param username the user Name.
     * @param password The password.
     * @return The user that matches the login.
     */
    public User validate(String username, String password) {
        try {
            byte[] salt = database.getSalt(username);
            password = hash.getHashedPassword(password, salt);
            User user = database.getLogin(username, password);
            Logger.log(LogType.INFO, "Bruger " + user.getUsername() + " er logget ind");
            return user;
        }
        catch (NullPointerException e) {
            Logger.log(LogType.WARNING, "Der gik noget galt under login af " + username);
            return null;
        }
    }
}
