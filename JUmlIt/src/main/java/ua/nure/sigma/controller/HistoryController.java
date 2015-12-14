package ua.nure.sigma.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.db_entities.HistorySession;
import ua.nure.sigma.model.HistoryModel;
import ua.nure.sigma.service.HistoryService;
import ua.nure.sigma.util.UserAccessibility;

@RestController
public class HistoryController {
	HistoryService service = new HistoryService();
	
	@RequestMapping(value = "/diagram/{diagramId}/history", method = RequestMethod.POST)
	public ResponseEntity<List<HistoryModel>> getHistoryByDiagram(@PathVariable long diagramId, Principal principal) {
		if (!UserAccessibility.hasAccess(principal, diagramId)) {
			return new ResponseEntity<List<HistoryModel>>(HttpStatus.FORBIDDEN);
		}
		List<HistorySession> sessions = service.getSessionsByDiagram(diagramId);
		List<HistoryModel> models = new ArrayList<>();
		for (HistorySession session:sessions) {
			HistoryModel model = new HistoryModel(session.getUser().getFullname(),session.getDiagram().getName(),session.getTimeStart(),session.getTimeFinish());
			models.add(model);
		}
		return new ResponseEntity<List<HistoryModel>>(models,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{userId}/history", method = RequestMethod.POST)
	public ResponseEntity<List<HistoryModel>> getHistoryByUser(@PathVariable long userId, Principal principal) {
		List<HistorySession> sessions = service.getSessionByUser(userId);
		List<HistoryModel> models = new ArrayList<>();
		for (HistorySession session:sessions) {
			HistoryModel model = new HistoryModel(session.getUser().getFullname(),session.getDiagram().getName(),session.getTimeStart(),session.getTimeFinish());
			models.add(model);
		}
		return new ResponseEntity<List<HistoryModel>>(models,HttpStatus.OK);
	}

}
