package ua.nure.sigma.db_entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "Diagram")

public class Diagram {
	private long diagramId;
	private long ownerId;
	private long statusId;
	private String jsonData;
	private DateTime creationDate;
	private DateTime lastUpdated;

	public Diagram() {
		jsonData = null;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "diagram_id")
	@OneToMany
	@JoinTable(name = "Collaborator", joinColumns = @JoinColumn(name = "diagram_id"))
	public long getDiagramId() {
		return diagramId;
	}

	public void setDiagramId(long id) {
		diagramId = id;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "owner_id")
	@ManyToOne
	@JoinTable(name = "Collaborator", joinColumns = @JoinColumn(name = "user_id"))
	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long id) {
		ownerId = id;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "status_id")
	@ManyToOne
	@JoinTable(name = "status_id")
	public long getStatus_id() {
		return statusId;
	}

	public void setStatus_id(long status_id) {
		this.statusId = status_id;
	}

	@Column(name = "json_data")
	public String getJson_data() {
		return jsonData;
	}

	public void setJson_data(String json_data) {
		this.jsonData = json_data;
	}

	@Column(name = "creation_date")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	public DateTime getCreation_date() {
		return creationDate;
	}

	public void setCreation_date(DateTime creation_date) {
		this.creationDate = creation_date;
	}

	@Column(name = "last_updated")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	public DateTime getLast_updated() {
		return lastUpdated;
	}

	public void setLast_updated(DateTime last_updated) {
		this.lastUpdated = last_updated;
	}

}
