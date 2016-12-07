package domain.controller;

import domain.bulletinboard.Bulletinboard;
import domain.database.DatabaseDriver;
import domain.page.Pagemanager;
import domain.user.Usermanager;
import io.swagger.model.*;

import java.io.File;
import java.util.List;

public class Controller {

    private static Controller instance;
    private Usermanager usermanager;
    private Pagemanager pagemanager;
    private Bulletinboard bulletinboard;
    private Object updateLock = new Object();

    public static Controller getController() {
        if(instance == null) {
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
        new Thread(() -> {
            if(requestUpdate()) {
                System.out.println("true");
            }
            else {
                System.out.println("false");
            }
        }).start();
        return usermanager.validate(username, password);
    }

    public List<Page> getSuppliers() {
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
            pagemanager.addNoteToSupplier(supplierName, editor, text);
            updateLock.notifyAll();
        }
    }

    public Post createPost(String owner, String title, String description, PostType type) {
        synchronized (updateLock) {
            updateLock.notifyAll();
            return bulletinboard.createPost(owner, title, description, type);
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
        return new File(DatabaseDriver.getInstance().getPDFFilePath(productID));
    }

    public boolean requestUpdate() {
        synchronized (updateLock) {
            try {
                updateLock.wait();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
