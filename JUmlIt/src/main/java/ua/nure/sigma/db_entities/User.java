package ua.nure.sigma.db_entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "user")
@Component
@Scope("session")
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id",
//		scope = Diagram.class)
public class User {
	private long userId;
	private String fullname ="";
	private String email ="";
	private String password ="";
	private DateTime registrationDate;
	private DateTime lastAvailable;

	private Set<UserRole> userRoles = new HashSet<UserRole>();

	private Set<Diagram> collaboratedDiagrams = new HashSet<Diagram>();
	private List<HistorySession> history = new ArrayList<>();

	public User() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public User(String fullname, String email, String password) {
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "user_id")
	public long getUserId() {
		return userId;
	}

	public void setUserId(long id) {
		userId = id;
	}

	@Column(name = "email", length = 256)
	public String getEmail() {
		return email;
	}

	public void setEmail(String e) {
		email = e;
	}

	@Column(name = "password",updatable=false)
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String p) {
		password = p;
	}

	@Column(name = "last_available")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getLastAvailable() {
		return lastAvailable;
	}

	public void setLastAvailable(DateTime la) {
		lastAvailable = la;
	}

	@Column(name = "full_name", length = 256)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "registration_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(DateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullname + ", email=" + email + ", password=" + password
				+ ", registrationDate=" + registrationDate + ", lastAvailable=" + lastAvailable + "]";
	}

	@ManyToMany(mappedBy = "collaborators")
	public Set<Diagram> getCollaboratedDiagrams() {
		return collaboratedDiagrams;
	}

	public void setCollaboratedDiagrams(Set<Diagram> collaboratedDiagrams) {
		this.collaboratedDiagrams = collaboratedDiagrams;
	}

	@OneToMany(mappedBy = "user")
	public List<HistorySession> getHistory() {
		return history;
	}

	public void setHistory(List<HistorySession> history) {
		this.history = history;
	}

}
