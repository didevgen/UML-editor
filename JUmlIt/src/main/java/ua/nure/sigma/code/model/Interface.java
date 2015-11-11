package ua.nure.sigma.code.model;

import java.util.List;

public class Interface {
	private String name;
	
	private List<Field> fields;
	
	private List<Method> methods;
	
	

	public Interface() {
		super();
	}

	
	public Interface(String name) {
		super();
		this.name = name;
	}


	public Interface(String name, List<Field> fields, List<Method> methods) {
		super();
		this.name = name;
		this.fields = fields;
		this.methods = methods;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	@Override
	public String toString() {
		return "Interface [name=" + name + ", fields=" + fields + ", methods=" + methods + "]";
	}
	
	
}
