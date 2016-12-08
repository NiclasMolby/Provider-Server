package io.swagger.api.impl;

import domain.controller.Controller;
import io.swagger.api.*;
import io.swagger.model.*;

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
    public Response requestUpdate(SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().requestUpdate()).build();
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
    public Response editProduct(Product product, String newProductName, String newChemicalName, 
            Double newMolWeight, String newDescription, Double newPrice, String newPackaging, String newDeliveryTime, SecurityContext securityContext) throws NotFoundException {
        Controller.getController().editProduct(product, newProductName, newChemicalName, newMolWeight, newDescription, newPrice, newPackaging, newDeliveryTime);
        return Response.ok().build();
    }
   
    @Override
    public Response getPDF(Integer productId, SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().getPDF(productId)).build();
    }

    @Override
    public Response createProduct(String productName, String chemicalName, Double molWeight,
            String description, Double price, String packaging, String deliveryTime, String producer, SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().createProduct(productName, chemicalName, molWeight, description, price, packaging, deliveryTime, producer)).build();
    }
}
