package io.openliberty.guides.inventory;

import java.util.Properties;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.openliberty.guides.inventory.model.InventoryList;

@Path("/systems")
public class InventoryResource {
	
	@Inject
	private InventoryManager manager;
    
	 @GET
	  @Path("/{hostname}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getPropertiesForHost(@PathParam("hostname") String hostname) {
	    Properties props = manager.get(hostname);
	    if (props == null) {
	      return Response.status(Response.Status.NOT_FOUND)
	                     .entity("ERROR: Unknown hostname or the system service may not be running on "
	                         + hostname)
	                     .build();
	    }
	    return Response.ok(props).build();
	  }

	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public InventoryList listContents() {
	    return manager.list();
	  }
}
