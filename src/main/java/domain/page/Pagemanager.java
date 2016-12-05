package domain.page;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

import common.Logger;
import domain.database.DatabaseDriver;
import io.swagger.model.Note;
import io.swagger.model.Page;
import io.swagger.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pagemanager {

    private DatabaseDriver database;
    private Map<String, Page> pages = new HashMap<>();

    public Pagemanager() {
        database = DatabaseDriver.getInstance();
    }

    public List<Page> getSuppliers() {
        /*List<Page> pages = database.getSuppliers();
        for(Page p : pages) {
            this.pages.put(p.getOwner(), p);
            p.products(database.getProducts(p.getOwner()));
        }*/
        database.getSuppliers().parallelStream()
                .map(page -> {
                    pages.put(page.getOwner(), page);
                    return page;
                }).forEach(page -> 
                        page.products(database.getProducts(page.getOwner())));
        return new ArrayList(pages.values());
    }

    public void addNoteToSupplier(String supplierName, String editor, String text) {
        Note note = new Note().text(text).editor(editor).creationDate(Date.valueOf(LocalDate.now()));
        if(pages.get(supplierName).getNote() == null) {
            database.addNoteToSupplier(supplierName, note);
        }
        else {
            database.editNoteOnSupplier(supplierName, note);
        }
        pages.get(supplierName).setNote(note);
        Logger.get().log(Logger.LogType.INFO, note.getEditor() + " har ændret noten på " + supplierName);
    }
    
    public void deleteProduct(Product product) {
        database.deleteProduct(product);
    }

    public void updatePage(String page, String description, String location, String contactInformation) {
        database.updatePage(page, description, location, contactInformation);
        Logger.get().log(Logger.LogType.INFO, page + " har ændret sine informationer");
    }

    public void editProduct(Product product, String newProductName, String newChemicalName, String newMolWeight, String newDescription, String newPrice, String newPackaging, String newDeliveryTime) {
        product.setProductName(newProductName);
        product.setChemicalName(newChemicalName);
        product.setMolWeight(newMolWeight);
        product.setDescription(newDescription);
        product.setPrice(newPrice);
        product.setPackaging(newPackaging);
        product.setDeliveryTime(newDeliveryTime);
        DatabaseDriver.getInstance().updateProduct(product); //TODO: opdater posten i databasen
        Logger.get().log(Logger.LogType.INFO, "En eller anden har redigeret productet " + product.getProductName());
    }

    public Product createProduct(String productName, String chemicalName, String molWeight, String description, String price, String packaging, String deliveryTime, String producer) {
        Product product = new Product().productName(productName).chemicalName(chemicalName).molWeight(molWeight).description(description).price(price).packaging(packaging).deliveryTime(deliveryTime).producer(producer);
        product.setId(DatabaseDriver.getInstance().addProduct(product));

        Logger.get().log(Logger.LogType.INFO, product.getProducer() + " har oprettet produktet, " + product.getProductName());
        return product;
    }
}
