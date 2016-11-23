package domain.controller;

import domain.bulletinboard.Bulletinboard;
import java.util.ArrayList;
import domain.page.Pagemanager;
import domain.user.Usermanager;
import io.swagger.model.*;
import java.util.Date;
import java.util.List;
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
    
    public List<Post> getAllPosts() {
        return bulletinboard.getAllPosts();
    }
    
    public void addNoteToSupplier(String supplierName, String editor, String text) {
    	pagemanager.addNoteToSupplier(supplierName, editor, text);
    }
    
    public Post createPost(String owner, String title, String description, PostType type){
        return bulletinboard.createPost(owner, title, description, type);
    }
    
    public void editPost(Post post, String newDescription, String newTitle) {
        bulletinboard.editPost(post, newDescription, newTitle);
    }
}
