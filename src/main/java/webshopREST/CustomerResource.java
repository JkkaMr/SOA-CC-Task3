package webshopREST;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import services.CustomerService;
import types.Customer;

/**
 * Root resource (exposed at "customers" path)
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
	CustomerService cs = new CustomerService();

    @GET
    public Response getCustomers() {
    	List<Customer> customers = cs.getCustomers();
    	if (customers != null) {
    		return Response.status(Status.OK).entity(customers).build();
    	}
    	// if customers == null, the data reading from shopdata.json has failed
    	return Response.status(Status.INTERNAL_SERVER_ERROR).entity("[]").build();
    }
    
    @GET
    @Path("/{customerId}")
    public Response getCustomer(@PathParam("customerId") String customerId) {
    	Customer customer = cs.getCustomer(customerId);
    	if (customer != null) {
			return Response.status(Status.OK).entity(customer).build();
    	}
    	// if customer == null, customer with id was not found (or data reading from shopdata.json has failed)
    	return Response.status(Status.BAD_REQUEST).entity("{}").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) {
    	Customer addedCustomer = cs.addCustomer(customer);
    	if (addedCustomer != null) {
    		return Response.status(Status.CREATED).entity(addedCustomer).build();
    	}
    	// if customer == null, the data reading from shopdata.json has failed
    	return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{}").build();
    }
    
    @PUT
    @Path("/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response replaceCustomer(@PathParam("customerId") String customerId, Customer customer) {
    	Customer replacedCustomer = cs.replaceCustomer(customerId, customer);
    	if (replacedCustomer != null) {
			return Response.status(Status.OK).entity(replacedCustomer).build();
    	}
    	// if customer == null, customer with id was not found (or data reading from shopdata.json has failed)
    	return Response.status(Status.BAD_REQUEST).entity("{}").build();
    }
    
    @DELETE
    @Path("/{customerId}")
    public Response removeCustomer(@PathParam("customerId") String customerId) {
    	if (cs.removeCustomer(customerId)) {
			return Response.status(Status.OK).entity("{}").build();
    	}
    	// if cs.removeCustomer() == false, customer with id was not found (or data reading from shopdata.json has failed)
    	return Response.status(Status.BAD_REQUEST).entity("{}").build();
    }
}
