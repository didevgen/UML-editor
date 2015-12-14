package ua.nure.sigma.util;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import ua.nure.sigma.db_entities.HistorySession;
import ua.nure.sigma.service.HistoryService;

public class WSListener implements ApplicationListener {
	@Autowired
	SimpMessagingTemplate template;

	private Long diagramId;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof SessionSubscribeEvent) {

			SessionSubscribeEvent connect = (SessionSubscribeEvent) event;
			StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(connect.getMessage());
			HistoryService service = new HistoryService();
			String dest = headerAccessor.getDestination();
			Long diagramId;
			try {
				diagramId = Long.parseLong(dest.substring(dest.lastIndexOf('/') + 1));
				HistorySession session = service.insertSession(connect.getUser().getName(), diagramId);
				template.convertAndSend("/topic/diagram/" + diagramId + "/history", session);
			} catch (NumberFormatException ex) {
				return;
			}
		} else if (event instanceof SessionUnsubscribeEvent) {
			SessionUnsubscribeEvent connect = (SessionUnsubscribeEvent) event;
			StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(connect.getMessage());
			Principal princ = connect.getUser();
			System.out.println(princ.getName());
			HistoryService service = new HistoryService();
			List<HistorySession> sessions = service.updateSession(connect.getUser().getName());
			for (HistorySession session : sessions) {
				template.convertAndSend("/topic/diagram/" + session.getDiagram().getDiagramId() + "/history", session);
			}

		}
	}

}
