package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ControllerApiService;
import io.swagger.api.factories.ControllerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.PostType;
import io.swagger.model.Post;
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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-30T11:56:05.841Z")
public class ControllerApi  {
   private final ControllerApiService delegate = ControllerApiServiceFactory.getControllerApi();

    @POST
    @Path("/AddNoteToSupplier")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Adds a note to a supplier", notes = "", response = void.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response addNoteToSupplier(@ApiParam(value = "",required=true) @QueryParam("supplierName") String supplierName
,@ApiParam(value = "",required=true) @QueryParam("editor") String editor
,@ApiParam(value = "",required=true) @QueryParam("text") String text
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addNoteToSupplier(supplierName,editor,text,securityContext);
    }
    @POST
    @Path("/CreatePost")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Creates a post", notes = "", response = Post.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Post.class) })
    public Response createPost(@ApiParam(value = "",required=true) @QueryParam("owner") String owner
,@ApiParam(value = "",required=true) @QueryParam("title") String title
,@ApiParam(value = "",required=true) @QueryParam("description") String description
,@ApiParam(value = "" ,required=true) PostType type
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createPost(owner,title,description,type,securityContext);
    }
    @POST
    @Path("/DeletePost")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Deletes a post", notes = "", response = void.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response deletePost(@ApiParam(value = "" ,required=true) Post post
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deletePost(post,securityContext);
    }
    @POST
    @Path("/EditPost")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Edits a posts", notes = "", response = void.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response editPost(@ApiParam(value = "" ,required=true) Post post
,@ApiParam(value = "",required=true) @QueryParam("newDescription") String newDescription
,@ApiParam(value = "",required=true) @QueryParam("newTitle") String newTitle
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.editPost(post,newDescription,newTitle,securityContext);
    }
    @POST
    @Path("/GetAllPosts")
    
    @Produces({ "text/plain", "application/json", "text/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets all posts", notes = "", response = Post.class, responseContainer = "List", tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Post.class, responseContainer = "List") })
    public Response getAllPosts(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAllPosts(securityContext);
    }
    @POST
    @Path("/GetSuppliers")
    
    @Produces({ "text/plain", "application/json", "text/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets all suppliers", notes = "", response = Page.class, responseContainer = "List", tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Page.class, responseContainer = "List") })
    public Response getSuppliers(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getSuppliers(securityContext);
    }
    @POST
    @Path("/Validate")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    @Produces({ "text/plain", "application/json", "text/json" })
    @io.swagger.annotations.ApiOperation(value = "Validate information", notes = "", response = User.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = User.class) })
    public Response validate(@ApiParam(value = "Username to login",required=true) @QueryParam("username") String username
,@ApiParam(value = "Password to login",required=true) @QueryParam("password") String password
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.validate(username,password,securityContext);
    }
}
