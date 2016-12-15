package io.swagger.api.impl;

import domain.controller.Controller;
import domain.page.PageManager;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Product;
import io.swagger.model.Page;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:16:30.225Z")
public class PageManagerApiServiceImpl extends PageManagerApiService {
    @Override
    public Response addNoteToSupplier(String supplierName, String editor, String text, SecurityContext securityContext) throws NotFoundException {
        PageManager.getPageManager().addNoteToSupplier(supplierName, editor, text);
        return Response.ok().build();
    }
    @Override
    public Response createProduct(String productName, String chemicalName, Double molWeight,
                                  String description, Double price, String packaging, String deliveryTime, String producer, SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(PageManager.getPageManager().createProduct(productName, chemicalName, molWeight, description, price, packaging, deliveryTime, producer)).build();
    }
    @Override
    public Response deleteProduct(Product product, SecurityContext securityContext) throws NotFoundException {
        PageManager.getPageManager().deleteProduct(product);
        return Response.ok().build();
    }
    @Override
    public Response editProduct(Product product, String newProductName, String newChemicalName,
                                Double newMolWeight, String newDescription, Double newPrice, String newPackaging, String newDeliveryTime, SecurityContext securityContext) throws NotFoundException {
        PageManager.getPageManager().editProduct(product, newProductName, newChemicalName, newMolWeight, newDescription, newPrice, newPackaging, newDeliveryTime);
        return Response.ok().build();
    }
    @Override
    public Response getSuppliers(SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(PageManager.getPageManager().getSuppliers()).build();
    }
    @Override
    public Response updatePage(String page, String description, String location, String contactInformation, SecurityContext securityContext) throws NotFoundException {
        PageManager.getPageManager().updatePage(page, description, location, contactInformation);
        return Response.ok().build();
    }
}
