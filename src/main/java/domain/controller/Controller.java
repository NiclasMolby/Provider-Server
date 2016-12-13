package domain.controller;

import common.*;
import domain.bulletinboard.*;
import domain.page.*;
import domain.user.*;
import database.*;
import io.swagger.model.*;
import security.RSA;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller implements IController {

    private static IController instance;
    private IUsermanager usermanager;
    private IPagemanager pagemanager;
    private IBulletinboard bulletinboard;
    private RSA rsa;
    private final Object updateLock = new Object();
    private PublicKey publicKey;

    public static IController getController() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
        rsa = new RSA();
        publicKey = rsa.getPublicKey();
        usermanager = new Usermanager();
        pagemanager = new Pagemanager();
        bulletinboard = new Bulletinboard();
    }

    public User validate(String username, String password) {
        return usermanager.validate(username, password);
    }

    public synchronized ArrayList getSuppliers() {
        return pagemanager.getSuppliers();
    }

    public List<Post> getAllPosts() {
        return bulletinboard.getAllPosts();
    }

    /**
     * Deletes the provided post from the database
     *
     * @param post The post that should be deleted
     */
    public void deletePost(Post post) {
        synchronized (updateLock) {
            bulletinboard.deletePost(post);
            updateLock.notifyAll();
        }
    }

    public void addNoteToSupplier(String supplierName, String editor, String text) {
        synchronized (updateLock) {
            String newText = new String(rsa.decrypt(text));
            pagemanager.addNoteToSupplier(supplierName, editor, newText);
            updateLock.notifyAll();
        }
    }

    public Post createPost(String owner, String title, String description, PostType type) {
        synchronized (updateLock) {
            Post newPost = bulletinboard.createPost(owner, title, description, type);
            updateLock.notifyAll();
            return newPost;
        }
    }

    public void editPost(Post post, String newDescription, String newTitle) {
        synchronized (updateLock) {
            bulletinboard.editPost(post, newDescription, newTitle);
            updateLock.notifyAll();
        }
    }

    public void deleteProduct(Product product) {
        synchronized (updateLock) {
            pagemanager.deleteProduct(product);
            updateLock.notifyAll();
        }
    }

    public void updatePage(String page, String description, String location, String contactInformation) {
        synchronized (updateLock) {
            pagemanager.updatePage(page, description, location, contactInformation);
            updateLock.notifyAll();
        }
    }

    public void editProduct(Product product, String newProductName, String newChemicalName, double newMolWeight, String newDescription, double newPrice, String newPackaging, String newDeliveryTime) {
        synchronized (updateLock) {
            pagemanager.editProduct(product, newProductName, newChemicalName, newMolWeight, newDescription, newPrice, newPackaging, newDeliveryTime);
            updateLock.notifyAll();
        }
    }

    public Product createProduct(String productName, String chemicalName, double molWeight, String description, double price, String packaging, String deliveryTime, String producer) {
        synchronized (updateLock) {
            updateLock.notifyAll();
            return pagemanager.createProduct(productName, chemicalName, molWeight, description, price, packaging, deliveryTime, producer);
        }
    }

    public File getPDF(int productID) {
        return new File(DatabaseFacade.getInstance().getPDFFilePath(productID));
    }

    public boolean requestUpdate() {
        synchronized (updateLock) {
            try {
                updateLock.wait();
            }
            catch(InterruptedException e) {
                Logger.log(LogType.WARNING, "Update-låsen blev afbrudt\n" + e);
            }
            return true;
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
