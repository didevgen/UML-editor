package ua.nure.sigma.db_entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "diagram")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, 
	property = "@id")
public class Diagram {
	private long diagramId;
	private long statusId = -1;
	private String jsonData = "";
	private DateTime creationDate;
	private DateTime lastUpdated;
	private String name = "";
	private String description="";
	private Set<User> collaborators = new HashSet<User>();
	private List<Clazz> classes = new ArrayList<>();
	private List<Relationship> relationships = new ArrayList<>();
	private List<HistorySession> history = new ArrayList<>();
	private User owner;
	
	public Diagram() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (diagramId ^ (diagramId >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Diagram other = (Diagram) obj;
		if (diagramId != other.diagramId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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

	@ManyToOne
	@JoinColumn(name = "owner_id")
	public User getOwner() {
		return this.owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "collaborator",  joinColumns = { 
			@JoinColumn(name = "diagram_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "user_id", 
					nullable = false, updatable = false) })
	public Set<User> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(Set<User> collaborators) {
		this.collaborators = collaborators;
	}
	@OneToMany(mappedBy = "diagramOwner")
	@NotFound(action = NotFoundAction.IGNORE)
	public List<Clazz> getClasses() {
		return classes;
	}

	public void setClasses(List<Clazz> classes) {
		this.classes = classes;
	}
	@OneToMany(mappedBy = "diagram")
	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}
	@OneToMany(mappedBy = "diagram")
	public List<HistorySession> getHistory() {
		return history;
	}

	public void setHistory(List<HistorySession> history) {
		this.history = history;
	}
	
	

}
