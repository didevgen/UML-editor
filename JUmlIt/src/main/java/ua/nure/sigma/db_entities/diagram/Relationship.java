package ua.nure.sigma.db_entities.diagram;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ua.nure.sigma.db_entities.Diagram;

@Entity
@Table(name = "relationships")
public class Relationship {

	private long id;

	private Clazz primaryMember;

	private Clazz secondaryMember;

	private String primaryToSecondaryMultiplicity;

	private String secondaryToPrimaryMultiplicity;

	private String name;

	private String type;

	private String primaryProps;

	private String secondaryProps;
	
	private Diagram diagram;

	public Relationship() {
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "relation_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "primary_id")
	public Clazz getPrimaryMember() {
		return primaryMember;
	}

	public void setPrimaryMember(Clazz primaryMember) {
		this.primaryMember = primaryMember;
	}

	@ManyToOne
	@JoinColumn(name = "secondary_id")
	public Clazz getSecondaryMember() {
		return secondaryMember;
	}

	public void setSecondaryMember(Clazz secondaryMember) {
		this.secondaryMember = secondaryMember;
	}


	@Column(name = "primary_multy")
	public String getPrimaryToSecondaryMultiplicity() {
		return primaryToSecondaryMultiplicity;
	}

	public void setPrimaryToSecondaryMultiplicity(String primaryToSecondaryMultiplicity) {
		this.primaryToSecondaryMultiplicity = primaryToSecondaryMultiplicity;
	}
	@Column(name = "secondary_multy")
	public String getSecondaryToPrimaryMultiplicity() {
		return secondaryToPrimaryMultiplicity;
	}

	public void setSecondaryToPrimaryMultiplicity(String secondaryToPrimaryMultiplicity) {
		this.secondaryToPrimaryMultiplicity = secondaryToPrimaryMultiplicity;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "primary_props")
	public String getPrimaryProps() {
		return primaryProps;
	}

	public void setPrimaryProps(String primaryProps) {
		this.primaryProps = primaryProps;
	}

	@Column(name = "secondary_props")
	public String getSecondaryProps() {
		return secondaryProps;
	}

	public void setSecondaryProps(String secondaryProps) {
		this.secondaryProps = secondaryProps;
	}
	@ManyToOne()
	@JoinColumn(name = "diagram_id")
	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Relationship other = (Relationship) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Relationship [primaryToSecondaryMultiplicity=" + primaryToSecondaryMultiplicity
				+ ", secondaryToPrimaryMultiplicity=" + secondaryToPrimaryMultiplicity + ", name=" + name + ", type="
				+ type + ", primaryProps=" + primaryProps + ", secondaryProps=" + secondaryProps + "]";
	}
	
	

}
