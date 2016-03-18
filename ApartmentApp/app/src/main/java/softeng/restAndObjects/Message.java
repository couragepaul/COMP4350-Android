package softeng.restAndObjects;

public class Message implements DTOBase {
	public String sender, recipient, content;
	public int urgency, timestamp;
	public boolean read;
	
	public Message(
			String sender,
			String recipient,
			String content,
			int urgency,
			int timestamp,
			boolean read) {
		
		this.sender = sender;
		this.recipient = recipient;
		this.content = content;
		this.urgency = urgency;
		this.timestamp = timestamp;
		this.read = read;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("{")
				.append("\n\tsender : ").append(sender)
				.append("\n\trecipient : ").append(recipient)
				.append("\n\tcontent : ").append(content)
				.append("\n\turgency : ").append(urgency)
				.append("\n\ttimestamp : ").append(timestamp)
				.append("\n\tread : ").append(read)
				.append("\n}").toString();
	}
}
