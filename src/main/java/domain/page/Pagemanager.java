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
		return database.getSuppliers();
	}
}
