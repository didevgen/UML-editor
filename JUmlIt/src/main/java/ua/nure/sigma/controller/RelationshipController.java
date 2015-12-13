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
import ua.nure.sigma.db_entities.diagram.Relationship;
import ua.nure.sigma.service.ClassDiagramService;
import ua.nure.sigma.service.DiagramService;
import ua.nure.sigma.service.HistoryService;
import ua.nure.sigma.util.UserAccessibility;

@RestController
public class RelationshipController {
	private final SimpMessagingTemplate template;
	private final ClassDiagramService service;

	private final DiagramService diagramService;
	private final HistoryService historyService;

	@Autowired
	public RelationshipController(SimpMessagingTemplate template) {
		this.template = template;
		this.service = new ClassDiagramService();
		this.diagramService = new DiagramService();
		this.historyService = new HistoryService(this.template);
	}

	@RequestMapping(value = "/diagram/{diagramId}/relationships/add", method = RequestMethod.POST)
	public ResponseEntity<Relationship> addRelationship(@RequestBody Relationship relationship,
			@PathVariable long diagramId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Relationship>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		relationship.setDiagram(diagram);
		historyService.insertHistory(principal, diagram, "relationship added");
		return new ResponseEntity<Relationship>(service.addRelation(relationship), HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/relationships/update", method = RequestMethod.POST)
	public ResponseEntity<Void> updateRelationship(@RequestBody Relationship relationship, @PathVariable long diagramId,
			Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "relationship updated");
		relationship.setDiagram(diagram);
		service.updateRelation(relationship);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{diagramId}/relationships/{relationId}/remove", method = RequestMethod.POST)
	public ResponseEntity<Void> removeRelationship(@PathVariable long diagramId, @PathVariable long relationId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(diagramId);
		historyService.insertHistory(principal, diagram, "relationship removed");
		service.removeRelation(relationId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
