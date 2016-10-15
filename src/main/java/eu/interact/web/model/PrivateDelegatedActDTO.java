/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.web.model;

import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PrivateDelegatedActEvent;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.List;

public class PrivateDelegatedActDTO extends PrivateDelegatedAct {

    public List<PrivateDelegatedActEvent> getEvents() {
        return events;
    }

    public void setEvents(List<PrivateDelegatedActEvent> events) {
        this.events = events;
    }

    private List<PrivateDelegatedActEvent> events;


}
