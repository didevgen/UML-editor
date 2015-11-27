package ua.nure.sigma.code.model;

import java.util.ArrayList;
import java.util.List;

public class Clazz {

	private String name;
	
	private List<String> modifiers = new ArrayList<String>();
	
	private Clazz superClass;
	
	private List<Field> fields =  new ArrayList<Field>();
	
	private List<Interface> interfaces =  new ArrayList<Interface>();
	
	private List<Method> methods =  new ArrayList<Method>();
	

	public Clazz() {
	}
	
	

	public Clazz(String name, List<String> modifiers, Clazz superClass, List<Field> fields, List<Interface> interfaces,
			List<Method> methods) {
		super();
		this.name = name;
		this.modifiers = modifiers;
		this.superClass = superClass;
		this.fields = fields;
		this.interfaces = interfaces;
		this.methods = methods;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Clazz getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Clazz superClass) {
		this.superClass = superClass;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Interface> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
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



	public List<String> getModifiers() {
		return modifiers;
	}



	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}
	
	
}
