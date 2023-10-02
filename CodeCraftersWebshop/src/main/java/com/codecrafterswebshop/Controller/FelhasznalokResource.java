package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Felhasznalo;
import com.codecrafterswebshop.Service.FelhasznaloService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("felhasznalok")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FelhasznalokResource {

    public FelhasznalokResource() {
    }

    @GET
    public List<Felhasznalo> felhasznalok() {
        List<Felhasznalo> felhasznalok = FelhasznaloService.felhasznalok();
        return felhasznalok;
    }

}
