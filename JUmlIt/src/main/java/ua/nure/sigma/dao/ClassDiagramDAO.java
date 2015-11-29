package ua.nure.sigma.dao;

import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;

public interface ClassDiagramDAO {
	Clazz insertClazz(Clazz clazz);

	void updateClazz(Clazz clazz);

	void removeClazz(long clazzId);

	Clazz getClazz(long clazzId);

	Method insertMethod(Method method);

	void updateMethod(Method method);

	void removeMethod(long methodId);

	Method getMethod(long methodId);

	Field insertField(Field field);

	void updateField(Field field);

	void removeField(long fieldId);

	Field getField(long fieldId);
}
