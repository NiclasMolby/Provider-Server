package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.File;
import io.swagger.model.PublicKey;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-15T10:29:50.305Z")
public abstract class ControllerApiService {
    public abstract Response getPDF(Integer productId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response requestPublicKey(SecurityContext securityContext) throws NotFoundException;
    public abstract Response requestUpdate(SecurityContext securityContext) throws NotFoundException;
}
