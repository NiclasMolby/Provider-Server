package domain.page;

import io.swagger.model.Product;
import java.util.ArrayList;

public interface IPagemanager {
    
    ArrayList getSuppliers();
    
    void addNoteToSupplier(String supplierName, String editor, String text);
    
    void deleteProduct(Product product);

    void updatePage(String page, String description, String location, String contactInformation);
    
    void editProduct(Product product, String newProductName, String newChemicalName, 
            double newMolWeight, String newDescription, double newPrice, String newPackaging, String newDeliveryTime);
    
    Product createProduct(String productName, String chemicalName, double molWeight, 
            String description, double price, String packaging, String deliveryTime, String producer);
    
}
