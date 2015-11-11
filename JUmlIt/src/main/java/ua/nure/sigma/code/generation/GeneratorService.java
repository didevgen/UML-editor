package ua.nure.sigma.code.generation;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

public class GeneratorService {
	public Modifier getModifier(String modifier) {
		return Modifier.valueOf(modifier.toUpperCase());
	}
	
	public TypeName getTypeName(String type) {
		String currType = type.toLowerCase();
		if (currType.equals("byte")) {
			return TypeName.BYTE;
		}
		else if (currType.equals("short")) {
			return TypeName.SHORT;
		}
		else if (currType.equals("int")) {
			return TypeName.INT;
		}
		else if (currType.equals("long")) {
			return TypeName.LONG;
		}
		else if (currType.equals("char")) {
			return TypeName.CHAR;
		}
		else if (currType.equals("float")) {
			return TypeName.FLOAT;
		}
		else if (currType.equals("double")) {
			return TypeName.DOUBLE;
		}
		else if (currType.equals("boolean")) {
			return TypeName.BOOLEAN;
		}
		else if (currType.equals("void")) {
			return TypeName.VOID;
		}
		else if (currType.equals("object")) {
			return TypeName.OBJECT;
		}
		else if (currType.equals("string")) {
			ClassName interfaceName = ClassName.get("", "String");
			return interfaceName;
		}
		else {
			ClassName interfaceName = ClassName.get("", type);
			return interfaceName;
		}
	}
}
