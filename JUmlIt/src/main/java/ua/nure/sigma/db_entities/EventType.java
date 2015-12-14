package ua.nure.sigma.db_entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "eventtype")
public class EventType {
	private long eventTypeId;
	private String description ="";

	public EventType() {
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

	@Column(name = "description", length = 256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
