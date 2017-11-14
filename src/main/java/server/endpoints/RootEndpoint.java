package server.endpoints;

import com.google.gson.Gson;
import server.models.User;
import server.providers.UserProvider;
import server.util.Auth;
import server.util.DBManager;
import server.util.Log;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/")
public class RootEndpoint {

    Log log = new Log();

    @GET
    public Response defaultGetMethod(){

        log.writeLog(this.getClass().getName(),this.getClass(),("defaultGetMethod was successful - " +
                "User active was: " + AuthenticationFilter.userEmailByToken),0);

        return Response.status(200).type("text/plain").entity("Welcome to our API").build();

    }
}
