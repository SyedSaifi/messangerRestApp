package org.example.jersey.messangerRestApp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.jersey.messangerRestApp.database.DatabaseClass;
import org.example.jersey.messangerRestApp.exception.DataNotFoundException;
import org.example.jersey.messangerRestApp.model.Comment;
import org.example.jersey.messangerRestApp.model.Message;

public class MessageService {
	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1L,"Syed", "Hi Syed"));
		Map<Long,Comment> comment1 = new HashMap<>();
		comment1.put(1L, new Comment(1L,"Syed", "Syed Message"));
		comment1.put(2L, new Comment(2L,"Talib", "Talib Message"));
		messages.get(1L).setComments(comment1);
		
		messages.put(2L, new Message(2L,"Talib", "Hi Talib"));
		Map<Long,Comment> comment2 = new HashMap<>();
		comment2.put(3L, new Comment(3L,"Saifi", "Saifi Message"));
		messages.get(2L).setComments(comment2);
		
		messages.put(3L, new Message(3L,"Saifi", "Hi Saifi"));
	}
	
	public List<Message> getAllMessage(){
		return new ArrayList<>(messages.values());
	}
	
	public List<Message> getAllMessageYear(int year){
		List<Message> messageyear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message msg: messages.values()){
			cal.setTime(msg.getDate());
			if(cal.get(Calendar.YEAR) == year)
				messageyear.add(msg);
		}
		return messageyear;
	}
	
	public List<Message> getMessagePaginated(int start, int size){
		List<Message> list = new ArrayList<>(messages.values());
		if(start+size>list.size())
			return new ArrayList<>();
		return list.subList(start, start+size);
	}
	
	public Message getMessage(Long id){
		Message msg = messages.get(id);
		if(msg == null)
			throw new DataNotFoundException("Message with id :"+id+" not found.");
		return msg;
	}
	
	public Message updateMessage(Message msg){
		if(msg.getId() <= 0)
			return null;
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message addMessage(Message msg){
		msg.setId(messages.size()+1);
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public void removeMessage(Long id){
		messages.remove(id);
	}
}
