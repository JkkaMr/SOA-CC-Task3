package errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class HandlingExcpetionMapper implements ExceptionMapper<HandlingException> {

	@Override
	public Response toResponse(HandlingException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),400,"http://myDocs.org");
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
	}

}
