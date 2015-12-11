package ua.nure.sigma.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.db_entities.diagram.Relationship;
import ua.nure.sigma.service.ClassDiagramService;
import ua.nure.sigma.service.DiagramService;
import ua.nure.sigma.service.HistoryService;

@RestController
public class DiagramClassController {

	private final SimpMessagingTemplate template;
	private final ClassDiagramService service;
	
	private final DiagramService diagramService;
	private final HistoryService historyService;

	@Autowired
	public DiagramClassController(SimpMessagingTemplate template) {
		this.template = template;
		this.service = new ClassDiagramService();
		this.diagramService = new DiagramService();
		this.historyService = new HistoryService(this.template);
	}


	@RequestMapping(value = "/diagram/{diagramId}/classes/add", method = RequestMethod.POST)
	public Clazz addClass(@RequestBody Clazz clazz, @PathVariable long diagramId, Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "class added");
		clazz.setDiagramOwner(diagram);
		return service.addClass(clazz);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/update", method = RequestMethod.POST)
	public Clazz updateClass(@RequestBody Clazz clazz, @PathVariable long diagramId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "class updated");
		clazz.setDiagramOwner(diagram);
		service.updateClass(clazz);
		return clazz;
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/remove", method = RequestMethod.POST)
	public void removeClass(@PathVariable long diagramId, @PathVariable long classId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "class removed");
		service.removeClass(classId);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}", method = RequestMethod.POST)
	public Clazz getClass(@PathVariable long diagramId, @PathVariable long classId,Principal principal) {
		return service.getClass(classId);
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/fields/add", method = RequestMethod.POST)
	public Field addField(@RequestBody Field field, @PathVariable long diagramId,@PathVariable long classId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "field added");
		Clazz clazz = service.getClass(classId);
		field.setClassOwner(clazz);
		clazz.getFields().add(field);
		return service.addField(field);
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/methods/add", method = RequestMethod.POST)
	public Method addMethod(@RequestBody Method method, @PathVariable long diagramId,@PathVariable long classId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "method added");
		Clazz clazz = service.getClass(classId);
		clazz.getMethods().add(method);
		method.setClassOwner(clazz);
		return service.addMethod(method);
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/fields/{fieldId}/remove", method = RequestMethod.POST)
	public void removeField(@PathVariable long diagramId,@PathVariable long classId,@PathVariable long fieldId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "field removed");
		service.removeField(fieldId);
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/methods/{methodId}/remove", method = RequestMethod.POST)
	public void removeMethod(@PathVariable long diagramId,@PathVariable long classId,@PathVariable long methodId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "method removed");
		service.removeMethod(methodId);
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/fields/update", method = RequestMethod.POST)
	public Field updateField(@RequestBody Field field,@PathVariable long diagramId,@PathVariable long classId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "field updated");
		Clazz clazz = service.getClass(classId);
		field.setClassOwner(clazz);
		service.updateField(field);
		return field;
	}
	
	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/methods/update", method = RequestMethod.POST)
	public Method updateMethod(@RequestBody Method method,@PathVariable long diagramId,@PathVariable long classId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "method updated");
		Clazz clazz = service.getClass(classId);
		method.setClassOwner(clazz);
		service.updateMethod(method);
		return method;
	}
	
	
	@RequestMapping(value = "/diagram/{diagramId}/relationships/add", method = RequestMethod.POST)
	public Relationship addRelationship(@RequestBody Relationship relationship, @PathVariable long diagramId, Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		relationship.setDiagram(diagram);
		System.out.println(relationship);
		historyService.insertHistory(principal, diagram, "relationship added");
		return service.addRelation(relationship);
	}

	@RequestMapping(value = "/diagram/{diagramId}/relationships/update", method = RequestMethod.POST)
	public void updateRelationship(@RequestBody Relationship relationship, @PathVariable long diagramId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		System.out.println(relationship);
		historyService.insertHistory(principal, diagram, "relationship updated");
		service.updateRelation(relationship);
	}

	@RequestMapping(value = "/diagram/{diagramId}/relationships/{relationId}/remove", method = RequestMethod.POST)
	public void removeRelationship(@PathVariable long diagramId, @PathVariable long relationId,Principal principal) {
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "relationship removed");
		service.removeRelation(relationId);
	}

}
