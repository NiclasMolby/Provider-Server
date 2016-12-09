package database;

import io.swagger.model.Note;
import io.swagger.model.Page;
import io.swagger.model.Post;
import io.swagger.model.Product;
import io.swagger.model.User;
import java.util.List;

public interface IDatabaseDriver {
    
    User getLogin(String username, String password);
    
    List<Page> getSuppliers();
    
    List<Product> getProducts(String pageSupplier);
    
    List<Post> getPosts();
    
    void addNoteToSupplier(String supplierName, Note note);
    
    void editNoteOnSupplier(String supplierName, Note note);
    
    void updatePost(Post post);
    
    int addPost(String owner, Post post);
    
    void deletePost(Post post);
    
    void deleteProduct(Product product);
    
    void updatePage(String page, String description, String location, String contactInformation);
    
    void updateProduct(Product product);
    
    int addProduct(Product product);
    
    String getPDFFilePath(int productID);
    
    void addProductToPage(Product product);
    
}
