package ua.nure.sigma.code.fileManager;

import java.io.IOException;
import java.io.PrintWriter;

import com.squareup.javapoet.JavaFile;

import ua.nure.sigma.code.converter.Converter;
import ua.nure.sigma.code.generation.CodeGenerator;
import ua.nure.sigma.code.model.Clazz;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.service.DiagramService;

public class FileGenerator {
	DiagramService service = new DiagramService();
	
	public void generateSourceCode(long diagramId) {
		Diagram diagram = service.getDiagramById(diagramId);
		for (ua.nure.sigma.db_entities.diagram.Clazz clazz : diagram.getClasses()) {
			generateFile(clazz.getName(),new Converter().diagramToClassModel(clazz));
		}
	}
	
	public void generateFile(String fileName, Clazz clazz) {
		CodeGenerator codegen = new CodeGenerator();
		JavaFile javaFile = JavaFile.builder("com.example.helloworld", codegen.generateClass(clazz)).build();
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName+".java", "UTF-8");
			javaFile.writeTo(writer);
			writer.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		
	}
}
