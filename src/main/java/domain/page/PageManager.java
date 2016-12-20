package domain.page;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import common.*;
import database.DatabaseFacade;
import database.IDatabaseFacade;
import domain.controller.Controller;
import domain.security.RSA;
import io.swagger.model.Note;
import io.swagger.model.Page;
import io.swagger.model.Product;
import java.util.ArrayList;
import java.util.Map;

public class PageManager implements IPageManager {

    private IDatabaseFacade database;
    private static IPageManager instance;
    private Map<String, Page> pages;
    private Object updateLock = Controller.updateLock;


    private PageManager() {
        database = DatabaseFacade.getInstance();
        pages = new HashMap<>();
    }

    public static IPageManager getPageManager() {
        if(instance == null) {
            instance = new PageManager();
        }
        return instance;
    }

    /**
     * Gets all the suppliers pages.
     * @return a list with all the suppliers.
     */
    public ArrayList getSuppliers() {
        database.getSuppliers()
                .parallelStream()
                .map(page -> {
                    pages.put(page.getOwner(), page);
                    return page;
                })
                .forEach(page -> page.products(database.getProducts(page.getOwner())));
        return new ArrayList(pages.values());
    }

    /**
     * Adds or edit a note in the database and logs it.
     * @param supplierName The name of the supplier which note will be edited.
     * @param editor The name of the User who edited the note.
     * @param text The edited note.
     */
    public void addNoteToSupplier(String supplierName, String editor, String text) {
        synchronized (updateLock) {
            if(!text.isEmpty()) {
                text = new String(RSA.getRSA().decrypt(text));
            }
            Note note = new Note().text(text).editor(editor).creationDate(Date.valueOf(LocalDate.now()));
            if (pages.get(supplierName).getNote() == null) {
                database.addNoteToSupplier(supplierName, note);
            } else {
                database.editNoteOnSupplier(supplierName, note);
            }
            pages.get(supplierName).setNote(note);
            Logger.log(LogType.INFO, note.getEditor() + " har ændret noten på " + supplierName);
            updateLock.notifyAll();
        }
    }

    /**
     * Deletes a product from the database and logs it.
     * @param product The product that will be deleted.
     */
    public void deleteProduct(Product product) {
        synchronized (updateLock) {
            database.deleteProduct(product);
            Logger.log(LogType.INFO, "Produktet med titlen " + product.getProductName() + " er blevet slettet");
            updateLock.notifyAll();
        }
    }

    public void updatePage(String page, String description, String location, String contactInformation) {
        synchronized (updateLock) {
            database.updatePage(page, description, location, contactInformation);
            Logger.log(LogType.INFO, page + " har ændret sine informationer");
            updateLock.notifyAll();
        }
    }

    /**
     * Edit a already existing product and saves it in the database and logs it. The database updates the product in the
     * database with the same product ID.
     * @param product The product that will be updated.
     * @param newProductName Product name.
     * @param newChemicalName Chemical name.
     * @param newMolWeight MolWeight.
     * @param newDescription Description.
     * @param newPrice Price.
     * @param newPackaging Packaging.
     * @param newDeliveryTime Delivery time.
     */
    public void editProduct(Product product, String newProductName, String newChemicalName, double newMolWeight, String newDescription, double newPrice, String newPackaging, String newDeliveryTime) {
        synchronized (updateLock) {
            product.setProductName(newProductName);
            product.setChemicalName(newChemicalName);
            product.setMolWeight(newMolWeight);
            product.setDescription(newDescription);
            product.setPrice(newPrice);
            product.setPackaging(newPackaging);
            product.setDeliveryTime(newDeliveryTime);
            DatabaseFacade.getInstance().updateProduct(product);
            Logger.log(LogType.INFO, "Produktet " + product.getProductName() + " er blevet ændret");
            updateLock.notifyAll();
        }
    }

    /**
     * Creates a new Product and logs it.
     * @param productName The product name.
     * @param chemicalName The chemicalName.
     * @param molWeight The mol weight.
     * @param description The description.
     * @param price The Price.
     * @param packaging The packaging.
     * @param deliveryTime The expected delivery time.
     * @param producer The producer of the product.
     * @return The new product.
     */
    public Product createProduct(String productName, String chemicalName, double molWeight, String description, double price, String packaging, String deliveryTime, String producer) {
        synchronized (updateLock) {
            Product product = new Product().productName(productName).chemicalName(chemicalName).molWeight(molWeight).description(description).price(price).packaging(packaging).deliveryTime(deliveryTime).producer(producer);
            product.setId(DatabaseFacade.getInstance().addProduct(product));
            DatabaseFacade.getInstance().addProductToPage(product);

            Logger.log(LogType.INFO, product.getProducer() + " har oprettet et produkt med navnet " + product.getProductName());
            updateLock.notifyAll();
            return product;
        }
    }
}
