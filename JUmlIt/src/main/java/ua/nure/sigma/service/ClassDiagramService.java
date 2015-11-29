package ua.nure.sigma.service;

import ua.nure.sigma.dao.ClassDiagramDAO;
import ua.nure.sigma.dao.impl.ClassDiagramDAOImpl;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;

public class ClassDiagramService {
	private ClassDiagramDAO dao = new ClassDiagramDAOImpl();

	public Clazz addClass(Clazz clazz) {
		return null;
	}

	public void updateClass(Clazz clazz) {
	}

	public void removeClass(long classId) {
	}

	public void getClass(long classId) {
	}

	public Field addField(Field field) {
		return null;
	}

	public void removeField(long fieldId) {
	}

	public void updateField(Field field) {

	}

	public Method addMethod(Method method) {
		return null;
	}

	public void removeMethod(long methodId) {
	}

	public void updateMethod(Method method) {

	}

}
