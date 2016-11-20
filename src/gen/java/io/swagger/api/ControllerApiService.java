package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Page;
import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-20T00:54:43.083Z")
public abstract class ControllerApiService {
    public abstract Response addNoteToSupplier(String supplierName,String text,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getSupplier(SecurityContext securityContext) throws NotFoundException;
    public abstract Response validate(String username,String password,SecurityContext securityContext) throws NotFoundException;
}
