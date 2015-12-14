package ua.nure.sigma.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.exceptions.DiagramException;
import ua.nure.sigma.service.AccountService;
import ua.nure.sigma.service.DiagramService;
import ua.nure.sigma.service.HistoryService;
import ua.nure.sigma.util.UserAccessibility;

@RestController
public class DiagramController {

	private final HttpSession httpSession;
	private final SimpMessagingTemplate template;
	private final HistoryService historyService;
	private final DiagramService diagramService;
	private final AccountService accountService;

	@Autowired
	public DiagramController(final HttpSession session, final SimpMessagingTemplate template) {
		this.httpSession = session;
		this.template = template;
		this.historyService = new HistoryService();
		this.accountService = new AccountService();
		this.diagramService = new DiagramService();
	}

	@RequestMapping(value = "/diagram/create", method = RequestMethod.POST)
	public Diagram insertDiagram(@RequestBody Diagram diagram, ModelMap model, Principal principal) {
		User user = accountService.getUserByLogin(principal.getName());
		diagram.setOwner(user);
		Diagram diagramModel = diagramService.createDiagram(diagram);
		if (diagramModel == null) {
			throw new DiagramException("Cannot create diagram");
		}
		return diagramModel;
	}

	@RequestMapping(value = "/diagram/{id}", method = RequestMethod.GET)
	public ResponseEntity<Diagram> getDiagramById(@PathVariable long id, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, id)) {
			return new ResponseEntity<Diagram>(HttpStatus.FORBIDDEN);
		}
		Diagram model = diagramService.getDiagramById(id);
		if (model == null) {
			throw new DiagramException("Cannot retrieve diagram with id " + id);
		}
		return new ResponseEntity<Diagram>(model, HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/update", method = RequestMethod.POST)
	public ResponseEntity<Diagram> updateDiagram(@RequestBody Diagram diagram, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagram.getDiagramId())) {
			return new ResponseEntity<Diagram>(HttpStatus.FORBIDDEN);
		}
		diagramService.updateDiagram(diagram);
		Diagram newDiagram = diagramService.getDiagramById(diagram.getDiagramId());
		return new ResponseEntity<Diagram>(newDiagram, HttpStatus.OK);
	}

	@RequestMapping(value = "/diagram/{id}/remove", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteDiagram(@PathVariable long id, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, id)) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		Diagram diagram = diagramService.getDiagramById(id);
		diagramService.deleteDiagram(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
