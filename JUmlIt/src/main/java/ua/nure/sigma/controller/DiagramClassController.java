package ua.nure.sigma.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.service.ClassDiagramService;
import ua.nure.sigma.service.DiagramService;

@RestController
public class DiagramClassController {

	private ClassDiagramService service = new ClassDiagramService();
	
	private DiagramService diagramService = new DiagramService();

	@RequestMapping(value = "/diagram/{diagramId}/classes/add", method = RequestMethod.POST)
	public Clazz addClass(@RequestBody Clazz clazz, @PathVariable long diagramId) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		clazz.setDiagramOwner(diagram);
		return service.addClass(clazz);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/update", method = RequestMethod.POST)
	public Clazz updateClass(@RequestBody Clazz clazz, @PathVariable long diagramId) {
		service.updateClass(clazz);
		return service.getClass(clazz.getClassId());
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/remove", method = RequestMethod.POST)
	public void removeClass(@PathVariable long diagramId, @PathVariable long classId) {
		service.removeClass(classId);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}", method = RequestMethod.GET)
	public Clazz getClass(@PathVariable long diagramId, @PathVariable long classId) {
		return service.getClass(classId);
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/fields/add", method = RequestMethod.POST)
	public Clazz addField(@RequestBody Field field, @PathVariable long diagramId,@PathVariable long classId) {
		Clazz clazz = service.getClass(classId);
		field.setClassOwner(clazz);
		service.addField(field);
		clazz.getFields().add(field);
		return clazz;
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/methods/add", method = RequestMethod.POST)
	public Clazz addMethod(@RequestBody Method method, @PathVariable long diagramId,@PathVariable long classId) {
		System.out.println(method);
		Clazz clazz = service.getClass(classId);
		method.setClassOwner(clazz);
		method.getArguments().forEach(element->element.setMethod(method));
		service.addMethod(method);
		clazz.getMethods().add(method);
		return clazz;
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/fields/{fieldId}/remove", method = RequestMethod.POST)
	public Clazz removeField(@PathVariable long diagramId,@PathVariable long classId,@PathVariable long fieldId) {
		service.removeField(fieldId);
		return service.getClass(classId);
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/methods/{methodId}/remove", method = RequestMethod.POST)
	public Clazz removeMethod(@PathVariable long diagramId,@PathVariable long classId,@PathVariable long methodId) {
		service.removeMethod(methodId);
		return service.getClass(classId);
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/fields/update", method = RequestMethod.POST)
	public void updateField(@RequestBody Field field,@PathVariable long diagramId,@PathVariable long classId) {
		service.updateField(field);
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/methods/update", method = RequestMethod.POST)
	public void updateMethod(@RequestBody Method method,@PathVariable long diagramId,@PathVariable long classId) {
		service.updateMethod(method);
	}

}
