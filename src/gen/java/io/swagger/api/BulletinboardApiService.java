package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.PostType;
import io.swagger.model.Post;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:23:21.947Z")
public abstract class BulletinboardApiService {
    public abstract Response createPost(String owner,String title,String description,PostType type,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deletePost(Post post,SecurityContext securityContext) throws NotFoundException;
    public abstract Response editPost(Post post,String newDescription,String newTitle,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getAllPosts(SecurityContext securityContext) throws NotFoundException;
}
