/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

//import eu.interact.temp.PrivateDelegatedActEvent;

/**
 *
 * @author Root
 */

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value = "delegated_act_private")
public class PrivateDelegatedAct {


    @PrimaryKey("id") private String id;
    @Column("code") private String code;
    @Column("title") private String title;
    @Column("keywords") private List<String> keywords;
    @Column("visibility") private boolean visibility;
    @Column("type") private String type;
    @Column("creation_date") private Date creationDate;

    //private List<PrivateDelegatedActEvent> events;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public List<PrivateDelegatedActEvent> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<PrivateDelegatedActEvent> events) {
//        this.events = events;
//    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
