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
     * @param username
     * @param password
     * Kalder metoden getLogin i database klassen. Database klassen vil returnere et User objekt, som denne metode sender videre tilbage.
     * @return User som er den User der stemmer overens med brugernavnet og passwordet.
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
