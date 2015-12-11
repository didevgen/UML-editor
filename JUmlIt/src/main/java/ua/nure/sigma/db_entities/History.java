package ua.nure.sigma.db_entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "history")
public class History implements Serializable {
	private long eventId;
	private long userId;

	@Id
	@Column(name = "event_id")
	public long getEvent_id() {
		return eventId;
	}

	public void setEvent_id(long event_id) {
		this.eventId = event_id;
	}

	@Id
	@Column(name = "user_id")
	public long getUser_id() {
		return userId;
	}

	public void setUser_id(long user_id) {
		this.userId = user_id;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(this.userId) + Long.hashCode(this.eventId);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof History)) {
			return false;
		}
		History other = (History) obj;
		return Long.compare(other.getUser_id(), this.userId) == 0
				&& Long.compare(other.getEvent_id(), this.eventId) == 0;
	}
	
	

}
