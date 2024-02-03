package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.User;
import com.codecrafterswebshop.Service.UserService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    public Response registration(User u) {
        String result = UserService.registration(u.getUserName(), u.getLastName(),
                u.getFirstName(), u.getEmail(), u.getPassword());
        return Response.status(Response.Status.CREATED).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("login")
    public Response login(User u) {
        JSONObject result = UserService.login(u.getUserName(), u.getPassword());
        return Response.status(Response.Status.OK).entity(result.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{userId}")
    public Response updateUser(User u, @PathParam("userId") Integer id) {
        JSONObject result = UserService.user(id);
        String update = UserService.updateUser(id, u.getUserName(), u.getLastName(),
                u.getFirstName(), u.getPassword());

        return result.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(update)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(update)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") Integer id) {
        JSONObject result = UserService.user(id);
        String delete = UserService.deleteUser(id);

        return result.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(delete)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(delete)
                .type(MediaType.APPLICATION_JSON).build();
    }

}
