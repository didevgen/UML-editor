package ua.nure.sigma.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

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
import ua.nure.sigma.db_entities.diagram.Field;
import ua.nure.sigma.service.ClassDiagramService;
import ua.nure.sigma.service.DiagramService;
import ua.nure.sigma.service.HistoryService;
import ua.nure.sigma.util.UserAccessibility;

@RestController
public class FieldController {
	private final SimpMessagingTemplate template;
	private final ClassDiagramService service;
	private final HttpSession httpSession;
	private final DiagramService diagramService;
	private final HistoryService historyService;

	@Autowired
	public FieldController(HttpSession session, SimpMessagingTemplate template) {
		this.httpSession = session;
		this.template = template;
		this.service = new ClassDiagramService();
		this.diagramService = new DiagramService();
		this.historyService = new HistoryService(this.template);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/fields/add", method = RequestMethod.POST)
	public ResponseEntity<Field> addField(@RequestBody Field field, @PathVariable long diagramId,
			@PathVariable long classId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Field>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory("field added: " + field.getName(), (Long) (httpSession.getAttribute("sessionId")));
		Clazz clazz = service.getClass(classId);
		field.setClassOwner(clazz);
		clazz.getFields().add(field);
		return new ResponseEntity<Field>(service.addField(field), HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/fields/{fieldId}/remove", method = RequestMethod.POST)
	public ResponseEntity<Void> removeField(@PathVariable long diagramId, @PathVariable long classId,
			@PathVariable long fieldId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		Field field = service.getFieldById(fieldId);
		historyService.insertHistory("field removed: " + field.getName(), (Long) (httpSession.getAttribute("sessionId")));
		service.removeField(fieldId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/fields/update", method = RequestMethod.POST)
	public ResponseEntity<Field> updateField(@RequestBody Field field, @PathVariable long diagramId,
			@PathVariable long classId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Field>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory("field updated: " + field.getName(), (Long) (httpSession.getAttribute("sessionId")));
		Clazz clazz = service.getClass(classId);
		field.setClassOwner(clazz);
		service.updateField(field);
		return new ResponseEntity<Field>(field, HttpStatus.OK);
	}
}
