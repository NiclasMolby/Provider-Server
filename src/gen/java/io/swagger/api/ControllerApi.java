package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ControllerApiService;
import io.swagger.api.factories.ControllerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.PostType;
import io.swagger.model.Post;
import io.swagger.model.Product;
import java.io.File;
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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-05T13:07:01.183Z")
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
    @Path("/CreateProduct")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Creates a product.", notes = "", response = Product.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Product.class) })
    public Response createProduct(@ApiParam(value = "",required=true) @QueryParam("ProductName") String productName
,@ApiParam(value = "",required=true) @QueryParam("ChemicalName") String chemicalName
,@ApiParam(value = "",required=true) @QueryParam("MolWeight") String molWeight
,@ApiParam(value = "",required=true) @QueryParam("Description") String description
,@ApiParam(value = "",required=true) @QueryParam("Price") String price
,@ApiParam(value = "",required=true) @QueryParam("Packaging") String packaging
,@ApiParam(value = "",required=true) @QueryParam("DeliveryTime") String deliveryTime
,@ApiParam(value = "",required=true) @QueryParam("Producer") String producer
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createProduct(productName,chemicalName,molWeight,description,price,packaging,deliveryTime,producer,securityContext);
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
    @Path("/DeleteProduct")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Deletes an existing product.", notes = "", response = void.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response deleteProduct(@ApiParam(value = "" ,required=true) Product product
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteProduct(product,securityContext);
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
    @Path("/EditProduct")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Edits an existing product.", notes = "", response = void.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response editProduct(@ApiParam(value = "" ,required=true) Product product
,@ApiParam(value = "",required=true) @QueryParam("newProductName") String newProductName
,@ApiParam(value = "",required=true) @QueryParam("newChemicalName") String newChemicalName
,@ApiParam(value = "",required=true) @QueryParam("newMolWeight") String newMolWeight
,@ApiParam(value = "",required=true) @QueryParam("newDescription") String newDescription
,@ApiParam(value = "",required=true) @QueryParam("newPrice") String newPrice
,@ApiParam(value = "",required=true) @QueryParam("newPackaging") String newPackaging
,@ApiParam(value = "",required=true) @QueryParam("newDeliveryTime") String newDeliveryTime
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.editProduct(product,newProductName,newChemicalName,newMolWeight,newDescription,newPrice,newPackaging,newDeliveryTime,securityContext);
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
    @Path("/GetPDF")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    @Produces({ "text/plain", "application/json", "text/json", "multipart/form-data" })
    @io.swagger.annotations.ApiOperation(value = "Get the specific PDF for the product", notes = "", response = File.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = File.class) })
    public Response getPDF(@ApiParam(value = "",required=true) @QueryParam("productId") Integer productId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getPDF(productId,securityContext);
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
    @Path("/UpdatePage")
    @Consumes({ "text/plain", "application/json", "text/json" })
    
    @io.swagger.annotations.ApiOperation(value = "Update a supplier page", notes = "", response = void.class, tags={ "Controller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response updatePage(@ApiParam(value = "",required=true) @QueryParam("page") String page
,@ApiParam(value = "",required=true) @QueryParam("description") String description
,@ApiParam(value = "",required=true) @QueryParam("location") String location
,@ApiParam(value = "",required=true) @QueryParam("contactInformation") String contactInformation
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updatePage(page,description,location,contactInformation,securityContext);
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
