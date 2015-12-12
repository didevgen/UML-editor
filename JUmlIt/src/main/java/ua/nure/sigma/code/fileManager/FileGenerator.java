package ua.nure.sigma.code.fileManager;

import java.io.IOException;
import java.io.PrintWriter;

import com.squareup.javapoet.JavaFile;

import ua.nure.sigma.code.converter.Converter;
import ua.nure.sigma.code.generation.CodeGenerator;
import ua.nure.sigma.code.model.Clazz;
import ua.nure.sigma.code.model.Interface;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.service.DiagramService;

public class FileGenerator {
	DiagramService service = new DiagramService();
	public static void main(String[] args) {
		FileGenerator gen = new  FileGenerator();
		gen.generateSourceCode(5);
	}
	public void generateSourceCode(long diagramId) {
		Diagram diagram = service.getDiagramById(diagramId);
		for (ua.nure.sigma.db_entities.diagram.Clazz clazz : diagram.getClasses()) {
			generateFile(clazz.getName(), new Converter().diagramToClassModel(clazz));
		}
	}

	public void generateFile(String fileName, Object clazz) {
		if (clazz == null) {
			return;
		}

		CodeGenerator codegen = new CodeGenerator();
		JavaFile javaFile;
		if (clazz instanceof Clazz) {
			javaFile= JavaFile.builder("ua.nure.jumlit", codegen.generateClass((Clazz) clazz)).build();
		}
		else if (clazz instanceof Interface) {
			javaFile = JavaFile.builder("ua.nure.jumlit", codegen.generateInterface((Interface) clazz)).build();
		}
		else {
			System.out.println("FileGenerator#unknown instance of");
			return;
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName + ".java", "UTF-8");
			javaFile.writeTo(System.out);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
