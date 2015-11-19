package ua.nure.sigma.db_entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "event")
public class Event implements Serializable{
	private long eventId;
	private long eventTypeId;
	private DateTime timeStamp;

	public Event() {
	}

	@Id
	@Column(name = "event_id")
	public long getEvent_id() {
		return eventId;
	}

	public void setEvent_id(long event_id) {
		this.eventId = event_id;
	}

	@Id
	@Column(name = "event_type_id")
	public long getEvent_type_id() {
		return eventTypeId;
	}

	public void setEvent_type_id(long event_type_id) {
		this.eventTypeId = event_type_id;
	}

	@Column(name = "last_available")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getTimestamp() {
		return timeStamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timeStamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		result = prime * result + (int) (eventTypeId ^ (eventTypeId >>> 32));
		result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
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
		if (eventTypeId != other.eventTypeId)
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		return true;
	}
	
	
	
}
