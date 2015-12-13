package ua.nure.sigma.service;

import java.util.List;

import ua.nure.sigma.dao.ClassDiagramDAO;
import ua.nure.sigma.dao.impl.ClassDiagramDAOImpl;
import ua.nure.sigma.db_entities.diagram.Argument;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.db_entities.diagram.Position;
import ua.nure.sigma.db_entities.diagram.Relationship;

public class ClassDiagramService {
	private ClassDiagramDAO dao = new ClassDiagramDAOImpl();

	public Clazz addClass(Clazz clazz) {
		Position pos = clazz.getPosition();
		pos.setClazz(clazz);
		Clazz cl = dao.insertClazz(clazz);
		return cl;
	}

	public void updateClass(Clazz clazz) {
		Position pos = clazz.getPosition();
		pos.setClazz(clazz);
		clazz.getFields().forEach(item->item.setClassOwner(clazz));
		clazz.getMethods().forEach(item->item.setClassOwner(clazz));
		clazz.getPrimaryRelations().forEach(item->item.setPrimaryMember(clazz));
		clazz.getSecondaryRelations().forEach(item->item.setSecondaryMember(clazz));
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
		List<Argument> args = method.getArgs();
		Method m = dao.insertMethod(method);
		for (Argument arg : args) {
			arg.setMethod(m);
			dao.insertArgument(arg);
		}
		return m;
	}

	public void removeMethod(long methodId) {
		dao.removeMethod(methodId);
	}

	public void updateMethod(Method method) {
		List<Argument> args = method.getArgs();
		for (Argument arg : args) {
			arg.setMethod(method);
			dao.insertArgument(arg);
		}
		dao.updateMethod(method);
	}
	
	public Relationship addRelation(Relationship relationship) {
		return dao.insertRelationship(relationship);
	}

	public void removeRelation(long relationId) {
		dao.removeRelationship(relationId);
	}

	public void updateRelation(Relationship relationship) {
		dao.updateRelationship(relationship);
	}
	
	public Field getFieldById(long fieldId) {
		return dao.getField(fieldId);
	}
	public Method getMethodById(long methodId) {
		return dao.getMethod(methodId);
	}
	public Relationship getRelationById(long relationId) {
		return dao.getRelationship(relationId);
	}

}
