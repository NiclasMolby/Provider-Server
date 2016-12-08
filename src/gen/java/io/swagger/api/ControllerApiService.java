package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-07T10:59:42.816Z")
public abstract class ControllerApiService {
    public abstract Response addNoteToSupplier(String supplierName,String editor,String text,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createPost(String owner,String title,String description,PostType type,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createProduct(String productName,String chemicalName,Double molWeight,String description,Double price,String packaging,String deliveryTime,String producer,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deletePost(Post post,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteProduct(Product product,SecurityContext securityContext) throws NotFoundException;
    public abstract Response editPost(Post post,String newDescription,String newTitle,SecurityContext securityContext) throws NotFoundException;
    public abstract Response editProduct(Product product,String newProductName,String newChemicalName,Double newMolWeight,String newDescription,Double newPrice,String newPackaging,String newDeliveryTime,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getAllPosts(SecurityContext securityContext) throws NotFoundException;
    public abstract Response getPDF(Integer productId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getSuppliers(SecurityContext securityContext) throws NotFoundException;
    public abstract Response requestUpdate(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updatePage(String page,String description,String location,String contactInformation,SecurityContext securityContext) throws NotFoundException;
    public abstract Response validate(String username,String password,SecurityContext securityContext) throws NotFoundException;
}
