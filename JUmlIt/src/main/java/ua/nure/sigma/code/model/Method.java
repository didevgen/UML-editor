package ua.nure.sigma.code.model;

import java.util.List;

public class Method {
	private String name;
	
	private String accessModifier;
	
	private boolean isAbstract;
	
	private boolean isStatic;
	
	private boolean isFinal;
	
	private Type returnType;
	
	private List<MethodArg> arguments;
	
	

	public Method() {
		super();
	}
	
	

	public Method(String name, String accessModifier, boolean isAbstract, boolean isStatic, boolean isFinal,
			Type returnType) {
		super();
		this.name = name;
		this.accessModifier = accessModifier;
		this.isAbstract = isAbstract;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.returnType = returnType;
	}



	public Method(String name, String accessModifier, boolean isAbstract, boolean isStatic, boolean isFinal,
			Type returnType, List<MethodArg> arguments) {
		super();
		this.name = name;
		this.accessModifier = accessModifier;
		this.isAbstract = isAbstract;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.returnType = returnType;
		this.arguments = arguments;
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

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
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

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	public List<MethodArg> getArguments() {
		return arguments;
	}

	public void setArguments(List<MethodArg> arguments) {
		this.arguments = arguments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arguments == null) ? 0 : arguments.hashCode());
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
		if (arguments == null) {
			if (other.arguments != null)
				return false;
		} else if (!arguments.equals(other.arguments))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
