package eu.interact.web.model;

import eu.interact.domain.PublicDelegatedAct;
import eu.interact.domain.PublicDelegatedActEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * Created by martin_mac on 15/10/16.
 */
public class PublicDelegatedActDTO extends PublicDelegatedAct {


    public PublicDelegatedActDTO(PublicDelegatedAct src) { // copy constructor
        BeanUtils.copyProperties(src, this);
    }

    private List<PublicDelegatedActEvent> events;


    public List<PublicDelegatedActEvent> getEvents() {
        return events;
    }

    public void setEvents(List<PublicDelegatedActEvent> events) {
        this.events = events;
    }
}
