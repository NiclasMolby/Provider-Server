package io.swagger.api.impl;

import domain.controller.Controller;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.PostType;
import io.swagger.model.Post;
import io.swagger.model.Page;
import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-22T16:22:57.556Z")
public class ControllerApiServiceImpl extends ControllerApiService {
    @Override
    public Response addNoteToSupplier(String supplierName, String editor, String text, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response createPost(String owner, String title, String description, PostType type, SecurityContext securityContext) throws NotFoundException {
        Controller.getController().CreatePost(owner, title, description, type);
        return Response.ok().build();
    }
    @Override
    public Response editPost(Post post, String newDescription, String newTitle, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getSupplier(SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response validate(String username, String password, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
