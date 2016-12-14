package io.swagger.api.factories;

import io.swagger.api.PagemanagerApiService;
import io.swagger.api.impl.PagemanagerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:23:21.947Z")
public class PagemanagerApiServiceFactory {
    private final static PagemanagerApiService service = new PagemanagerApiServiceImpl();

    public static PagemanagerApiService getPagemanagerApi() {
        return service;
    }
}
