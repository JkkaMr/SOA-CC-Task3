package webshopREST;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import services.ProductService;
import types.Product;

/**
 * SAMOIN KUIN PRODUCTSERVICE, TOTEUTUS KUNHAN TIEDETÄÄN MITEN TEHDÄ
 *TODO:
 */

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
	private ProductService prodService = new ProductService();
	
	@GET
	public List<Product> getProducts(@PathParam("productCategoryId") String productCategoryId) {
		return null;
	}
	
}
