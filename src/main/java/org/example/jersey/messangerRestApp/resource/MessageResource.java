package org.example.jersey.messangerRestApp.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.example.jersey.messangerRestApp.beanfilter.MessageFilterBean;
import org.example.jersey.messangerRestApp.model.Message;
import org.example.jersey.messangerRestApp.service.MessageService;
@Path("/message")
public class MessageResource {
	
	MessageService messageservice = new MessageService();
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{messageid}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//public List<Message> getAllMessage(@QueryParam ("year") int year, @QueryParam ("start") int start, @QueryParam ("size") int size){
	public List<Message> getAllMessage(@BeanParam MessageFilterBean filterbean){
		/*if(filterbean.getYear() > 0)
			return messageservice.getAllMessageYear(filterbean.getYear());
		if(filterbean.getStart() >=0 && filterbean.getSize()>=0)
			return messageservice.getMessagePaginated(filterbean.getStart(), filterbean.getSize());*/
		return messageservice.getAllMessage();
	}
	
	@GET
	@Path("/{messageid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageid") long messageid, @Context UriInfo uriinfo){
		Message msg =  messageservice.getMessage(messageid);
		msg.addLink(getURIForLink(uriinfo, msg), "self");
		msg.addLink(getURIForProfileLink(uriinfo, msg), "profile");
		msg.addLink(getURIForCommentsLink(uriinfo, msg), "comment");
		return msg;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message msg, @Context UriInfo uriinfo){
		Message newMessage = messageservice.addMessage(msg);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriinfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();

	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{messageid}")
	public Message updateMessage(Message msg, @PathParam("messageid") long messageid){
		msg.setId(messageid);
		return messageservice.updateMessage(msg);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{messageid}")
	public void removeMessage(@PathParam("messageid") long messageid){
		messageservice.removeMessage(messageid);
	}
	
	private String getURIForLink(UriInfo uriinfo, Message msg) {
		String uri = uriinfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(msg.getId()))
				.build().toString();
		return uri;
	}
	
	private String getURIForProfileLink(UriInfo uriinfo, Message msg) {
		String uri = uriinfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(msg.getName())
				.build().toString();
		return uri;
	}
	
	private String getURIForCommentsLink(UriInfo uriinfo, Message msg) {
		String uri = uriinfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageid", msg.getId())
				.build().toString();
		return uri;
	}
}
