package ua.nure.sigma.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.service.ClassDiagramService;

public class DiagramClassController {
	@Autowired
	private HttpSession session;

	private ClassDiagramService service = new ClassDiagramService();

	@RequestMapping(value = "/diagram/{id}/classes/add", method = RequestMethod.POST)
	public Clazz addClass(@RequestBody Clazz clazz, @PathVariable long diagramId) {
		return service.addClass(clazz);
	}

	@RequestMapping(value = "/diagram/{id}/classes/update", method = RequestMethod.POST)
	public void updateClass(@RequestBody Clazz clazz, @PathVariable long diagramId) {
		service.updateClass(clazz);
	}

	@RequestMapping(value = "/diagram/{id}/classes/{id}/remove", method = RequestMethod.POST)
	public void removeClass(@PathVariable long diagramId, @PathVariable long classId) {
		service.removeClass(classId);
	}

	@RequestMapping(value = "/diagram/{id}/classes/{id}", method = RequestMethod.POST)
	public void getClass(@PathVariable long diagramId, @PathVariable long classId) {
		service.getClass(classId);
	}
	
	@RequestMapping(value = "/diagram/{id}/classes/{id}/fields/add", method = RequestMethod.POST)
	public Field addField(@RequestBody Field field, @PathVariable long diagramId,@PathVariable long classId) {
		return service.addField(field);
	}
	
	@RequestMapping(value = "/diagram/{id}/classes/{id}/methods/add", method = RequestMethod.POST)
	public Method addMethod(@RequestBody Method method, @PathVariable long diagramId,@PathVariable long classId) {
		return service.addMethod(method);
	}
	
	@RequestMapping(value = "/diagram/{id}/classes/{id}/fields/{id}/remove", method = RequestMethod.POST)
	public void removeField(@PathVariable long diagramId,@PathVariable long classId,@PathVariable long fieldId) {
		service.removeField(fieldId);
	}
	
	@RequestMapping(value = "/diagram/{id}/classes/{id}/methods/{id}/remove", method = RequestMethod.POST)
	public void removeMethod(@PathVariable long diagramId,@PathVariable long classId,@PathVariable long methodId) {
		service.removeMethod(methodId);
	}
	
	@RequestMapping(value = "/diagram/{id}/classes/{id}/fields/update", method = RequestMethod.POST)
	public void updateField(@RequestBody Field field,@PathVariable long diagramId,@PathVariable long classId) {
		service.updateField(field);
	}
	
	@RequestMapping(value = "/diagram/{id}/classes/{id}/methods/update", method = RequestMethod.POST)
	public void updateMethod(@RequestBody Method method,@PathVariable long diagramId,@PathVariable long classId) {
		service.updateMethod(method);
	}

}
