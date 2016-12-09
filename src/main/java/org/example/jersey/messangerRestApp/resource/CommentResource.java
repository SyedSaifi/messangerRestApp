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

import org.example.jersey.messangerRestApp.model.Comment;
import org.example.jersey.messangerRestApp.service.CommentService;

@Path("/")
public class CommentResource {
CommentService commentService = new CommentService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getAllComment(@PathParam("messageid") long messageid){
		return commentService.getAllComment(messageid);
	}
	
	@GET
	@Path("/{commentid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getComment(@PathParam("messageid") long messageid, @PathParam("commentid") String commentid){
		return commentService.getComment(messageid,commentid);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment addComment(@PathParam("messageid") long messageid, Comment comment){
		return commentService.addComment(messageid, comment);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{commentid}")
	public Comment updateComment(Comment comment, @PathParam("messageid") long messageid, @PathParam("commentid") long commentid){
		comment.setId(commentid);
		return commentService.updateComment(messageid, comment);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{commentid}")
	public void removeComment(@PathParam("messageid") long messageid, @PathParam("commentid") String commentid){
		commentService.removeComment(messageid, commentid);
	}

}
