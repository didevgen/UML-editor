package ua.nure.sigma.db_entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EventType")
public class EventType {
	private BigInteger eventTypeId;
	private String description;

	public EventType() {
		description = null;
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

	@Column(name = "description", length = 256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
