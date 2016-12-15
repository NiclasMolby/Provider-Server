package io.swagger.api.factories;

import io.swagger.api.PageManagerApiService;
import io.swagger.api.impl.PageManagerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-15T10:29:50.305Z")
public class PageManagerApiServiceFactory {
    private final static PageManagerApiService service = new PageManagerApiServiceImpl();

    public static PageManagerApiService getPageManagerApi() {
        return service;
    }
}
