package ua.sigma.nure.db_entities;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Diagram")

public class Diagram {
	private BigInteger diagram_id;
	private BigInteger owner_id;
	private BigInteger status_id;
	private String json_data;
	private Date creation_date;
	private Date last_updated;

	public Diagram() {
		json_data = null;
	}

	public Diagram(Diagram id) {
		diagram_id = id.getDiagramId();
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "diagram_id")
	@OneToMany
	@JoinTable(name = "Collaborator", joinColumns = @JoinColumn(name = "diagram_id"))
	public BigInteger getDiagramId() {
		return diagram_id;
	}

	public void setDiagramId(BigInteger id) {
		diagram_id = id;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "owner_id")
	@ManyToOne
	@JoinTable(name = "Collaborator", joinColumns = @JoinColumn(name = "user_id"))
	public BigInteger getOwnerId() {
		return owner_id;
	}

	public void setOwnerId(BigInteger id) {
		owner_id = id;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "status_id")
	@ManyToOne
	@JoinTable(name = "status_id")
	public BigInteger getStatus_id() {
		return status_id;
	}

	public void setStatus_id(BigInteger status_id) {
		this.status_id = status_id;
	}

	@Column(name = "json_data")
	public String getJson_data() {
		return json_data;
	}

	public void setJson_data(String json_data) {
		this.json_data = json_data;
	}

	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	@Column(name = "last_updated")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

}
