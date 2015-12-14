package ua.nure.sigma.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import ua.nure.sigma.db_entities.Comment;
import ua.nure.sigma.db_entities.Diagram;
import ua.nure.sigma.db_entities.User;
import ua.nure.sigma.service.AccountService;
import ua.nure.sigma.service.CommentService;
import ua.nure.sigma.service.DiagramService;

@RestController
public class CommentController {

	private SimpMessagingTemplate template;
	private AccountService service = new AccountService();
	private CommentService commentService = new CommentService();
	private DiagramService diagramService = new DiagramService();

	@Autowired
	public CommentController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/diagram/{id}/comment")
	@SendTo("/topic/diagram/{id}/comment")
	public Comment handle(@DestinationVariable String id, String message, Principal principal) {
		User user = service.getUserByLogin(principal.getName());
		Diagram diagram = diagramService.getDiagramById(Long.parseLong(id));
		Comment comment = new Comment();
		comment.setCommentText(message);
		comment.setDiagram(diagram);
		comment.setUser(user);
		return comment;
	}

}
