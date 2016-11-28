/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.web;

import eu.interact.domain.PublicDelegatedActEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author virteiu
 */
@RestController
@RequestMapping("/trigger")
public class EventTriggerController {
    
    @Autowired
    private SimpMessagingTemplate template;
    
    @RequestMapping(value = "/{institution}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void triggerEvent(@PathVariable String institution) {
        
        List<String> destinationInstitutions;
        if (institution.equals("Commission")) {
            destinationInstitutions = Arrays.asList("Council", "Parliament");
        } else if (institution.equals("Council")) {
            destinationInstitutions = Arrays.asList("Commission", "Parliament");
        } else {
            destinationInstitutions = Arrays.asList("Commission", "Council");
        }
        
        PublicDelegatedActEvent newEvent = new PublicDelegatedActEvent();
        newEvent.setCreationDate(new Date());
        newEvent.setDelegatedActId("1");
        newEvent.setDestinationInstitutions(destinationInstitutions);
        newEvent.setId("15");
        newEvent.setKeywords(Arrays.asList("contracts"));
        newEvent.setName("actPlanned");
        newEvent.setOriginatingInstitution(institution);
        
        template.convertAndSend("/topic/events", newEvent);
    }
}
