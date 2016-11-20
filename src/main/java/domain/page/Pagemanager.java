package domain.page;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import domain.database.DatabaseDriver;
import io.swagger.model.Note;
import io.swagger.model.Page;

public class Pagemanager {

	private DatabaseDriver database;
	private HashMap<String, Page> pages = new HashMap<String, Page>();
	
	public Pagemanager(){
		database = new DatabaseDriver();
	}
	
	public ArrayList<Page> getSuppliers(){
		ArrayList<Page> pages = database.getSuppliers();
		for(Page p : pages){
			
			System.out.println("[INFO] #Supplier " +p.getOwner());
			this.pages.put(p.getOwner(), p);
			//System.out.println("[INFO] " + p.getNote() != null ? "#SupplierNote " +p.getNote().getText()+" " +p.getNote().getCreationDate() : "");
		}
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
		pages.get(supplierName).setNote(note);
	}
}
