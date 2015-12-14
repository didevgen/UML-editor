package ua.nure.sigma.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "user_role")
@Component
@Scope("session")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, 
	property = "@id", scope = UserRole.class)
public class UserRole {

	private long userRoleId;
	private String role ="";
    private User user;
	
	public UserRole() {
		
	}
	
	public UserRole(User user, String role) {
		this.user = user;
		this.role = role;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "role_id")
	public long getUserRoleId() {
		return userRoleId;
	}

	@Column(name = "role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
