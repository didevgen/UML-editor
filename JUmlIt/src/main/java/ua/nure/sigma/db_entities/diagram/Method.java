package ua.nure.sigma.db_entities.diagram;

import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "method")
public class Method {
	private long id;

	private String accessModifier ="";

	private boolean isStatic;

	private String name ="";

	private String returnType="";

	private List<Argument> args = new ArrayList<>();
	
	@JsonIgnore 
	private Clazz classOwner;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args == null) ? 0 : args.hashCode());
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
		Method other = (Method) obj;
		if (args == null) {
			if (other.args != null)
				return false;
		} else if (!args.equals(other.args))
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
	@Column(name = "method_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "method_access")
	public String getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(String accessModifier) {
		this.accessModifier = accessModifier;
	}

	@Column(name = "is_static")
	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	@Column(name = "method_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "return_type")
	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	@ManyToOne
	@JoinColumn(name = "class_id")
	public Clazz getClassOwner() {
		return classOwner;
	}

	public void setClassOwner(Clazz classMethodOwner) {
		this.classOwner = classMethodOwner;
	}

	@Override
	public String toString() {
		return "Method [id=" + id + ", accessModifier=" + accessModifier + ", isStatic=" + isStatic + ", name=" + name
				+ ", returnType=" + returnType + ", args=" + args + "]";
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "method")
	public List<Argument> getArgs() {
		return args;
	}

	public void setArgs(List<Argument> args) {
		this.args = args;
	}

}
