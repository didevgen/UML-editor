package ua.nure.sigma.service;

import ua.nure.sigma.dao.ClassDiagramDAO;
import ua.nure.sigma.dao.impl.ClassDiagramDAOImpl;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.db_entities.diagram.Position;

public class ClassDiagramService {
	private ClassDiagramDAO dao = new ClassDiagramDAOImpl();

	public Clazz addClass(Clazz clazz) {
		Position pos = clazz.getPosition();
		pos.setClazz(clazz);
		Clazz cl = dao.insertClazz(clazz);
		return cl;
	}

	public void updateClass(Clazz clazz) {
		dao.updateClazz(clazz);
	}

	public void removeClass(long classId) {
		dao.removeClazz(classId);
	}

	public Clazz getClass(long classId) {
		return dao.getClazz(classId);
	}

	public Field addField(Field field) {
		return dao.insertField(field);
	}

	public void removeField(long fieldId) {
		dao.removeField(fieldId);
	}

	public void updateField(Field field) {
		dao.updateField(field);
	}

	public Method addMethod(Method method) {
		return dao.insertMethod(method);
	}

	public void removeMethod(long methodId) {
		dao.removeMethod(methodId);
	}

	public void updateMethod(Method method) {
		dao.updateMethod(method);
	}

}