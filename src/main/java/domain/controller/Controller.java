package domain.controller;

import domain.bulletinboard.Bulletinboard;
import domain.database.DatabaseDriver;
import domain.page.Pagemanager;
import domain.user.Usermanager;
import io.swagger.model.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
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

    public void deletePost(Post post){
        bulletinboard.deletePost(post);
    }

    public User validate(String username, String password) {
        return usermanager.validate(username, password);
    }

    public List<Page> getSuppliers() {
        return pagemanager.getSuppliers();
    }
    
    public List<Post> getAllPosts() {
        return bulletinboard.getAllPosts();
    }
    
    public void addNoteToSupplier(String supplierName, String editor, String text) {
    	pagemanager.addNoteToSupplier(supplierName, editor, text);}
    
    public Post createPost(String owner, String title, String description, PostType type){
        return bulletinboard.createPost(owner, title, description, type);
    }
    
    public void editPost(Post post, String newDescription, String newTitle) {
        bulletinboard.editPost(post, newDescription, newTitle);
    }
    
    public void deleteProduct(Product product) {
        pagemanager.deleteProduct(product);
    }

    public void updatePage(String page, String description, String location, String contactInformation) {
        pagemanager.updatePage(page, description, location, contactInformation);
    }

    public void editProduct(Product product, String newDescription, String newChemicalName, String newMolWeight, String newDescription1, String newPrice, String newPackaging, String newDeliveryTime) {
    }
    public File getPDF(int productID){
        return new File(DatabaseDriver.getInstance().getPDFFilePath(productID));
    }
}
