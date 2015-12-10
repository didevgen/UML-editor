package ua.nure.sigma.beans;

import java.io.IOException;
import java.util.List;

import com.squareup.javapoet.JavaFile;

import ua.nure.sigma.code.converter.Converter;
import ua.nure.sigma.code.generation.CodeGenerator;
import ua.nure.sigma.dao.impl.DiagramDAOImpl;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.diagram.Clazz;

public class Tester {

	public static void main(String[] args) {
		Diagram d = new DiagramDAOImpl().getDiagramById(5);
		List<Clazz> classes = d.getClasses();
		Converter conv = new Converter();
		for (Clazz clazz : classes) {
			ua.nure.sigma.code.model.Clazz cl = conv.diagramToClassModel(clazz);
			CodeGenerator codegen = new CodeGenerator();
			JavaFile javaFile = JavaFile.builder("com.example.helloworld", codegen.generateClass(cl)).build();
			try {
				javaFile.writeTo(System.out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
