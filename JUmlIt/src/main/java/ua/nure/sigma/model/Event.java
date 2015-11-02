package ua.nure.sigma.model;

import org.joda.time.DateTime;

import ua.nure.sigma.enums.Status;

public class Event {

	private long eventId;
	
	private DateTime timeStamp;
	
	private Status status;
	
	public Event() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventId != other.eventId)
			return false;
		return true;
	}

	public long getEventId() {
		return eventId;
	}


	public void setEventId(long eventId) {
		this.eventId = eventId;
	}


	public DateTime getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(DateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	
}
