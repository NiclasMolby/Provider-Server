package domain.user;

import domain.database.DatabaseDriver;
import io.swagger.model.*;

public class Usermanager {

    DatabaseDriver database;

    public Usermanager() {
        database = new DatabaseDriver();
    }

    public User validate(String username, String password) {
        try {
            System.out.println("[INFO] #Bruger " + database.getLogin(username, password).getUsername() + " er logget ind");
            return database.getLogin(username, password);
        }
        catch (NullPointerException e) {
            System.out.println("[ERROR] Der gik noget galt under login af " + username);
            return null;
        }
        //return new User(username, password);
    }
}
