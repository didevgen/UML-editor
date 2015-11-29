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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ua.nure.sigma.db_entities.Diagram;
@Entity
@Table(name = "class")
public class Clazz {
	
	private long classId;
	
	private String name;
	
	private boolean isStatic;
	
	private String accessModifier;
	
	private List<Field> fields = new ArrayList<Field>();
	
	private List<Method> methods = new ArrayList<>();
	
	private Position position;
	
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
		} else if (!name.equals(other.name))
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
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	@OneToMany(mappedBy = "classOwner",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "clazz", cascade = CascadeType.ALL)
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagram_id")
	public Diagram getDiagramOwner() {
		return diagramOwner;
	}

	public void setDiagramOwner(Diagram diagramOwner) {
		this.diagramOwner = diagramOwner;
	}
	
	
}
