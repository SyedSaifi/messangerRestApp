package org.example.jersey.messangerRestApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.example.jersey.messangerRestApp.database.DatabaseClass;
import org.example.jersey.messangerRestApp.model.Comment;
import org.example.jersey.messangerRestApp.model.Message;

public class CommentService {
	private Map<Long,Message> messages = DatabaseClass.getMessages();

	public List<Comment> getAllComment(long messageId){
		Map<Long,Comment> comments =messages.get(messageId).getComments();
		return new ArrayList<>(comments.values());
	}
	
	public Comment getComment(long messageId,String commentId){
		Map<Long,Comment> comments =messages.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment updateComment(long messageId, Comment comment){
		Map<Long,Comment> comments =messages.get(messageId).getComments();
		if(comment.getId()<=0)
			return null;
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment){
		Map<Long,Comment> comments =messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public void removeComment(long messageId,String commentId){
		Map<Long,Comment> comments =messages.get(messageId).getComments();
		comments.remove(commentId);
	}

}
