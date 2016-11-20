package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Page;
import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import domain.controller.Controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-17T10:47:02.780Z")
public class ControllerApiServiceImpl extends ControllerApiService {
    @Override
    public Response getSupplier(SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().getSuppliers()).build();
    }
    @Override
    public Response validate(String username, String password, SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().validate(username, password)).build();
    }
	@Override
	public Response addNoteToSupplier(String supplierName, String text, SecurityContext securityContext) {
		Controller.getController().addNoteToSupplier(supplierName, text);
		return Response.ok().build();
	}
}
