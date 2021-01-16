package me.modernpage.restapitutorial.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import me.modernpage.restapitutorial.messenger.database.DatabaseClass;
import me.modernpage.restapitutorial.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L,  new Message(1L, "Hello World", "Yusuf"));
		messages.put(2L, new Message(2L, "Hello Jersey", "Yusuf"));
	}
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id); 
	}
	
	public List<Message> getAllMessagesforYear(int year) {
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message message: messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		
		return messagesForYear;
	}
	
	public List<Message> getAllMessagePeginated(int start, int size) {
		List<Message> list = new ArrayList<Message>(messages.values());
		if(start+size > list.size()) {
			return list.subList(start, ((start + size) - ((start + size) - list.size()))-1);
		}
		return list.subList(start, start + size);
	}
	
	
	
	
}
