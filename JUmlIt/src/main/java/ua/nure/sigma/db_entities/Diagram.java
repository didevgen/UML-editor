package ua.nure.sigma.db_entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "diagram")

public class Diagram {
	private long diagramId;
	private long ownerId = -1;
	private long statusId = -1;
	private String jsonData = "";
	private DateTime creationDate;
	private DateTime lastUpdated;
	private String name = "";
	private String description="";
	
	
	public Diagram() {
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

	@Column(name = "owner_id")
	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long id) {
		ownerId = id;
	}

	@Column(name = "status_id")
	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
	
	@Column(name = "json_data")
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	@Column(name = "created_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	@Column(name = "last_updated")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(DateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
