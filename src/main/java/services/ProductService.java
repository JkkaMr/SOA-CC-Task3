package services;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import types.Product;
import types.ShopData;

/**
 * Class for product service
 *KESKEN KESKEN KESKEN
 *TODO:
 */
public class ProductService {
	private static List<Product> products;
	
	public ProductService () {
		
	}
	/* ???
	public ProductService (String id) {
		if (products == null) {
			try {
				// We use easy-to-use Jackson library for parsing shopdata.json straight to Java types 
				ObjectMapper mapper = new ObjectMapper();
				InputStream is = ShopData.class.getResourceAsStream("/shopdata.json");
				
				ShopData shopData = mapper.readValue(is, ShopData.class);
				
				
				//TODO mitä tähän?
				// For nested orders it would be like orders = shopData.getCustomers().get(customerId).getOrders();
				products = shopData.getProductCategories().get(Integer.parseInt(id)).getProducts();
			} catch (Exception e) {
				e.printStackTrace();
				products = null;
			}
		}
	}
	*/
	
	/**
	 * TODO: Tämä pitää varmaan muuttaa kunhan selviää miten nested funktio toteutetaan
	 * @param productId id to find
	 * @return Product with productId, null if not found (or if there was an error)
	 */
	public Product getProduct(String categoryId, String productId) {
		if (products != null) {
			return products.stream().filter(product -> product.getId().equals(productId)).findFirst().orElse(null);
		}
		return null;
	}
}
