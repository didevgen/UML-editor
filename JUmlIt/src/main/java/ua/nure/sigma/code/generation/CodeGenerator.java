package ua.nure.sigma.code.generation;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import ua.nure.sigma.code.model.Clazz;
import ua.nure.sigma.code.model.Field;
import ua.nure.sigma.code.model.Interface;
import ua.nure.sigma.code.model.Method;
import ua.nure.sigma.code.model.MethodArg;

public class CodeGenerator {

	public static final String MAIN_PACKAGE = "ua.nure.jumlit";

	private GeneratorService service = new GeneratorService();

	public TypeSpec generateClass(Clazz clazz) {
		Builder builder = TypeSpec.classBuilder(clazz.getName());
		for (String modifierName : clazz.getModifiers()) {
			builder.addModifiers(service.getModifier(modifierName));
		}
		for (Interface iFace : clazz.getInterfaces()) {
			ClassName interfaceName = ClassName.get(MAIN_PACKAGE, iFace.getName());
			builder.addSuperinterface(interfaceName);
		}
		for (Field field : clazz.getFields()) {
			builder.addField(generateField(field));
		}
		for (Method method : clazz.getMethods()) {
			builder.addMethod(generateMethod(method));
		}
		if (clazz.getSuperClass() != null) {
			builder.superclass(service.getTypeName(clazz.getSuperClass().getName()));
		}
		return builder.build();
	}

	public TypeSpec generateInterface(Interface iFace) {
		Builder interfaceBuilder = TypeSpec.interfaceBuilder(iFace.getName()).addModifiers(Modifier.PUBLIC);
		for (Field field : iFace.getFields()) {
			interfaceBuilder.addField(generateInterfaceField(field));
		}
		for (Method method : iFace.getMethods()) {
			interfaceBuilder.addMethod(generateIFaceMethod(method));
		}
		if (iFace.getSuperIface() != null) {
			interfaceBuilder.addSuperinterface(service.getTypeName(iFace.getSuperIface()));
		}
		return interfaceBuilder.build();
	}

	private MethodSpec generateIFaceMethod(Method method) {
		com.squareup.javapoet.MethodSpec.Builder builder = MethodSpec.methodBuilder(method.getName());
		for (String modif : method.getModifiers()) {
			builder.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
		}
		for (MethodArg arg : method.getArguments()) {
			builder.addParameter(prepareParam(arg));
		}
		builder.returns(service.getTypeName(method.getReturnType().getTypeName()));
		return builder.build();
	}

	private FieldSpec generateField(Field field) {
		try {
			com.squareup.javapoet.FieldSpec.Builder builder = FieldSpec
					.builder(service.getTypeName(field.getType().getTypeName()), field.getName());
			for (int i = 0; i < field.getModifiers().size(); i++) {
				builder.addModifiers(service.getModifier(field.getModifiers().get(i)));
			}
			return builder.build();
		} catch (Exception ex) {
			return FieldSpec.builder(TypeName.BOOLEAN, "ERROR", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
					.build();
		}
	}

	private FieldSpec generateInterfaceField(Field field) {
		try {
			com.squareup.javapoet.FieldSpec.Builder builder = FieldSpec.builder(
					service.getTypeName(field.getType().getTypeName()), field.getName().toUpperCase(), Modifier.PUBLIC,
					Modifier.STATIC, Modifier.FINAL);

			return builder.build();
		} catch (Exception ex) {
			return FieldSpec.builder(TypeName.BOOLEAN, "ERROR", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
					.build();
		}
	}

	private MethodSpec generateMethod(Method method) {
		com.squareup.javapoet.MethodSpec.Builder builder = MethodSpec.methodBuilder(method.getName());
		for (String modif : method.getModifiers()) {
			builder.addModifiers(service.getModifier(modif));
		}
		for (MethodArg arg : method.getArguments()) {
			builder.addParameter(prepareParam(arg));
		}
		builder.returns(service.getTypeName(method.getReturnType().getTypeName()));
		return builder.build();
	}

	private ParameterSpec prepareParam(MethodArg arg) {
		com.squareup.javapoet.ParameterSpec.Builder builder = ParameterSpec
				.builder(service.getTypeName(arg.getType().getTypeName()), arg.getName());
		for (String modif : arg.getModifiers()) {
			builder.addModifiers(service.getModifier(modif));
		}
		return builder.build();
	}

}
