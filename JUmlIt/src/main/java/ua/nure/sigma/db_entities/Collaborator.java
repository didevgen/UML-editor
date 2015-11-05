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
	private long userId;
	private long diagramId;

	public Collaborator() {

	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "user_id")
	@ManyToOne
	@JoinTable(name = "User", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "owner_id"))
	public long getCollId() {
		return userId;
	}

	public void setCollId(long id) {
		userId = id;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "diagram_id")
	public long getDiagramId() {
		return diagramId;
	}

	public void setDiagramId(long id) {
		diagramId = id;
	}

}
