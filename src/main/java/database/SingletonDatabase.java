package database;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import errors.DataNotFoundException;
import errors.HandlingException;
import types.Customer;
import types.Product;
import types.ProductCategory;
import types.ShopData;

/**
 * Reads the JSON-file and acts as a dumb database for the web shop
 * Other classes can refer to the database by  SingletonDatabase.getDatabase().getCustomers() and SingletonDatabase.getDatabase().GetProductCategories()
 */
public class SingletonDatabase {
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

	/**
	 * Function returns the list of product categories
	 * @return list of product categories or throws an exception if there was an error
	 */
	public List<ProductCategory> getProductCategories() {
		if (savedProductCategories == null) {
			throw new HandlingException("Could not get categories");
		}
		return savedProductCategories;
	}

	/**
	 * Function returns the requested product category if found
	 * @param categoryId id to find
	 * @return ProductCategory with categoryId, exception if not found (or if there was an error)
	 */
	public ProductCategory getCategory(String categoryId) {
		if (savedProductCategories == null) {
			throw new DataNotFoundException("Category with id "+ categoryId + " not found");
		}
		ProductCategory category = savedProductCategories.stream()
				.filter(cat -> cat.getId().equals(categoryId))
				.findFirst()
				.orElse(null);
		if (category == null) {
			throw new DataNotFoundException("Category with id "+ categoryId + " not found");
		}
		return category;
	}
	
	/**
	 * Function to add a new category
	 * @param category category object to be added
	 * @return Added category object (or exception if there was an error)
	 */
	public ProductCategory addCategory(ProductCategory newCategory) {
		if (savedProductCategories == null) {
			throw new DataNotFoundException("Problem adding category "+ newCategory);
		}
		
		savedProductCategories.add(newCategory);
		return newCategory;
	}
	
	/**
	 * Replace/edit a category
	 * @param cateogryId id to be replaced
	 * @param newCateogry category that replaces old category
	 * @return replaced category, throws exception if not found (or if there was an error)
	 */
	public ProductCategory replaceCategory(String categoryId, ProductCategory newCategory) {
		ProductCategory category = this.getCategory(categoryId);
		
		if (category == null) {
			throw new HandlingException("Category with id "+ categoryId + " could not be replaced");
		}
		
			category.setName(newCategory.getName());
			category.setProducts(newCategory.getProducts());
			return category;
	}
	
	/**
	 * Function to remove product categories
	 * @param categoryId id to be removed
	 * @return  true if found and succeeded
	 */
	public boolean removeCategory(String categoryId) {
		if (savedProductCategories == null) {
			throw new HandlingException("Category with id "+ categoryId + " not removed");
		}
		return savedProductCategories.removeIf(category -> category.getId().equals(categoryId));
	}

	
	
	/** Get all products in a category of specified id.
	 * @param categoryId id of the category whose products will be returned.
	 * @param maker search string for n
	 * @return List of products in the category.
	 */
	public List<Product> getProducts(String categoryId, String manufacturer) {
		ProductCategory category = this.getCategory(categoryId);
		String manufacturerRegex;
		if (manufacturer != null) {
			manufacturerRegex = ".*" + manufacturer + ".*";
		} else {
			manufacturerRegex = ".*";
		}
		
		List<Product> products = category.getProducts().stream()
				.filter(product -> product.getManufacturer().matches(manufacturerRegex))
				.collect(Collectors.toList());
		return products;
	}
	
	/** Finds product with given id.
	 * @param productId 
	 * @return Product with given id or null if not found
	 */
	public Product getProduct(String categoryId, String productId) {
		ProductCategory category = this.getCategory(categoryId);
		
		Product product = category.getProducts().stream()
				.filter(prod -> productId.equals(prod.getId()))
				.findFirst()
				.orElse(null);
		
		if (product == null) {
			throw new DataNotFoundException("Product with id "+ productId + " not found");
		}
		
		return product;
	}
	
	
	/** Adds a product to a specified category.
	 * @param categoryId
	 * @param product
	 * @return Added product
	 */
	public Product addProduct(String categoryId, Product product) {
		ProductCategory category = this.getCategory(categoryId);
		return category.addProduct(product);
	}

	public Product replaceProduct(String categoryId, String productId, Product product) {
		ProductCategory category = this.getCategory(categoryId);
		Product productToBeUpdated = category.getProduct(productId);
		return productToBeUpdated.update(product);
	}
	
	/** Delete a product from a category with corresponding ids
	 * @param categoryId
	 * @param productId
	 * @return was a product successfully deleted
	 */
	public boolean removeProduct(String categoryId, String productId) {
		ProductCategory category = this.getCategory(categoryId);
		return category.getProducts().removeIf(product -> productId.equals(product.getId()));
	}
	
	/*
	 */
	//Nopea testaus:
	public static void main(String[] args) {
		SingletonDatabase db = SingletonDatabase.getDatabase();
		
		
		List<Customer> testiA = SingletonDatabase.getDatabase().getCustomers();
		List<ProductCategory> testiB = SingletonDatabase.getDatabase().getProductCategories();
		}


}
