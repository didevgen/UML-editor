package ua.nure.sigma.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "User")

public class User {
	private long userId;
	private String fullname;
	private String email;
	private String password;
	private DateTime registrationDate;
	private DateTime lastAvailable;

	public User() {
	}

	public User(User name, User e_mail) {
		fullname = name.getName();
		email = e_mail.getEmail();
	}
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "user_id")
	@OneToMany
	@JoinTable(name = "Collaborator", joinColumns = @JoinColumn(name = "user_id"))
	public long getUserId() {
		return userId;
	}

	public void setUserId(long id) {
		userId = id;
	}
	@Column(name = "full_name", length = 256)
	public String getName() {
		return fullname;
	}

	public void setFullName(String name) {
		fullname = name;
	}
	@Column(name = "email", length = 256)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String p) {
		email = p;
	}
	@Column(name = "regitration_date")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	public DateTime getRegDate() {
		return registrationDate;
	}

	public void setRegDate(DateTime rd) {
		registrationDate = rd;
	}
	@Column(name = "last_available")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	public DateTime getLastAvailable() {
		return lastAvailable;
	}

	public void setLastAvailable(DateTime la) {
		lastAvailable = la;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullname + ", email=" + email + ", password=" + password
				+ ", registrationDate=" + registrationDate + ", lastAvailable=" + lastAvailable + "]";
	}
	
	
}
