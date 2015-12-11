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
		
	}
}
