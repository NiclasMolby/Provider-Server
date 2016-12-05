package io.swagger.api.impl;

import domain.controller.Controller;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.PostType;
import io.swagger.model.Post;
import io.swagger.model.Page;
import io.swagger.model.User;

import java.util.Date;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.naming.ldap.Control;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-02T13:49:11.596Z")
public class ControllerApiServiceImpl extends ControllerApiService {
    @Override
    public Response addNoteToSupplier(String supplierName, String editor, String text, SecurityContext securityContext) throws NotFoundException {
        Controller.getController().addNoteToSupplier(supplierName, editor, text);
        return Response.ok().build();
    }
    @Override
    public Response editPost(Post post, String newDescription, String newTitle, SecurityContext securityContext) throws NotFoundException {
        Controller.getController().editPost(post, newDescription, newTitle);
        return Response.ok().build();
    }
    @Override
    public Response getAllPosts(SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().getAllPosts()).build();
    }
    @Override
    public Response getSuppliers(SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().getSuppliers()).build();
    }

    @Override
    public Response updatePage(String page, String description, String location, String contactInformation, SecurityContext securityContext) throws NotFoundException {
        Controller.getController().updatePage(page, description, location, contactInformation);
        return Response.ok().build();
    }

    @Override
    public Response validate(String username, String password, SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().validate(username, password)).build();
    }
    @Override
    public Response createPost(String owner, String title, String description, PostType type, SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().createPost(owner, title, description, type)).build();
    }

    @Override
    public Response deletePost(Post post, SecurityContext securityContext) throws NotFoundException {
        Controller.getController().deletePost(post);
        return Response.ok().build();
    }

    @Override
    public Response deleteProduct(Product product, SecurityContext securityContext) throws NotFoundException {
        Controller.getController().deleteProduct(product);
        return Response.ok().build();
    }

    @Override
    public Response editProduct(Product product, String newProductName, String newChemicalName, String newMolWeight, String newDescription, String newPrice, String newPackaging, String newDeliveryTime, SecurityContext securityContext) throws NotFoundException {
        Controller.getController().editProduct(product, newProductName, newChemicalName, newMolWeight, newDescription, newPrice, newPackaging, newDeliveryTime);
        return Response.ok().build();
    }
   
    @Override
    public Response getPDF(Integer productId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response createProduct(String productName, String chemicalName, String molWeight, String description, String price, String packaging, String deliveryTime, String producer, SecurityContext securityContext) throws NotFoundException {
       
        return Response.ok().entity(Controller.getController().createProduct(productName, chemicalName, molWeight, description, price, packaging, deliveryTime)).build();
    }
}
