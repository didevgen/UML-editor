package ua.nure.sigma.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.diagram.Clazz;
import ua.nure.sigma.db_entities.diagram.Method;
import ua.nure.sigma.service.ClassDiagramService;
import ua.nure.sigma.service.DiagramService;
import ua.nure.sigma.service.HistoryService;
import ua.nure.sigma.util.UserAccessibility;

@RestController
public class MethodController {
	private final SimpMessagingTemplate template;
	private final ClassDiagramService service;

	private final DiagramService diagramService;
	private final HistoryService historyService;

	@Autowired
	public MethodController(SimpMessagingTemplate template) {
		this.template = template;
		this.service = new ClassDiagramService();
		this.diagramService = new DiagramService();
		this.historyService = new HistoryService(this.template);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/methods/add", method = RequestMethod.POST)
	public ResponseEntity<Method> addMethod(@RequestBody Method method, @PathVariable long diagramId,
			@PathVariable long classId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Method>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "method added");
		method.getArgs().forEach(item -> item.setMethod(method));
		Clazz clazz = service.getClass(classId);
		clazz.getMethods().add(method);
		method.setClassOwner(clazz);
		return new ResponseEntity<Method>(service.addMethod(method), HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/methods/update", method = RequestMethod.POST)
	public ResponseEntity<Method> updateMethod(@RequestBody Method method, @PathVariable long diagramId, @PathVariable long classId,
			Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Method>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "method updated");
		Clazz clazz = service.getClass(classId);
		method.setClassOwner(clazz);
		method.getArgs().forEach(item -> item.setMethod(method));
		service.updateMethod(method);
		return new ResponseEntity<Method>(method, HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/methods/{methodId}/remove", method = RequestMethod.POST)
	public ResponseEntity<Void> removeMethod(@PathVariable long diagramId, @PathVariable long classId,
			@PathVariable long methodId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "method removed");
		service.removeMethod(methodId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
