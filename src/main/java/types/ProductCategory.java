package types;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import errors.DataNotFoundException;

@XmlRootElement
public class ProductCategory {
	// base value for newly created ids
	private static int count = 1000;
	
	private String id = count++ + "";
	private String name;
	private List<Product> products;
	
	public ProductCategory() {}

	public ProductCategory(String id, String name, List<Product> products) {
		this.name = name;
		this.products = products;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/** Adds a product to the list of products
	 * @param product to be added
	 * @return added product
	 */
	public Product addProduct(Product product) {
		this.products.add(product);
		return product;
	}

	/** Finds product with corresponding id
	 * @param productId
	 * @return found product
	 */
	public Product getProduct(String productId) {
		Product found = this.products.stream().filter(prod -> prod.getId().equals(productId))
		.findFirst().orElse(null);
		
		if (found == null) {
			throw new DataNotFoundException("Product with id " + productId + "not found.");
		}
		
		return found;
	}	
	
}
