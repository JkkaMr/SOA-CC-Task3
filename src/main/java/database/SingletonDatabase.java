package database;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import types.Customer;
import types.ProductCategory;
import types.ShopData;

/**
 * Reads the JSON-file and acts as a dumb database for the web shop
 * Other classes can refer to the database by  SingletonDatabase.getDatabase().getCustomers() and SingletonDatabase.getDatabase().GetProductCategories()
 */
class SingletonDatabase {
	private List<Customer> savedCustomers;
	private List<ProductCategory> savedProductCategories;

	static SingletonDatabase obj = new SingletonDatabase();

	private SingletonDatabase() {

		if (savedProductCategories == null) {
			try {
				//Käytän vähän spagettikoodimaisesti Jukan ShopData luokan Get-metodeja JSON-tiedoston lukemisessa
				
				ObjectMapper mapper = new ObjectMapper();
				InputStream is = ShopData.class.getResourceAsStream("/shopdata.json");
				
				ShopData shopData = mapper.readValue(is, ShopData.class);
				
				savedCustomers = shopData.getCustomers();
				savedProductCategories = shopData.getProductCategories();
			} catch (Exception e) {
				e.printStackTrace();
				savedProductCategories = null;
			}
		}

	}

	public static SingletonDatabase getDatabase() {
		return obj;
	}

	public List<Customer> getCustomers() {
		return savedCustomers;
	}

	public List<ProductCategory> GetProductCategories() {
		return savedProductCategories;
	}

	
	/*
	//Nopea testaus:
	public static void main(String[] args) {
		List<Customer> testiA = SingletonDatabase.getDatabase().getCustomers();
		List<ProductCategory> testiB = SingletonDatabase.getDatabase().GetProductCategories();
		}
		*/

}
