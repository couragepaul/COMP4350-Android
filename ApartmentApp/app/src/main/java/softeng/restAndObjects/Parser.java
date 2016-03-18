package softeng.restAndObjects;

import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Parser {
	private Gson gson;
	private DefaultResponse response;
	
	public Parser(String toParse, Class<?> type) {
		this.gson = new Gson();
		this.response = (DefaultResponse) gson.fromJson(toParse, type);
		this.response = (DefaultResponse) gson.fromJson(toParse, type); //this.response = gson.fromJson(toParse, type);
	}
	
	public boolean isSuccessful() {
		return response.Status.equalsIgnoreCase("Success");
	}
	
	public List<DTOBase> getReturnedObjects() {
		if (isSuccessful()) {
			ArrayList<DTOBase> objs = new ArrayList<DTOBase>();
			
			if (response instanceof MessageResponse) {
				for (Message msg: ((MessageResponse)response).Response)
					objs.add(msg);
			} else if (response instanceof BulletinResponse) {
				for (Bulletin btn: ((BulletinResponse)response).Response)
					objs.add(btn);
			} else if (response instanceof CommentResponse) {
				for (Comment cmt: ((CommentResponse)response).Response)
					objs.add(cmt);
			} else if (response instanceof EventResponse) {
				for (Event evt: ((EventResponse)response).Response)
					objs.add(evt);
			} 
//			else if (response instanceof SendMessageResponse) {
//				objs.add(((SendMessageResponse)response).Received);
//			} else if (response instanceof SendBulletinResponse) {
//				objs.add(((SendBulletinResponse)response).Received);
//			} else if (response instanceof SendCommentResponse) {
//				objs.add(((SendCommentResponse)response).Received);
//			} else if (response instanceof SendEventResponse) {
//				objs.add(((SendEventResponse)response).Received);
//			}
			
			return objs;
		}
		
		return new ArrayList<DTOBase>();
	}

	public static String serialize(DTOBase obj) {
		return new Gson().toJson(obj);
	}
}
