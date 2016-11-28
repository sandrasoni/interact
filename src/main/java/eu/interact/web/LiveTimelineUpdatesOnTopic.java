/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.web;

import eu.interact.domain.PublicDelegatedActEvent;
import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Root
 */
@Component
public class LiveTimelineUpdatesOnTopic {
    
    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay=3000)
    public void publishUpdates(){
        
        PublicDelegatedActEvent newEvent = new PublicDelegatedActEvent();
        newEvent.setCreationDate(new Date());
        newEvent.setDelegatedActId("1");
        newEvent.setDestinationInstitutions(Arrays.asList("Commission"));
        newEvent.setId("15");
        newEvent.setKeywords(Arrays.asList("contracts"));
        newEvent.setName("Event name");
        newEvent.setOriginatingInstitution("Paliament");
        
        template.convertAndSend("/topic/greetings", newEvent);
    }
}
