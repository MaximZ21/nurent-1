import com.google.gson.Gson;



import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Path("/signup")
public class SignupServlet {

    @Context
    private ServletContext context;

    private Request request = new Request();

    @GET
    @Produces({MediaType.TEXT_HTML})
    public InputStream getSignupPage() {
        return context.getResourceAsStream("signup.html");
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signUp(AccountSignup account) {
        System.out.println("signup");
        String username = account.getUsername();
        String password = account.getPassword();
        String firstName = account.getName();
        String lastName = account.getSurname();
        String phone = account.getPhone();
        String email = account.getEmail();



        //TODO: Validate the given sign up inforamtion
        try {
            request.addNewUser(username, password, firstName, lastName, phone,email);
            String token = request.generateToken(username);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
