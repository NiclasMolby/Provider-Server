package io.swagger.api.factories;

import io.swagger.api.UsermanagerApiService;
import io.swagger.api.impl.UsermanagerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-15T00:04:09.670Z")
public class UsermanagerApiServiceFactory {
    private final static UsermanagerApiService service = new UsermanagerApiServiceImpl();

    public static UsermanagerApiService getUsermanagerApi() {
        return service;
    }
}
