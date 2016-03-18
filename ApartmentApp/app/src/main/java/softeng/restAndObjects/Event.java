package me.jdasilva.test;

class Event implements DTOBase {
    public String sender, content, title;
    public int timestamp, starttime, endtime;

    public Event(String sender,
    		String content,
    		String title,
    		int timestamp,
    		int starttime,
    		int endtime) {
		this.sender = sender;
		this.title = title;
		this.content = content;
		this.timestamp = timestamp;
		this.starttime = starttime;
		this.endtime = endtime;
    }

	@Override
	public String toString() {
		return new StringBuilder("{")
				.append("\n\tsender : ").append(sender)
				.append("\n\ttitle : ").append(title)
				.append("\n\tcontent : ").append(content)
				.append("\n\ttimestamp : ").append(timestamp)
				.append("\n\tstarttime : ").append(starttime)
				.append("\n\tendtime : ").append(endtime)
				.append("\n}").toString();
	}
}
