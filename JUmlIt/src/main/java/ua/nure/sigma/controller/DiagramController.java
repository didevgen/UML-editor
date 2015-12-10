package ua.nure.sigma.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class DiagramController {

	@Autowired
	private HttpSession session;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	private HistoryService historyService = new HistoryService();
	private DiagramService diagramService = new DiagramService();
	private AccountService accountService = new AccountService();

	@RequestMapping(value = "/diagram/create", method = RequestMethod.POST)
	public Diagram insertDiagram(@RequestBody Diagram diagram, ModelMap model, Principal principal) {
		User user = accountService.getUserByLogin(principal.getName());
		diagram.setOwner(user);
		Diagram diagramModel = diagramService.createDiagram(diagram);
		historyService.insertHistory(principal, diagramModel, "inserted diagram");
		if (diagramModel == null) {
			throw new DiagramException("Cannot create diagram");
		}
		return diagramModel;
	}

	@RequestMapping(value = "/diagram/{id}", method = RequestMethod.GET)
	public Diagram getDiagramById(@PathVariable long id) {
		Diagram model = diagramService.getDiagramById(id);
		if (model == null) {
			throw new DiagramException("Cannot retrieve diagram with id " + id);
		}
		return model;
	}

	@RequestMapping(value = "/diagram/update", method = RequestMethod.POST)
	public Diagram updateDiagram(@RequestBody Diagram diagram, Principal principal) {
		diagramService.updateDiagram(diagram);
		Diagram newDiagram = diagramService.getDiagramById(diagram.getDiagramId());
		historyService.insertHistory(principal, newDiagram, "updated diagram");
		messagingTemplate.convertAndSend("/diagram/external", newDiagram);
		return newDiagram;
	}

	@RequestMapping(value = "/diagram/{id}/remove", method = RequestMethod.POST)
	public void updateDiagram(@PathVariable long id) {
		diagramService.deleteDiagram(id);
	}
}
