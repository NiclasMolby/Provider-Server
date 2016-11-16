package domain.page;

import java.util.ArrayList;

import domain.database.DatabaseDriver;
import io.swagger.model.Page;

public class Pagemanager {

	private DatabaseDriver database;
	
	public Pagemanager(){
		database = new DatabaseDriver();
	}
	
	public ArrayList<Page> getSuppliers(){
		for(Page p : database.getSuppliers()){
			System.out.println(p.getOwner());
		}
		return database.getSuppliers();
	}
}
