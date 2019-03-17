import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/listings")
public class ListingsServlet {
    @Context
    private ServletContext context;

    private Request request = new Request();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getListByParameters(@QueryParam("city") String city,
                                        @QueryParam("minprice") String minprice,
                                        @QueryParam("maxprice") String maxprice,
                                        @QueryParam("min_number_of_rooms") String min_num_of_rooms,
                                        @QueryParam("max_number_of_rooms") String max_num_of_rooms
                                        ) {

        System.out.println("from ListingsServlet");
        System.out.println(city);
        System.out.println(minprice);
        System.out.println(maxprice);
        System.out.println(min_num_of_rooms);
        System.out.println(max_num_of_rooms);
        System.out.println("t1");
        List<Book> books = request.getBookssByParameters(null,null,null);
        System.out.println(books.size());

        Gson gson = new Gson();
        String json = gson.toJson(books);
        return Response.ok(json).build();
    }

    @GET
    @Path("chosenBook")
    @Produces({MediaType.TEXT_HTML})
    public InputStream chosenBook() throws FileNotFoundException {
        System.out.println("hello from chosenBook");
        return context.getResourceAsStream("chosenBook.html");
    }

    @GET
    @Path("getBookInfo")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBookInfo(@Context HttpHeaders headers, @QueryParam("id") int id) {
        System.out.println("from getBookInfo");

        List<String> auth = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);


        String token = auth.get(0).substring("Bearer".length()).trim();


        System.out.println(id);
        Book book = request.getBookInfo(id);
        System.out.println(book.name);
        BookPost bookPost = new BookPost();
        bookPost.setId(Integer.toString(book.getId()));
        bookPost.setName(book.getName());
        bookPost.setOwner_name(book.getOwner_name());
        bookPost.setOwner_id(Integer.toString(book.getOwner_id()));
        bookPost.setCurrent_holder_id(Integer.toString(book.getCurrent_holder_id()));
        bookPost.setCurrent_holder_name(book.getCurrent_holder_name());
        bookPost.setDescription(book.getDescription());
        bookPost.setUser_id(request.getUserId(token));
        bookPost.setUser_name(request.getUserName(token));
        bookPost.setOption(request.getOption(book.getId(),book.getOwner_id(),book.getCurrent_holder_id(),request.getUserId(token)));
        for (int i = 0 ; i < book.recs.size() ; i++){
            bookPost.recs.add(book.recs.get(i));
        }
        System.out.println("sizes");
        System.out.println(book.recs.size());
        System.out.println(bookPost.recs.size());
        Gson gson = new Gson();
        String json = gson.toJson(bookPost);
        return Response.ok(json).build();
    }

    @POST
    @Path("requestBook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response requestBook(@Context HttpHeaders headers, BookPost book
    ) {
        System.out.println("from requestBook");
        List<String> auth = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String token = auth.get(0).substring("Bearer".length()).trim();
        System.out.println(book.getName());
        System.out.println(book.getId());
        request.requestBook(book.getUser_id(),Integer.parseInt(book.getId()),Integer.parseInt(book.getOwner_id()),book.getOwner_name(),book.getUser_name());
        return Response.ok().build();
    }

    @POST
    @Path("returnBook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response returnBook(@Context HttpHeaders headers, BookPost book
    ) {
        System.out.println("from requestBook");
        List<String> auth = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String token = auth.get(0).substring("Bearer".length()).trim();
        System.out.println(book.getName());
        System.out.println(book.getId());
        request.requestBook(book.getUser_id(),Integer.parseInt(book.getId()),Integer.parseInt(book.getOwner_id()),book.getOwner_name(),book.getUser_name());
        return Response.ok().build();
    }

    @POST
    @Path("at")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response at(@Context HttpHeaders headers, BookPost book
    ) {
        System.out.println("from at");
        List<String> auth = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String token = auth.get(0).substring("Bearer".length()).trim();
        System.out.println(book.getName());
        System.out.println(book.taker_id);

        return Response.ok().build();
    }

}