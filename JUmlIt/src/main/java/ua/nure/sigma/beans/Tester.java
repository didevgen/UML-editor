package ua.nure.sigma.beans;

import java.util.Arrays;

import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.db_entities.diagram.Position;
import ua.nure.sigma.model.DiagramModel;
import ua.nure.sigma.service.ClassDiagramService;
import ua.nure.sigma.service.DiagramService;

public class Tester {

	private static ClassDiagramService service = new ClassDiagramService();
	
	private static DiagramService diagramService = new DiagramService();

	public static void main(String[] args) {
		long diagramId = 3;
		Clazz clazz = service.getClass(1);
		clazz.setAccessModifier("private");
		System.out.println(clazz.getFields().size());
		clazz.setName("myClazz");
		Position pos = new Position(13,20);
		Field field = new Field();
		field.setAccessModifier("public");
		field.setName("myName");
		field.setType("List");
		Method m = new Method();
		m.setAccessModifier("public");
		m.setName("myMethod");
		m.setReturnType("void");
		m.setClassOwner(clazz);
		field.setClassOwner(clazz);
		clazz.setMethods(Arrays.asList(m));
		clazz.setFields(Arrays.asList(field));
		pos.setClazz(clazz);
		clazz.setPosition(pos);
		DiagramModel diagram = diagramService.getDiagramById(diagramId);
		clazz.setDiagramOwner(diagram.getDiagram());
		
//		service.removeClass(1);
//		service.addClass(clazz);
	}
}
