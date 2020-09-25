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

import services.ProductCategoryService;
import types.ProductCategory;

	/**
	 * Root resource (exposed at "customers" path)
	 */
	@Path("/productcategories")
	@Produces(MediaType.APPLICATION_JSON)
	public class ProductCategoryResource {
		ProductCategoryService prodCatService = new ProductCategoryService();
		
	    @GET
	    public Response getProductCategories() {
	    	List<ProductCategory> productCategories = prodCatService.getProductCategories();
	    	if (productCategories != null) {
	    		return Response.status(Status.OK).entity(productCategories).build();
	    	}
	    	return Response.status(Status.INTERNAL_SERVER_ERROR).entity("[]").build();
	    }
	    
	    @GET
	    @Path("/{categoryId}")
	    public Response getCategory(@PathParam("categoryId") String categoryId) {
	    	ProductCategory category = prodCatService.getCategory(categoryId);
	    	if (category != null) {
				return Response.status(Status.OK).entity(category).build();
	    	}
	    	// if customer == null, customer with id was not found (or data reading from shopdata.json has failed)
	    	return Response.status(Status.BAD_REQUEST).entity("{}").build();
	    }
	    
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response addCategory(ProductCategory category) {
	    	ProductCategory addedCategory = prodCatService.addCategory(category);
	    	if (addedCategory != null) {
	    		return Response.status(Status.OK).entity(addedCategory).build();
	    	}
	    	return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{}").build();
	    }
	    
	    @PUT
	    @Path("/{categoryId}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response replaceCategory(@PathParam("categoryId") String categoryId, ProductCategory category) {
	    	ProductCategory replacedCategory = prodCatService.replaceCategory(categoryId, category);
	    	if (replacedCategory != null) {
				return Response.status(Status.OK).entity(replacedCategory).build();
	    	}
	    	return Response.status(Status.BAD_REQUEST).entity("{}").build();
	    }
	    
	    @DELETE
	    @Path("/{categoryId}")
	    public Response removeCategory(@PathParam("customerId") String categoryId) {
	    	if (prodCatService.removeCategory(categoryId)) {
				return Response.status(Status.OK).entity("{}").build();
	    	}
	    	return Response.status(Status.BAD_REQUEST).entity("{}").build();
	    }
	    
	    //PRODUCT SERVICE
	    @Path("/{categoryId}/products")
	    public ProductResource getProductResource() {
	    	return new ProductResource();
	    }
}
