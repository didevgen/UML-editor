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
import ua.nure.sigma.service.ClassDiagramService;
import ua.nure.sigma.service.DiagramService;
import ua.nure.sigma.service.HistoryService;
import ua.nure.sigma.util.UserAccessibility;

@RestController
public class DiagramClassController {
	private final HttpSession httpSession;
	
	private final SimpMessagingTemplate template;
	private final ClassDiagramService service;

	private final DiagramService diagramService;
	private final HistoryService historyService;

	@Autowired
	public DiagramClassController(HttpSession session, SimpMessagingTemplate template) {
		this.template = template;
		this.service = new ClassDiagramService();
		this.diagramService = new DiagramService();
		this.historyService = new HistoryService(this.template);
		this.httpSession = session;
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/add", method = RequestMethod.POST)
	public ResponseEntity<Clazz> addClass(@RequestBody Clazz clazz, @PathVariable long diagramId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Clazz>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		diagram.getClasses().add(clazz);
		Long myVal = (Long)(httpSession.getAttribute("sessionId"));
		if (myVal== null) {
			myVal = 1L;
		}
		historyService.insertHistory("added class: "+clazz.getName(),myVal);
		clazz.setDiagramOwner(diagram);
		return new ResponseEntity<Clazz>(service.addClass(clazz), HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/update", method = RequestMethod.POST)
	public ResponseEntity<Clazz> updateClass(@RequestBody Clazz clazz, @PathVariable long diagramId,
			Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Clazz>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		int index = diagram.getClasses().indexOf(clazz);
		diagram.getClasses().remove(index);
		diagram.getClasses().add(index, clazz);
		historyService.insertHistory("class updated: "+clazz.getName(),(Long)(httpSession.getAttribute("sessionId")));
		clazz.setDiagramOwner(diagram);
		service.updateClass(clazz);
		return new ResponseEntity<Clazz>(service.addClass(clazz), HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/classes/{classId}/remove", method = RequestMethod.POST)
	public ResponseEntity<Void> removeClass(@PathVariable long diagramId, @PathVariable long classId,
			Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		Clazz clazz = service.getClass(classId);
		historyService.insertHistory("class removed: "+clazz.getName(),(Long)(httpSession.getAttribute("sessionId")));
		service.removeClass(classId);
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
