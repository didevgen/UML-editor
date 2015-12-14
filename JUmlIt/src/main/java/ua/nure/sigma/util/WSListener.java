package ua.nure.sigma.util;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import ua.nure.sigma.dao.HistoryDAO;
import ua.nure.sigma.dao.impl.HistoryDAOImpl;
import ua.nure.sigma.db_entities.HistorySession;
import ua.nure.sigma.service.HistoryService;
@Component
public class WSListener implements ApplicationListener {
	@Autowired
	private HttpSession httpSession;
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		if (event instanceof SessionSubscribeEvent) {
			SessionSubscribeEvent connect = (SessionSubscribeEvent) event;
			StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(connect.getMessage());
			HistoryService service = new HistoryService();
			String dest = headerAccessor.getDestination();
			Long diagramId = Long.parseLong(dest.substring(dest.lastIndexOf('/')+1));
			HistorySession session = service.insertSession(connect.getUser().getName(), diagramId);
//			httpSession.setAttribute("sessionId", session.getSessionId());
		}
		else if (event instanceof SessionUnsubscribeEvent) {
			SessionUnsubscribeEvent connect = (SessionUnsubscribeEvent) event;
			StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(connect.getMessage());
			HistoryService service = new HistoryService();
			HistoryDAO dao = new HistoryDAOImpl();
			Long sessionId = (Long )(httpSession.getAttribute("sessionId"));
			HistorySession session = dao.getSessionById(sessionId);
			service.updateSession(session);
		}
	}

}
