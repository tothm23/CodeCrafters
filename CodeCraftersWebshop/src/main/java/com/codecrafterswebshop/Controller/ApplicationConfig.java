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
        resources.add(com.codecrafterswebshop.Config.TokenFilter.class);
        resources.add(com.codecrafterswebshop.Controller.FelhasznaloResource.class);
        resources.add(com.codecrafterswebshop.Controller.FooldalResource.class);
        resources.add(com.codecrafterswebshop.Controller.JatekResource.class);
        resources.add(com.codecrafterswebshop.Controller.KosarResource.class);
        resources.add(com.codecrafterswebshop.Controller.RendelesResource.class);
        resources.add(com.codecrafterswebshop.Controller.TermekekResource.class);
    }

}
