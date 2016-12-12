package io.swagger.api.factories;

import io.swagger.api.ControllerApiService;
import io.swagger.api.impl.ControllerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-12T13:53:13.813Z")
public class ControllerApiServiceFactory {
    private final static ControllerApiService service = new ControllerApiServiceImpl();

    public static ControllerApiService getControllerApi() {
        return service;
    }
}
