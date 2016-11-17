package domain.database;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import io.swagger.model.Note;
import io.swagger.model.Page;
import io.swagger.model.User;
import io.swagger.model.User.RightsEnum;


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
                return new User().username(result.getString(1)).rights(RightsEnum.PROVIA);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Page> getSuppliers()
    {
        String query = "SELECT public.user.username, public.note.text, public.note.date FROM public.user " +
                            "LEFT JOIN public.note ON public.user.username = public.note.supplier WHERE public.user.rights='Supplier'";
        ArrayList<Page> pageList = new ArrayList<Page>();
        try
        {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
            Page page;
            while (result.next())
            {
                //if(read.IsDBNull(1) && read.IsDBNull(2))
                if(result.getString(2) == null && result.getDate(3) == null)	
                {
                    page = new Page().owner(result.getString(1));
                }
                else
                {
                    page = new Page().owner(result.getString(1)).note(new Note().text(result.getString(2)));

                }
                pageList.add(page);
            }
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
        }
        return pageList;
    }
}
