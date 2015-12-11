package ua.nure.sigma.code.converter;

import java.util.ArrayList;
import java.util.List;

import ua.nure.sigma.code.model.MethodArg;
import ua.nure.sigma.code.model.Type;
import ua.nure.sigma.db_entities.diagram.Argument;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;

public class Converter {
	public ua.nure.sigma.code.model.Clazz diagramToClassModel(Clazz clazz) {
		ua.nure.sigma.code.model.Clazz resultClass = new ua.nure.sigma.code.model.Clazz();
		resultClass.setName(clazz.getName());
		resultClass.setModifiers(getModifiersList(clazz));
		resultClass.setFields(mapFields(clazz.getFields()));
		resultClass.setMethods(mapMethods(clazz.getMethods()));
		return resultClass;
	}

	private List<ua.nure.sigma.code.model.Field> mapFields(List<Field> fields) {
		List<ua.nure.sigma.code.model.Field> result = new ArrayList<>();
		for (Field field : fields) {
			ua.nure.sigma.code.model.Field modelField = new ua.nure.sigma.code.model.Field();
			modelField.setModifiers(getModifiersList(field));
			modelField.setName(field.getName());
			modelField
					.setType(new Type(field.getType(), field.getType().contains("[") && field.getType().contains("]")));
			result.add(modelField);
		}
		return result;
	}

	private List<ua.nure.sigma.code.model.Method> mapMethods(List<Method> methods) {
		List<ua.nure.sigma.code.model.Method> result = new ArrayList<>();
		for (Method method : methods) {
			ua.nure.sigma.code.model.Method modelMethod = new ua.nure.sigma.code.model.Method();
			modelMethod.setModifiers(getModifiersList(method));
			modelMethod.setName(method.getName());
			modelMethod.setReturnType(new Type(method.getReturnType(),
					method.getReturnType().contains("[") && method.getReturnType().contains("]")));
			modelMethod.setArguments(getArguments(method));
			result.add(modelMethod);
		}
		return result;
	}

	private List<String> getModifiersList(Clazz clazz) {
		List<String> result = new ArrayList<>();
		result.add(clazz.getAccessModifier());
		if (clazz.isStatic()) {
			result.add("static");
		}
		return result;
	}

	private List<String> getModifiersList(Field field) {
		List<String> result = new ArrayList<>();
		result.add(field.getAccessModifier());
		if (field.isStatic()) {
			result.add("static");
		}
		return result;
	}

	private List<String> getModifiersList(Method method) {
		List<String> result = new ArrayList<>();
		result.add(method.getAccessModifier());
		if (method.isStatic()) {
			result.add("static");
		}
		return result;
	}

	private List<MethodArg> getArguments(Method method) {
		List<MethodArg> result = new ArrayList<>();
		for (Argument arg : method.getArguments()) {
			MethodArg argument = new MethodArg();
			argument.setName(arg.getName());
			argument.setType(new Type(method.getReturnType(),
					method.getReturnType().contains("[") && method.getReturnType().contains("]")));
			result.add(argument);
		}
		return result;
	}
}
