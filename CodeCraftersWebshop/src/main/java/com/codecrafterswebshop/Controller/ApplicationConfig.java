package com.codecrafterswebshop.Controller;

import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import java.util.HashSet;

/**
 *
 * @author tothm23
 */
@ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.codecrafterswebshop.Config.CorsFilter.class);
        resources.add(com.codecrafterswebshop.Controller.BasketResource.class);
        resources.add(com.codecrafterswebshop.Controller.GameResource.class);
        resources.add(com.codecrafterswebshop.Controller.MainPageResource.class);
        resources.add(com.codecrafterswebshop.Controller.OrderResource.class);
        resources.add(com.codecrafterswebshop.Controller.ProductsResource.class);
        resources.add(com.codecrafterswebshop.Controller.UserResource.class);
    }

}
