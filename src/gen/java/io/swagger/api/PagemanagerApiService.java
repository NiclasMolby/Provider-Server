package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Product;
import io.swagger.model.Page;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:23:21.947Z")
public abstract class PagemanagerApiService {
    public abstract Response addNoteToSupplier(String supplierName,String editor,String text,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createProduct(String productName,String chemicalName,Double molWeight,String description,Double price,String packaging,String deliveryTime,String producer,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteProduct(Product product,SecurityContext securityContext) throws NotFoundException;
    public abstract Response editProduct(Product product,String newProductName,String newChemicalName,Double newMolWeight,String newDescription,Double newPrice,String newPackaging,String newDeliveryTime,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getSuppliers(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updatePage(String page,String description,String location,String contactInformation,SecurityContext securityContext) throws NotFoundException;
}
