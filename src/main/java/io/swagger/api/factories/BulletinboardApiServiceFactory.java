package io.swagger.api.factories;

import io.swagger.api.BulletinboardApiService;
import io.swagger.api.impl.BulletinboardApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-14T14:23:21.947Z")
public class BulletinboardApiServiceFactory {
    private final static BulletinboardApiService service = new BulletinboardApiServiceImpl();

    public static BulletinboardApiService getBulletinboardApi() {
        return service;
    }
}
