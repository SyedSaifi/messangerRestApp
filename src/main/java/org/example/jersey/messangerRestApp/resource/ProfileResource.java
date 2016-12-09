package org.example.jersey.messangerRestApp.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.example.jersey.messangerRestApp.model.Profile;
import org.example.jersey.messangerRestApp.service.ProfileService;

@Path("/profile")
public class ProfileResource {
	ProfileService profileService = new ProfileService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getAllProfile(){
		return profileService.getAllProfile();
	}
	
	@GET
	@Path("/{shortname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Profile getProfile(@PathParam("shortname") String shortname){
		return profileService.getProfile(shortname);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile addProfile(Profile prof){
		return profileService.addProfile(prof);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{shortname}")
	public Profile updateProfile(Profile prof, @PathParam("shortname") String shortname){
		prof.setShortname(shortname);
		return profileService.updateProfile(prof);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{shortname}")
	public void removeProfile(@PathParam("shortname") String shortname){
		profileService.removeProfile(shortname);
	}

}
