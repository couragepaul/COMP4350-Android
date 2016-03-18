package softeng.restAndObjects;

import java.util.ArrayList;

class DefaultResponse {
	String Status;
}

class MessageResponse extends DefaultResponse {
	ArrayList<Message> Response;
}

class BulletinResponse extends DefaultResponse {
	ArrayList<Bulletin> Response;
}

class CommentResponse extends DefaultResponse {
	ArrayList<Comment> Response;
}

class EventResponse extends DefaultResponse {
	ArrayList<Event> Response;
}

class SendMessageResponse extends DefaultResponse {
	Message Received;
}

class SendBulletinResponse extends DefaultResponse {
	Bulletin Received;
}

class SendCommentResponse extends DefaultResponse {
	Comment Received;
}

class SendEventResponse extends DefaultResponse {
	Event Received;
}