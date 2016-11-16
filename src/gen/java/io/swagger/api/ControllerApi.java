package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ControllerApiService;
import io.swagger.api.factories.ControllerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Page;
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

@Path("/Controller")


@io.swagger.annotations.Api(description = "the Controller API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-16T17:01:32.921Z")
public class ControllerApi  {
   private final ControllerApiService delegate = ControllerApiServiceFactory.getControllerApi();

    @POST
    @Path("/GetSuppliers")
    
    @Produces({ "text/plain", "application/json", "text/json" })
    @io.swagger.annotations.ApiOperation(value = "Henter suppliers", notes = "", response = Page.class, responseContainer = "List", tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Page.class, responseContainer = "List") })
    public Response getSupplier(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getSupplier(securityContext);
    }
    @POST
    @Path("/Validate")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    @Produces({ "text/plain", "application/json", "text/json" })
    @io.swagger.annotations.ApiOperation(value = "Validate information", notes = "Validate the users information", response = User.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = User.class) })
    public Response validate(@ApiParam(value = "Username to login",required=true) @QueryParam("username") String username
,@ApiParam(value = "Password to login",required=true) @QueryParam("password") String password
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.validate(username,password,securityContext);
    }
}
