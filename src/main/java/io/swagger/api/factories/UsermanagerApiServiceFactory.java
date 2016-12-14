package io.swagger.api.factories;

import io.swagger.api.UsermanagerApiService;
import io.swagger.api.impl.UsermanagerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:23:21.947Z")
public class UsermanagerApiServiceFactory {
    private final static UsermanagerApiService service = new UsermanagerApiServiceImpl();

    public static UsermanagerApiService getUsermanagerApi() {
        return service;
    }
}
