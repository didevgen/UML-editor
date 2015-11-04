package ua.nure.sigma.db_entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Collaborator")
public class Collaborator {
	private BigInteger user_id;
	private BigInteger diagram_id;

	public Collaborator(Collaborator id) {
		user_id = id.getCollId();

	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "user_id")
	@ManyToOne
	@JoinTable(name = "User", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "owner_id"))
	public BigInteger getCollId() {
		return user_id;
	}

	public void setCollId(BigInteger id) {
		user_id = id;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "diagram_id")
	public BigInteger getDiagramId() {
		return diagram_id;
	}

	public void setDiagramId(BigInteger id) {
		diagram_id = id;
	}

}
