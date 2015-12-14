package ua.nure.sigma.db_entities.diagram;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ua.nure.sigma.db_entities.Diagram;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "class")
public class Clazz {
	
	private long classId;
	
	private String name ="";
	
	private boolean isStatic;
	
	private String accessModifier ="";
	
	private String classType ="";
	
	private List<Field> fields = new ArrayList<Field>();
	
	private List<Method> methods = new ArrayList<>();
	
	@JsonIgnore
	private List<Relationship> primaryRelations = new ArrayList<>();
	
	@JsonIgnore
	private List<Relationship> secondaryRelations = new ArrayList<>();
	
	private long x;

	private long y;
	
	@JsonIgnore
	private Diagram diagramOwner;
	
	public Clazz() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Clazz other = (Clazz) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (Long.compare(classId, other.classId) != 0)
			return false;
		return true;
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "class_id")
	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	@Column(name = "class_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "is_static")
	public boolean isStatic() {
		return isStatic;
	}
	
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	@Column(name = "class_access")
	public String getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(String accessModifier) {
		this.accessModifier = accessModifier;
	}
	
	@OneToMany(mappedBy = "classOwner",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	@OneToMany(mappedBy = "classOwner",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagram_id")
	public Diagram getDiagramOwner() {
		return diagramOwner;
	}

	public void setDiagramOwner(Diagram diagramOwner) {
		this.diagramOwner = diagramOwner;
	}
	@Column(name = "class_type")
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	@OneToMany(mappedBy = "primaryMember",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	public List<Relationship> getPrimaryRelations() {
		return primaryRelations;
	}

	public void setPrimaryRelations(List<Relationship> primaryRelations) {
		this.primaryRelations = primaryRelations;
	}
	
	@OneToMany(mappedBy = "secondaryMember",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	public List<Relationship> getSecondaryRelations() {
		return secondaryRelations;
	}

	public void setSecondaryRelations(List<Relationship> secondaryRelations) {
		this.secondaryRelations = secondaryRelations;
	}
	@Column(name = "x")
	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}
	@Column(name = "y")
	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}
	
	
}
