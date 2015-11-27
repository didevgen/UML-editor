package ua.nure.sigma.code.model;

import java.util.ArrayList;
import java.util.List;

public class Method {
	private String name;
	
	private List<String> modifiers = new ArrayList<String>();
	
	private Type returnType;
	
	private List<MethodArg> arguments = new ArrayList<MethodArg>();
	
	

	public Method(String name, List<String> modifiers, Type returnType, List<MethodArg> arguments) {
		super();
		this.name = name;
		this.modifiers = modifiers;
		this.returnType = returnType;
		this.arguments = arguments;
	}


	public Method() {
		super();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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



	public List<String> getModifiers() {
		return modifiers;
	}



	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}
	
	
	
}
