package io.swagger.api.factories;

import io.swagger.api.UserManagerApiService;
import io.swagger.api.impl.UserManagerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-15T10:29:50.305Z")
public class UserManagerApiServiceFactory {
    private final static UserManagerApiService service = new UserManagerApiServiceImpl();

    public static UserManagerApiService getUserManagerApi() {
        return service;
    }
}
