package ua.nure.sigma.code.model;

import java.util.ArrayList;
import java.util.List;

public class Interface {
	private String name;
	private String superIface;
	
	private List<Field> fields = new ArrayList<Field>();
	
	private List<Method> methods = new ArrayList<Method>();
	
	

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


	public String getSuperIface() {
		return superIface;
	}


	public void setSuperIface(String superIface) {
		this.superIface = superIface;
	}
	
	
}
