package ua.nure.sigma.model;

import org.joda.time.DateTime;

public class User {
	
	private long userId;
	
	private String fullName;
	
	private String email;
	
	private String password;
	
	private DateTime registrationDate;
	
	private DateTime lastAvailable;
	
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


	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", password=" + password
				+ ", registrationDate=" + registrationDate + ", lastAvailable=" + lastAvailable + "]";
	}


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(DateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public DateTime getLastAvailable() {
		return lastAvailable;
	}

	public void setLastAvailable(DateTime lastAvailable) {
		this.lastAvailable = lastAvailable;
	}

}
