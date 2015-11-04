package ua.sigma.nure.db_entities;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "User")

public class User {
	private BigInteger user_id;
	private String full_name;
	private String email;
	private String password;
	private Date registration_date;
	private Date last_available;

	public User() {
		full_name = null;
	}

	public User(User id) {
		user_id = id.getUserId();
	};

	public User(User name, User e_mail) {
		full_name = name.getName();
		email = e_mail.getEmail();
	}
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "user_id")
	@OneToMany
	@JoinTable(name = "Collaborator", joinColumns = @JoinColumn(name = "user_id"))
	public BigInteger getUserId() {
		return user_id;
	}

	public void setUserId(BigInteger id) {
		user_id = id;
	}
	@Column(name = "full_name", length = 256)
	public String getName() {
		return full_name;
	}

	public void setFullName(String name) {
		full_name = name;
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
	@Temporal(TemporalType.DATE)
	public Date getRegDate() {
		return registration_date;
	}

	public void setRegDate(Date rd) {
		registration_date = rd;
	}
	@Column(name = "last_available")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastAvailable() {
		return last_available;
	}

	public void setLastAvailable(Date la) {
		last_available = la;
	}
}
