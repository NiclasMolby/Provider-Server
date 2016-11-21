package domain.controller;

import domain.bulletinboard.Bulletinboard;
import java.util.ArrayList;
import domain.page.Pagemanager;
import domain.user.Usermanager;
import io.swagger.model.*;
public class Controller {

    private static Controller instance;
    private Usermanager usermanager;
    private Pagemanager pagemanager;
    private Bulletinboard bulletinboard;

    public static Controller getController() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
        usermanager = new Usermanager();
        pagemanager = new Pagemanager();
        bulletinboard = new Bulletinboard();
    }

    public User validate(String username, String password) {
        return usermanager.validate(username, password);
    }

    public ArrayList<Page> getSuppliers() {
        return pagemanager.getSuppliers();
    }
    
    public void addNoteToSupplier(String supplierName, String editor, String text) {
    	pagemanager.addNoteToSupplier(supplierName, editor, text);
    }
    
    public void editPost(Post post, String newDescription, String newTitle)
        {
            bulletinboard.EditPost(post, newDescription, newTitle);
        }
}
