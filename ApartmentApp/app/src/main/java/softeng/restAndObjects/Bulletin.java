package softeng.restAndObjects;

class Bulletin implements DTOBase {
	public String sender, subject, content;
	public int timestamp;
	
	public Bulletin(
			String sender,
			String subject,
			String content,
			int timestamp) {
		
		this.sender = sender;
		this.subject = subject;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("{")
				.append("\n\tsender : ").append(sender)
				.append("\n\tsubject : ").append(subject)
				.append("\n\tcontent : ").append(content)
				.append("\n\ttimestamp : ").append(timestamp)
				.append("\n}").toString();
	}
	
	public String getReference() {
		return new StringBuilder(sender).append(":").append(timestamp).toString();
	}
}
