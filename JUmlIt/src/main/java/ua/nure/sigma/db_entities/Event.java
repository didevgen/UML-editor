package ua.nure.sigma.db_entities;

import java.math.BigInteger;
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
	private BigInteger eventId;
	private BigInteger eventTypeId;
	private Date timeStamp;

	public Event(Event id) {
		eventId = id.getEvent_id();
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "event_id")
	public BigInteger getEvent_id() {
		return eventId;
	}

	public void setEvent_id(BigInteger event_id) {
		this.eventId = event_id;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "event_type_id")
	public BigInteger getEvent_type_id() {
		return eventTypeId;
	}

	public void setEvent_type_id(BigInteger event_type_id) {
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
