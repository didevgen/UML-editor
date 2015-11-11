package ua.nure.sigma.code.model;

import java.util.List;

public class MethodArg {
	
	private String name;

	private Type type;

	private List<String> modifiers;
	
	public MethodArg() {
		super();
	}


	public MethodArg(String name, Type type, List<String> modifiers) {
		super();
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
		MethodArg other = (MethodArg) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public List<String> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}
	
}
