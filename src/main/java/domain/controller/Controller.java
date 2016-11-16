package domain.controller;
import java.util.ArrayList;

import domain.page.Pagemanager;
import domain.user.Usermanager;
import io.swagger.model.*;

public class Controller {

	private static Controller instance;
	private Usermanager usermanager;
	private Pagemanager pagemanager;
	
	public static Controller getController(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}
	
	private Controller() {
		usermanager = new Usermanager();
		pagemanager = new Pagemanager();
	}
	
	
	public User validate(String username, String password){
		return usermanager.validate(username, password);
	}
	
	public ArrayList<Page> getSuppliers(){
		return pagemanager.getSuppliers();
	}
}
