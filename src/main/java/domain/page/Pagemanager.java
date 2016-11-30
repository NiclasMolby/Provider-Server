package domain.page;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import domain.controller.Logger;
import domain.database.DatabaseDriver;
import io.swagger.model.Note;
import io.swagger.model.Page;
import java.util.List;
import java.util.Map;

public class Pagemanager {

    private DatabaseDriver database;
    private Map<String, Page> pages = new HashMap<>();

    public Pagemanager() {
        database = DatabaseDriver.getInstance();
    }

    public List<Page> getSuppliers() {
        List<Page> pages = database.getSuppliers();
        for(Page p : pages) {
            this.pages.put(p.getOwner(), p);
            p.products(database.getProducts(p.getOwner()));
            //System.out.println("[INFO] " + p.getNote() != null ? "#SupplierNote " +p.getNote().getText()+" " +p.getNote().getCreationDate() : "");
        }
        //System.out.println(pages.toString());
        return pages;
    }

    public void addNoteToSupplier(String supplierName, String editor, String text) {
        Note note = new Note().text(text).editor(editor).creationDate(Date.valueOf(LocalDate.now()));
        if(pages.get(supplierName).getNote() == null) {
            database.addNoteToSupplier(supplierName, note);
        }
        else {
            database.editNoteOnSupplier(supplierName, note);
        }
        Logger.get().log(Logger.LogType.INFO, note.getEditor() + " har ændret noten på " + supplierName);
        pages.get(supplierName).setNote(note);
    }
}
