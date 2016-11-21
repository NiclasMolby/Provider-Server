package domain.database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import io.swagger.model.*;
import io.swagger.model.User.RightsEnum;
import io.swagger.model.Post.TypesEnum;

public class DatabaseDriver {

    private static DatabaseDriver instance;
    private Connection connection;
    private Statement stmt;
    private ResultSet result;

    public static DatabaseDriver getInstance() {
        if(instance == null)
            instance = new DatabaseDriver();

        return instance;
    }

    public DatabaseDriver() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://tek-mmmi-db0a.tek.c.sdu.dk:5432/group_2_db", "group_2", "MDI5NTli");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getLogin(String username, String password) throws NullPointerException {
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery("SELECT * FROM public.user WHERE public.user.username = '" + username + "' AND public.user.password = '" + password + "'");

            while (result.next()) {
                return new User().username(result.getString(1)).rights(RightsEnum.PROVIA);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Page> getSuppliers() {
        String query = "SELECT public.user.username, public.note.text, public.note.date, public.note.lasteditor FROM public.user "
                + "LEFT JOIN public.note ON public.user.username = public.note.supplier WHERE public.user.rights='Supplier'";
        ArrayList<Page> pageList = new ArrayList<Page>();
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
            Page page;
            while (result.next()) {
                //if(read.IsDBNull(1) && read.IsDBNull(2))
                if (result.getString(2) == null && result.getDate(3) == null) {
                    page = new Page().owner(result.getString(1));
                } else {
                    page = new Page().owner(result.getString(1)).note(new Note().text(result.getString(2)).creationDate(result.getDate(3)).editor(result.getString(4)));

                }
                pageList.add(page);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return pageList;
    }
    
    public ArrayList<Post> getPosts() {
        String query = "SELECT * FROM public.post";
        ArrayList<Post> postList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
            while (result.next()) {
                TypesEnum type;
                switch(result.getString(5)) {
                    case "Warning":
                        type = TypesEnum.WARNING;
                        break;
                    case "Request":
                        type = TypesEnum.REQUEST;
                        break;
                    case "Offer":
                        type = TypesEnum.OFFER;
                        break;
                    default:
                        type = TypesEnum.NOTAVAILABE;
                        break;
                }
                postList.add(new Post().owner(result.getString(1)).title(result.getString(4)).description(result.getString(2)).types(type).creationDate(result.getDate(3)).id(result.getInt(6)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }
    
    public void addNoteToSupplier(String supplierName, Note note) {
    	String query = "INSERT INTO public.note(supplier, text, date, lasteditor) VALUES ('" + supplierName + "', '" + note.getText() + "', '" + note.getCreationDate() + "', '" + note.getEditor() + "');";
    	try {
    		stmt = connection.createStatement();
    		stmt.execute(query);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void editNoteOnSupplier(String supplierName, Note note) {
    	String query = "UPDATE public.note SET text = '" + note.getText() + "', date = '" + note.getCreationDate() + "', lasteditor = '" + note.getEditor() + "' WHERE public.note.supplier = '" + supplierName + "';";
    	try {
    		stmt = connection.createStatement();
    		stmt.execute(query);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void updatePost(String owner, Post post){    
        
        String query = "UPDATE public.post SET text = '" + post.getDescription() + "', title = '" + post.getTitle() + "' WHERE id = " + post.getId() + ";";
        
        try { 
            stmt = connection.createStatement();
            stmt.execute(query);
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
