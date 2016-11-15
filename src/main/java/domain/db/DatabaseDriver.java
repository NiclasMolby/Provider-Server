package domain.db;


import java.sql.*;

import io.swagger.model.User;


/**
 * Created by niclasmolby on 14/11/2016.
 */
public class DatabaseDriver {

    private Connection conn;
    private Statement stmt;
    private ResultSet result;

    public DatabaseDriver(){
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://tek-mmmi-db0a.tek.c.sdu.dk:5432/group_2_db", "group_2", "MDI5NTli");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getLogin(String username, String password) throws NullPointerException{

        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM public.user WHERE public.user.username = '" + username + "' AND public.user.password = '" + password + "'");

            while(result.next()){
                return new User().username(result.getString(1)).rights(result.getString(3));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
