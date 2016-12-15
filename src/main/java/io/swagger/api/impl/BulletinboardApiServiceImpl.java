package io.swagger.api.impl;

import domain.bulletinboard.Bulletinboard;
import domain.controller.Controller;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.PostType;
import io.swagger.model.Post;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:16:30.225Z")
public class BulletinboardApiServiceImpl extends BulletinboardApiService {
    @Override
    public Response createPost(String owner, String title, String description, PostType type, SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Bulletinboard.getBulletinboard().createPost(owner, title, description, type)).build();
    }
    @Override
    public Response deletePost(Post post, SecurityContext securityContext) throws NotFoundException {
        Bulletinboard.getBulletinboard().deletePost(post);
        return Response.ok().build();
    }
    @Override
    public Response editPost(Post post, String newDescription, String newTitle, SecurityContext securityContext) throws NotFoundException {
        Bulletinboard.getBulletinboard().editPost(post, newDescription, newTitle);
        return Response.ok().build();
    }
    @Override
    public Response getAllPosts(SecurityContext securityContext) throws NotFoundException {
        return Response.ok().entity(Bulletinboard.getBulletinboard().getAllPosts()).build();
    }
}
