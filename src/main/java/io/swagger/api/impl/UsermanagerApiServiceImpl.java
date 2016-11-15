package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import domain.user.Usermanager;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-15T00:04:09.670Z")
public class UsermanagerApiServiceImpl extends UsermanagerApiService {
    @Override
    public Response validate(String username, String password, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new Usermanager().validate(username, password)).build();
    }
}
