package ua.nure.sigma.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;

public class DiagramUpdateService {
	
	private final SimpMessagingTemplate template;

	public DiagramUpdateService(final SimpMessagingTemplate template) {
		this.template = template;
	}
	
	public void notify(final String destination, final Object object, final Principal principal) {
		final Map<String, Object> headers = new HashMap<>();
		headers.put("fromUser", principal.getName());
		
		this.template.convertAndSend(destination, object, headers);
	}
}
