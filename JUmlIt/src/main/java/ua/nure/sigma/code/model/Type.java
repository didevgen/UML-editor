package ua.nure.sigma.code.model;

public class Type {
	private String typeName;

	private boolean isArray;
	
	public Type() {
		super();
	}

	public Type(String typeName, boolean isArray) {
		super();
		this.typeName = typeName;
		this.isArray = isArray;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean isArray() {
		return isArray;
	}

	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}

	@Override
	public String toString() {
		return "Type [typeName=" + typeName + ", isArray=" + isArray + "]";
	}
	
}
