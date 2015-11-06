package ua.nure.sigma.db_entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Event")
public class Event {
	private long eventId;
	private long eventTypeId;
	private Date timeStamp;

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
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTimestamp() {
		return timeStamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timeStamp = timestamp;
	}
}
