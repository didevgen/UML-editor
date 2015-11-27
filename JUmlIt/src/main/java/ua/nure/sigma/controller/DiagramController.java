package ua.nure.sigma.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.exceptions.DiagramException;
import ua.nure.sigma.model.DiagramModel;
import ua.nure.sigma.service.AccountService;
import ua.nure.sigma.service.DiagramService;

@RestController
public class DiagramController {

	@Autowired
	private HttpSession session;

	private DiagramService diagramService = new DiagramService();
	private AccountService accountService = new AccountService();

	@RequestMapping(value = "/diagram/create", method = RequestMethod.POST)
	public DiagramModel registerUser(@RequestBody DiagramModel diagram, ModelMap model, Principal principal) {
		diagram.setOwner(accountService.getUserByLogin(principal.getName()));
		DiagramModel diagramModel = diagramService.createDiagram(diagram);
		if (diagramModel == null) {
			throw new DiagramException("Cannot create diagram");
		}
		return diagramModel;
	}

	@RequestMapping(value = "/diagram/{id}", method = RequestMethod.GET)
	public DiagramModel getUserId(@PathVariable long id) {
		DiagramModel model = diagramService.getDiagramById(id);
		if (model == null) {
			throw new DiagramException("Cannot retrieve diagram with id " + id);
		}
		return model;
	}

	@RequestMapping(value = "/diagram/update", method = RequestMethod.POST)
	public void updateDiagram(@RequestBody DiagramModel diagram) {
		diagramService.updateDiagram(diagram);
	}

	@RequestMapping(value = "/diagram/{id}/remove", method = RequestMethod.POST)
	public void updateDiagram(@PathVariable long id) {
		diagramService.deleteDiagram(id);
	}
}
