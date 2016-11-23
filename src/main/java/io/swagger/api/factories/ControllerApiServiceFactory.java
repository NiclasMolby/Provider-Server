package io.swagger.api.factories;

import io.swagger.api.ControllerApiService;
import io.swagger.api.impl.ControllerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-23T18:03:00.493Z")
public class ControllerApiServiceFactory {
    private final static ControllerApiService service = new ControllerApiServiceImpl();

    public static ControllerApiService getControllerApi() {
        return service;
    }
}
