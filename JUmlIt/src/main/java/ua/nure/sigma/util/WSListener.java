package ua.nure.sigma.util;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

public class WSListener implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		if (event instanceof SessionSubscribeEvent) {
			SessionSubscribeEvent connect = (SessionSubscribeEvent) event;
			StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(connect.getMessage());
			System.out.println(headerAccessor.getDestination());
			System.out.println(connect.getUser().getName());
		}
		else if (event instanceof SessionUnsubscribeEvent) {
			
		}
	}

}
