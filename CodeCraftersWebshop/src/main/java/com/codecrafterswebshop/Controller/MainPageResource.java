package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.MainPageService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("mainPage")
@Produces(MediaType.APPLICATION_JSON)
public class MainPageResource {

    @Context
    private UriInfo uriInfo;
    private Logger logger;
    private String time;

    public MainPageResource() {
        this.logger = LogManager.getLogger(GameResource.class.getName());
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @GET
    public Response mainPage() {
        JSONArray result = MainPageService.mainPage();
        Response response = Response.status(Response.Status.OK).entity(result.toString())
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] GET " + uriInfo.getPath());
        return response;
    }

}
