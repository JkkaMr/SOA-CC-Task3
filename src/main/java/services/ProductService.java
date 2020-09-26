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
