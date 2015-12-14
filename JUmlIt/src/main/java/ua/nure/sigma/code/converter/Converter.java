package ua.nure.sigma.code.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.nure.sigma.code.model.Interface;
import ua.nure.sigma.code.model.MethodArg;
import ua.nure.sigma.code.model.Type;
import ua.nure.sigma.db_entities.diagram.Argument;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.db_entities.diagram.Relationship;

public class Converter {
	public Object diagramToClassModel(Clazz clazz) {
		if (clazz.getClassType().intern() == "Class".intern()) {
			return genClazz(clazz);
		} else if (clazz.getClassType().intern() == "Abstract class".intern()) {
			return genClazz(clazz);
		} else if (clazz.getClassType().intern() == "Interface".intern()) {
			return genInterface(clazz);
		} else {
			return null;
		}
	}

	private ua.nure.sigma.code.model.Clazz genClazz(Clazz clazz) {
		ua.nure.sigma.code.model.Clazz resultClass = new ua.nure.sigma.code.model.Clazz();
		resultClass.setName(clazz.getName());
		resultClass.setModifiers(getModifiersList(clazz));
		resultClass.setFields(mapFields(clazz.getFields()));
		resultClass.setMethods(mapMethods(clazz.getMethods()));
		setSuperInterfaces(resultClass, clazz);
		setCompAndAggregationItems(resultClass, clazz);
		setSuperClass(resultClass, clazz);
		return resultClass;
	}

	private ua.nure.sigma.code.model.Interface genInterface(Clazz clazz) {
		ua.nure.sigma.code.model.Interface resultClass = new ua.nure.sigma.code.model.Interface();
		resultClass.setName(clazz.getName());
		resultClass.setFields(mapFields(clazz.getFields()));
		resultClass.setMethods(mapMethods(clazz.getMethods()));
		setCompAndAggregationItems(resultClass, clazz);
		setSuperClass(resultClass, clazz);
		return resultClass;
	}
	private ua.nure.sigma.code.model.Interface setCompAndAggregationItems(ua.nure.sigma.code.model.Interface resultClazz,
			Clazz startClazz) {
		List<Relationship> relations = startClazz.getSecondaryRelations();
		for (Relationship relation : relations) {
			if (relation.getType().equalsIgnoreCase("aggregation")
					|| relation.getType().equalsIgnoreCase("composition")) {
				ua.nure.sigma.code.model.Field field = new ua.nure.sigma.code.model.Field();
				field.setName(relation.getPrimaryMember().getName().toLowerCase());
				field.setModifiers(Arrays.asList("private"));
				field.setType(new Type(relation.getPrimaryMember().getName(), false));
				resultClazz.getFields().add(field);
			}
		}
		return resultClazz;
	}

	private ua.nure.sigma.code.model.Interface setSuperClass(ua.nure.sigma.code.model.Interface resultClazz, Clazz startClazz) {
		List<Relationship> relations = startClazz.getPrimaryRelations();
		for (Relationship relation : relations) {
			if (relation.getType().equalsIgnoreCase("generalization") && checkExtensibility(relation)) {
				ua.nure.sigma.code.model.Interface superClass = new ua.nure.sigma.code.model.Interface();
				superClass.setName(relation.getSecondaryMember().getName());
				resultClazz.setSuperIface(relation.getSecondaryMember().getName());
			}
		}
		return resultClazz;
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
		if (clazz.getClassType().intern() == "Abstract class") {
			result.add("abstract");
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
		if (method.getClassOwner().getClassType().intern() == "Abstract class".intern()) {
			result.add("abstract");
		}
		return result;
	}

	private List<MethodArg> getArguments(Method method) {
		List<MethodArg> result = new ArrayList<>();
		for (Argument arg : method.getArgs()) {
			MethodArg argument = new MethodArg();
			argument.setName(arg.getName());
			argument.setType(new Type(arg.getType(), arg.getType().contains("[") && arg.getType().contains("]")));
			result.add(argument);
		}
		return result;
	}

	private ua.nure.sigma.code.model.Clazz setCompAndAggregationItems(ua.nure.sigma.code.model.Clazz resultClazz,
			Clazz startClazz) {
		List<Relationship> relations = startClazz.getSecondaryRelations();
		for (Relationship relation : relations) {
			if (relation.getType().equalsIgnoreCase("aggregation")
					|| relation.getType().equalsIgnoreCase("composition")) {
				ua.nure.sigma.code.model.Field field = new ua.nure.sigma.code.model.Field();
				field.setName(relation.getPrimaryMember().getName().toLowerCase());
				field.setModifiers(Arrays.asList("private"));
				field.setType(new Type(relation.getPrimaryMember().getName(), false));
				resultClazz.getFields().add(field);
			}
		}
		return resultClazz;
	}

	private ua.nure.sigma.code.model.Clazz setSuperClass(ua.nure.sigma.code.model.Clazz resultClazz, Clazz startClazz) {
		List<Relationship> relations = startClazz.getPrimaryRelations();
		for (Relationship relation : relations) {
			if (relation.getType().equalsIgnoreCase("generalization") && checkExtensibility(relation)) {
				ua.nure.sigma.code.model.Clazz superClass = new ua.nure.sigma.code.model.Clazz();
				superClass.setName(relation.getSecondaryMember().getName());
				resultClazz.setSuperClass(superClass);
			}
		}
		return resultClazz;
	}

	private boolean checkExtensibility(Relationship relation) {
		if (relation.getPrimaryMember().getClassType().equalsIgnoreCase(relation.getSecondaryMember().getClassType())) {
			return true;
		} else if (relation.getPrimaryMember().getClassType().equalsIgnoreCase("abstract class")
				&& relation.getSecondaryMember().getClassType().equalsIgnoreCase("class")) {
			return true;
		} else if (relation.getPrimaryMember().getClassType().equalsIgnoreCase("class")
				&& relation.getSecondaryMember().getClassType().equalsIgnoreCase("abstract class")) {
			return true;
		}
		return false;
	}

	private ua.nure.sigma.code.model.Clazz setSuperInterfaces(ua.nure.sigma.code.model.Clazz resultClazz,
			Clazz startClazz) {
		List<Relationship> relations = startClazz.getPrimaryRelations();
		for (Relationship relation : relations) {
			if (relation.getType().equalsIgnoreCase("realization") && checkRealizationable(relation)) {
				Interface iface = new Interface(relation.getSecondaryMember().getName());
				resultClazz.getInterfaces().add(iface);
			}
		}
		return resultClazz;
	}

	private boolean checkRealizationable(Relationship relation) {
		return !(relation.getPrimaryMember().getClassType().equalsIgnoreCase("interface")) 
				&& relation.getSecondaryMember().getClassType().equalsIgnoreCase("interface");
	}
}
