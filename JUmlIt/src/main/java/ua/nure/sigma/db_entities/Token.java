package ua.nure.sigma.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "token")
public class Token {
	
	private long tokenId;
	
	private String token ="";
	
	private long userId;
	
	public Token() {
	}
	public Token(String token, long userId) {
		this.token = token;
		this.userId = userId;
	}
	public Token(long tokenId, String token, long userId) {
		this.tokenId = tokenId;
		this.token = token;
		this.userId = userId;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "tokenId")
	public long getTokenId() {
		return tokenId;
	}

	public void setTokenId(long tokenId) {
		this.tokenId = tokenId;
	}
	@Column(name = "tokenValue")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	@Column(name = "userId")
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
