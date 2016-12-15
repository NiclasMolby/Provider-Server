package io.swagger.api.impl;

import domain.controller.Controller;
import io.swagger.api.*;
import io.swagger.model.*;

import java.io.File;
import io.swagger.model.PublicKey;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:16:30.225Z")
public class ControllerApiServiceImpl extends ControllerApiService {
    @Override
    public Response getPDF(Integer productId, SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().getPDF(productId)).build();
    }
    @Override
    public Response requestPublicKey(SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().getPublicKey()).build();
    }

    @Override
    public Response requestUpdate(SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Controller.getController().requestUpdate()).build();
    }
}
