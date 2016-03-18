package softeng.restAndObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RestInterface {
	private final static String WEBSITE_BASE = "http://apartment-app.pfsa2harbh.us-west-2.elasticbeanstalk.com/api/";
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	private static String get(String url) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		String resp = null;
		
		try {
			Response response = client.newCall(request).execute(); 
			resp = response.body().string();
		} catch (IOException e) {
			System.err.println("Error during rest request: " + e);
		}
		
		return resp;
	}
	
	private static String post(String url, String body) {
		OkHttpClient client = new OkHttpClient();
		String resp = null;
		Request request = new Request.Builder()
				.url(url)
				.post(RequestBody.create(JSON, body))
				.build();
		
		try {
			Response response = client.newCall(request).execute();
			resp = response.body().string();
		} catch (IOException e) {
			System.err.println("Error during rest request: " + e);
		}
		
		return resp;
	}
	
	public static ArrayList<Message> getMessages(String recipient) {
		String url = new StringBuilder(WEBSITE_BASE).append("messages/")
				.append(recipient).append("/").toString();
		
		String response = get(url);
		List<DTOBase> respObjs = new Parser(response, MessageResponse.class).getReturnedObjects();
		ArrayList<Message> castedObjs = new ArrayList<Message>();
		
		for (int i = 0; i < respObjs.size(); i++)
			castedObjs.add((Message)respObjs.get(i));
		
		return castedObjs;
	}
	
	public static boolean sendMessage(Message send) {
		String url = new StringBuilder(WEBSITE_BASE).append("messages/").toString();
		String response = post(url, Parser.serialize(send));
		return new Parser(response, SendMessageResponse.class).isSuccessful();
	}
	
	public static boolean readMessage(Message send) {
		String url = new StringBuilder(WEBSITE_BASE).append("messages/mark_read/")
				.toString();
		String response = post(url, Parser.serialize(send));
		return new Parser(response, SendMessageResponse.class).isSuccessful();
	}
	
	public static ArrayList<Bulletin> getBulletins() {
		String url = new StringBuilder(WEBSITE_BASE)
				.append("bulletins/").toString();
		
		String response = get(url);
		List<DTOBase> respObjs = new Parser(response, BulletinResponse.class).getReturnedObjects();
		ArrayList<Bulletin> castedObjs = new ArrayList<Bulletin>();
		
		for (int i = 0; i < respObjs.size(); i++)
			castedObjs.add((Bulletin)respObjs.get(i));
		
		return castedObjs;
	}	
	
	public static boolean sendBulletin(Bulletin send) {
		String url = new StringBuilder(WEBSITE_BASE).append("bulletins/").toString();
		String response = post(url, Parser.serialize(send));		
		return new Parser(response, SendBulletinResponse.class).isSuccessful();
	}
	
	public static ArrayList<Comment> getComments(Bulletin ref) {
		String url = new StringBuilder(WEBSITE_BASE).append("comments/")
				.append(ref.getReference()).append("/").toString();
		
		String response = get(url);
		List<DTOBase> respObjs = new Parser(response, CommentResponse.class)
				.getReturnedObjects();
		ArrayList<Comment> castedObjs = new ArrayList<Comment>();
		
		for (int i = 0; i < respObjs.size(); i++)
			castedObjs.add((Comment)respObjs.get(i));
		
		return castedObjs;
	}
	
	public static boolean sendComment(Comment send) {
		String url = new StringBuilder(WEBSITE_BASE).append("comments/").toString();
		String response = post(url, Parser.serialize(send));		
		return new Parser(response, SendCommentResponse.class).isSuccessful();
	}

	public static ArrayList<Event> getEvents() {
		String url = new StringBuilder(WEBSITE_BASE).append("events/").toString();
		String response = get(url);
		
		List<DTOBase> respObjs = new Parser(response, EventResponse.class)
				.getReturnedObjects();
		ArrayList<Event> castedObjs = new ArrayList<Event>();
		
		for (int i = 0; i < respObjs.size(); i++)
			castedObjs.add((Event)respObjs.get(i));
		
		return castedObjs;
 	}

	public static boolean sendEvent(Event send) {
		String url = new StringBuilder(WEBSITE_BASE).append("events/").toString();
		String response = post(url, Parser.serialize(send));		
		return new Parser(response, SendEventResponse.class).isSuccessful();
	}
	
	public static void main(String[] args) {		
		RestInterface.getMessages("test");
		RestInterface.sendMessage(new Message("umm","umm","umm",1,1,false));
	}
}
