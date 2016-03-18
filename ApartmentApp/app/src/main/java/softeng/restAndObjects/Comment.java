package softeng.restAndObjects;

class Comment implements DTOBase {
	public String sender, content, bulletin_reference;
	public int timestamp;
	
	public Comment(
			String bulletin_reference,
			String sender,
			String content,
			int timestamp) {
		
		this.sender = sender;
		this.bulletin_reference = bulletin_reference;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("{")
				.append("\n\tsender : ").append(sender)
				.append("\n\tbulletin_reference : ").append(bulletin_reference)
				.append("\n\tcontent : ").append(content)
				.append("\n\ttimestamp : ").append(timestamp)
				.append("\n}").toString();
	}
}
