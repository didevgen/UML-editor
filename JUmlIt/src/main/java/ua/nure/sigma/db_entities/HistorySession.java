package ua.nure.sigma.db_entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "history_session")
public class HistorySession {
	private long sessionId;

	@JsonIgnore
	private User user;
	
	@JsonProperty 
	private String userName;

	private Diagram diagram;

	private Date timeStart;

	private Date timeFinish;
	
	private String subscriptionId;

	public HistorySession() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (sessionId ^ (sessionId >>> 32));
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
		HistorySession other = (HistorySession) obj;
		if (sessionId != other.sessionId)
			return false;
		return true;
	}
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "session_id")
	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		if (user != null) {
			this.userName = user.getEmail();
		}
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagram_id")
	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}
	@Column(name = "time_start")
	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
	@Column(name = "time_finish")
	public Date getTimeFinish() {
		return timeFinish;
	}

	public void setTimeFinish(Date timeFinish) {
		this.timeFinish = timeFinish;
	}
	@Column(name = "subscription_id")
	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
