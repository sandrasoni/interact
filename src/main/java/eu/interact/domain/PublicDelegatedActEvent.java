package eu.interact.domain;


import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.List;

@Table(value = "delegated_act_event_public")
public class PublicDelegatedActEvent implements Comparable {

    @PrimaryKey("id") String id;
    @Column("delegated_act_id") String delegatedActId;
    @Column("originating_institution") String originatingInstitution;
    @Column("destination_institution") List<String> destinationInstitutions;
    @Column("name") String name;
    @Column("keywords") private List<String> keywords;
    @Column("creation_date") Date creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelegatedActId() {
        return delegatedActId;
    }

    public void setDelegatedActId(String delegatedActId) {
        this.delegatedActId = delegatedActId;
    }

    public String getOriginatingInstitution() {
        return originatingInstitution;
    }

    public void setOriginatingInstitution(String originatingInstitution) {
        this.originatingInstitution = originatingInstitution;
    }

    public List<String> getDestinationInstitutions() {
        return destinationInstitutions;
    }

    public void setDestinationInstitutions(List<String> destinationInstitutions) {
        this.destinationInstitutions = destinationInstitutions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof PublicDelegatedActEvent) {
            return delegatedActId.compareTo(((PublicDelegatedActEvent) o).getDelegatedActId());
        }
        return -1;
    }
}
