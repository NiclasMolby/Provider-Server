package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.UsermanagerApiService;
import io.swagger.api.factories.UsermanagerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/Usermanager")


@io.swagger.annotations.Api(description = "the Usermanager API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:23:21.947Z")
public class UsermanagerApi  {
   private final UsermanagerApiService delegate = UsermanagerApiServiceFactory.getUsermanagerApi();

    @POST
    @Path("/Validate")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    @Produces({ "text/plain", "application/json", "text/json" })
    @io.swagger.annotations.ApiOperation(value = "Validate information", notes = "", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = User.class) })
    public Response validate(@ApiParam(value = "Username to login",required=true) @QueryParam("username") String username
,@ApiParam(value = "Password to login",required=true) @QueryParam("password") String password
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.validate(username,password,securityContext);
    }
}
