package domain.controller;

import io.swagger.model.Post;
import io.swagger.model.PostType;
import io.swagger.model.Product;
import io.swagger.model.User;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface IController {
    
    User validate(String username, String password);
    
    ArrayList getSuppliers();

    List<Post> getAllPosts();
    
    void deletePost(Post post);

    void addNoteToSupplier(String supplierName, String editor, String text);

    Post createPost(String owner, String title, String description, PostType type);

    void editPost(Post post, String newDescription, String newTitle);

    void deleteProduct(Product product);

    void updatePage(String page, String description, String location, String contactInformation);

    void editProduct(Product product, String newProductName, String newChemicalName, 
            double newMolWeight, String newDescription, double newPrice, String newPackaging, String newDeliveryTime);

    Product createProduct(String productName, String chemicalName, double molWeight, 
            String description, double price, String packaging, String deliveryTime, String producer);

    File getPDF(int productID);

    boolean requestUpdate();
    
}
