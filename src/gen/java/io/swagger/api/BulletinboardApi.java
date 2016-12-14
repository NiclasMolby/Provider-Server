package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.BulletinboardApiService;
import io.swagger.api.factories.BulletinboardApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.PostType;
import io.swagger.model.Post;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/Bulletinboard")


@io.swagger.annotations.Api(description = "the Bulletinboard API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:23:21.947Z")
public class BulletinboardApi  {
   private final BulletinboardApiService delegate = BulletinboardApiServiceFactory.getBulletinboardApi();

    @POST
    @Path("/CreatePost")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Creates a post", notes = "", response = Post.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Post.class) })
    public Response createPost(@ApiParam(value = "",required=true) @QueryParam("owner") String owner
,@ApiParam(value = "",required=true) @QueryParam("title") String title
,@ApiParam(value = "",required=true) @QueryParam("description") String description
,@ApiParam(value = "" ,required=true) PostType type
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createPost(owner,title,description,type,securityContext);
    }
    @POST
    @Path("/DeletePost")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Deletes a post", notes = "", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response deletePost(@ApiParam(value = "" ,required=true) Post post
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deletePost(post,securityContext);
    }
    @POST
    @Path("/EditPost")
    @Consumes({ "application/json", "text/json", "application/json-patch+json" })
    
    @io.swagger.annotations.ApiOperation(value = "Edits a posts", notes = "", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = void.class) })
    public Response editPost(@ApiParam(value = "" ,required=true) Post post
,@ApiParam(value = "",required=true) @QueryParam("newDescription") String newDescription
,@ApiParam(value = "",required=true) @QueryParam("newTitle") String newTitle
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.editPost(post,newDescription,newTitle,securityContext);
    }
    @POST
    @Path("/GetAllPosts")
    
    @Produces({ "text/plain", "application/json", "text/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets all posts", notes = "", response = Post.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "ok", response = Post.class, responseContainer = "List") })
    public Response getAllPosts(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAllPosts(securityContext);
    }
}
