package ua.nure.sigma.db_entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "history")
public class History {
private long eventId;
private long userId;

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
@Column(name = "user_id")
public long getUser_id() {
	return userId;
}
public void setUser_id(long user_id) {
	this.userId = user_id;
}

}
