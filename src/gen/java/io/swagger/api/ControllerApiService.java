package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.PostType;
import io.swagger.model.Post;
import io.swagger.model.Product;
import java.io.File;
import io.swagger.model.Page;
import io.swagger.model.PublicKey;
import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-12T14:39:30.259Z")
public abstract class ControllerApiService {
    public abstract Response addNoteToSupplier(String supplierName,String editor,byte[] text,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createPost(String owner,String title,String description,PostType type,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createProduct(String productName,String chemicalName,Double molWeight,String description,Double price,String packaging,String deliveryTime,String producer,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deletePost(Post post,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteProduct(Product product,SecurityContext securityContext) throws NotFoundException;
    public abstract Response editPost(Post post,String newDescription,String newTitle,SecurityContext securityContext) throws NotFoundException;
    public abstract Response editProduct(Product product,String newProductName,String newChemicalName,Double newMolWeight,String newDescription,Double newPrice,String newPackaging,String newDeliveryTime,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getAllPosts(SecurityContext securityContext) throws NotFoundException;
    public abstract Response getPDF(Integer productId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getSuppliers(SecurityContext securityContext) throws NotFoundException;
    public abstract Response requestPublicKey(SecurityContext securityContext) throws NotFoundException;
    public abstract Response requestUpdate(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updatePage(String page,String description,String location,String contactInformation,SecurityContext securityContext) throws NotFoundException;
    public abstract Response validate(String username,String password,SecurityContext securityContext) throws NotFoundException;
}
