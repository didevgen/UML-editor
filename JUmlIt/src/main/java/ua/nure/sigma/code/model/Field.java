package ua.nure.sigma.code.model;

public class Field {
	private String name;
	
	private String accessModifier;
	
	private Type type;
	
	private boolean isStatic;
	
	private boolean isFinal;
	
	

	public Field() {
		super();
	}
	
	public Field(String name, String accessModifier, Type type, boolean isStatic, boolean isFinal) {
		super();
		this.name = name;
		this.accessModifier = accessModifier;
		this.type = type;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccessModifier() {
		return accessModifier;
	}

	public void setAccessModifier(String accessModifier) {
		this.accessModifier = accessModifier;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
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
		Field other = (Field) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Field [name=" + name + ", accessModifier=" + accessModifier + ", type=" + type + ", isStatic="
				+ isStatic + ", isFinal=" + isFinal + "]";
	}
	
	
}
