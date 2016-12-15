package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.PageManagerApiService;
import io.swagger.api.factories.PageManagerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Product;
import io.swagger.model.Page;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/PageManager")


@io.swagger.annotations.Api(description = "the PageManager API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-15T10:29:50.305Z")
public class PageManagerApi  {
   private final PageManagerApiService delegate = PageManagerApiServiceFactory.getPageManagerApi();

    @POST
    @Path("/AddNoteToSupplier")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Adds a note to a supplier", notes = "", response = void.class, tags={ "PageManager", })
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
    @Path("/CreateProduct")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Creates a product.", notes = "", response = Product.class, tags={ "PageManager", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Product.class) })
    public Response createProduct(@ApiParam(value = "",required=true) @QueryParam("ProductName") String productName
,@ApiParam(value = "",required=true) @QueryParam("ChemicalName") String chemicalName
,@ApiParam(value = "",required=true) @QueryParam("MolWeight") Double molWeight
,@ApiParam(value = "",required=true) @QueryParam("Description") String description
,@ApiParam(value = "",required=true) @QueryParam("Price") Double price
,@ApiParam(value = "",required=true) @QueryParam("Packaging") String packaging
,@ApiParam(value = "",required=true) @QueryParam("DeliveryTime") String deliveryTime
,@ApiParam(value = "",required=true) @QueryParam("Producer") String producer
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createProduct(productName,chemicalName,molWeight,description,price,packaging,deliveryTime,producer,securityContext);
    }
    @POST
    @Path("/DeleteProduct")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Deletes an existing product.", notes = "", response = void.class, tags={ "PageManager", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response deleteProduct(@ApiParam(value = "" ,required=true) Product product
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteProduct(product,securityContext);
    }
    @POST
    @Path("/EditProduct")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Edits an existing product.", notes = "", response = void.class, tags={ "PageManager", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response editProduct(@ApiParam(value = "" ,required=true) Product product
,@ApiParam(value = "",required=true) @QueryParam("newProductName") String newProductName
,@ApiParam(value = "",required=true) @QueryParam("newChemicalName") String newChemicalName
,@ApiParam(value = "",required=true) @QueryParam("newMolWeight") Double newMolWeight
,@ApiParam(value = "",required=true) @QueryParam("newDescription") String newDescription
,@ApiParam(value = "",required=true) @QueryParam("newPrice") Double newPrice
,@ApiParam(value = "",required=true) @QueryParam("newPackaging") String newPackaging
,@ApiParam(value = "",required=true) @QueryParam("newDeliveryTime") String newDeliveryTime
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.editProduct(product,newProductName,newChemicalName,newMolWeight,newDescription,newPrice,newPackaging,newDeliveryTime,securityContext);
    }
    @POST
    @Path("/GetSuppliers")
    
    @Produces({ "text/plain", "application/json", "text/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets all suppliers", notes = "", response = Page.class, responseContainer = "List", tags={ "PageManager", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Page.class, responseContainer = "List") })
    public Response getSuppliers(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getSuppliers(securityContext);
    }
    @POST
    @Path("/UpdatePage")
    @Consumes({ "text/plain", "application/json", "text/json" })
    
    @io.swagger.annotations.ApiOperation(value = "Update a supplier page", notes = "", response = void.class, tags={ "PageManager", })
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
}
