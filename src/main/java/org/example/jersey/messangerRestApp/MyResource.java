package org.example.jersey.messangerRestApp;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@MatrixParam ("param") String param, @HeaderParam("authid") String authid, @CookieParam ("name") String name) {
        return "Matrix param : "+param+ " with authid : "+authid+" and cookies name as : "+name;
    }
    
    @GET
    @Path("/context")
    @Produces(MediaType.TEXT_PLAIN)
    public String getContext(@Context UriInfo uniInfo, @Context HttpHeaders headers){
    	String cookie = headers.getCookies().toString();
    	String path = uniInfo.getAbsolutePath().toString();
    	
    	return "Path : "+path+" Cookies : "+cookie;
    }
    
}
