package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ControllerApiService;
import io.swagger.api.factories.ControllerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import java.io.File;
import io.swagger.model.PublicKey;

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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:23:21.947Z")
public class ControllerApi  {
   private final ControllerApiService delegate = ControllerApiServiceFactory.getControllerApi();

    @POST
    @Path("/GetPDF")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    @Produces({ "text/plain", "application/json", "text/json", "multipart/form-data" })
    @io.swagger.annotations.ApiOperation(value = "Get the specific PDF for the product", notes = "", response = File.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = File.class) })
    public Response getPDF(@ApiParam(value = "",required=true) @QueryParam("productId") Integer productId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getPDF(productId,securityContext);
    }
    @POST
    @Path("/RequestPublicKey")
    
    @Produces({ "application/json", "text/json", "application/json-patch+json" })
    @io.swagger.annotations.ApiOperation(value = "Send the public key to a client", notes = "", response = PublicKey.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = PublicKey.class) })
    public Response requestPublicKey(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.requestPublicKey(securityContext);
    }
    @POST
    @Path("/RequestUpdate")
    
    @Produces({ "application/json", "text/json", "application/json-patch+json" })
    @io.swagger.annotations.ApiOperation(value = "Send a request to the server to get any updates", notes = "", response = Boolean.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Boolean.class) })
    public Response requestUpdate(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.requestUpdate(securityContext);
    }
}
