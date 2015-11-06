package ua.nure.sigma.db_entities;

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
public class Event {
	private long eventId;
	private long eventTypeId;
	private DateTime timeStamp;

	public Event() {
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "event_id")
	public long getEvent_id() {
		return eventId;
	}

	public void setEvent_id(long event_id) {
		this.eventId = event_id;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
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
	
}
