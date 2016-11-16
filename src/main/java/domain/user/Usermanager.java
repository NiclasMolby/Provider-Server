package domain.user;

import domain.database.DatabaseDriver;
import io.swagger.model.*;
/**
 * Created by niclasmolby on 14/11/2016.
 */
public class Usermanager {

    DatabaseDriver database;

    public Usermanager(){
        database = new DatabaseDriver();
    }
    public User validate(String username, String password){
    	try{
        System.out.println(database.getLogin(username, password).getUsername());
        return database.getLogin(username, password);
    	}
    	catch(NullPointerException e){
    		System.out.println("der gik noget galt!");
    		return null;
    	}
    	//return new User(username, password);
    }
}
