package ua.nure.sigma.db_entities;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "History")
public class History {
private BigInteger event_id;
private BigInteger user_id;

@Id
@GeneratedValue(generator = "increment")
@GenericGenerator(name = "increment", strategy = "increment")
@Column(name = "event_id")
public BigInteger getEvent_id() {
	return event_id;
}
public void setEvent_id(BigInteger event_id) {
	this.event_id = event_id;
}
@Id
@GeneratedValue(generator = "increment")
@GenericGenerator(name = "increment", strategy = "increment")
@Column(name = "user_id")
public BigInteger getUser_id() {
	return user_id;
}
public void setUser_id(BigInteger user_id) {
	this.user_id = user_id;
}

}
