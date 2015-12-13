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
import ua.nure.sigma.service.ClassDiagramService;
import ua.nure.sigma.service.DiagramService;
import ua.nure.sigma.service.DiagramUpdateService;
import ua.nure.sigma.service.HistoryService;
import ua.nure.sigma.util.UserAccessibility;

@RestController
public class DiagramClassController {

	private final SimpMessagingTemplate template;
	private final ClassDiagramService service;

	private final DiagramService diagramService;
	private final HistoryService historyService;
	private final DiagramUpdateService updateService;

	@Autowired
	public DiagramClassController(SimpMessagingTemplate template) {
		this.template = template;
		this.service = new ClassDiagramService();
		this.diagramService = new DiagramService();
		this.historyService = new HistoryService();
		this.updateService = new DiagramUpdateService(template);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/add", method = RequestMethod.POST)
	public ResponseEntity<Clazz> addClass(@RequestBody Clazz clazz, @PathVariable long diagramId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Clazz>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		diagram.getClasses().add(clazz);
		historyService.insertHistory(principal, diagram, "class added");
		clazz.setDiagramOwner(diagram);
		this.updateService.notify("/topic/diagram/" + diagram.getDiagramId() + "/clazz_added",
				clazz, principal);
		return new ResponseEntity<Clazz>(service.addClass(clazz), HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/update", method = RequestMethod.POST)
	public ResponseEntity<Clazz> updateClass(@RequestBody Clazz clazz, @PathVariable long diagramId,
			Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Clazz>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);

		historyService.insertHistory(principal, diagram, "class updated");
		clazz.setDiagramOwner(diagram);
		service.updateClass(clazz);
		this.updateService.notify("/topic/diagram/" + diagram.getDiagramId() + "/clazz_updated", clazz, principal);
		return new ResponseEntity<Clazz>(clazz, HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/remove", method = RequestMethod.POST)
	public ResponseEntity<Void> removeClass(@PathVariable long diagramId, @PathVariable long classId,
			Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "class removed");
		service.removeClass(classId);
		this.updateService.notify("/topic/diagram/" + diagram.getDiagramId() + "/clazz_removed", classId, principal);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}", method = RequestMethod.POST)
	public ResponseEntity<Clazz> getClass(@PathVariable long diagramId, @PathVariable long classId,
			Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Clazz>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Clazz>(service.getClass(classId), HttpStatus.OK);
	}

}
