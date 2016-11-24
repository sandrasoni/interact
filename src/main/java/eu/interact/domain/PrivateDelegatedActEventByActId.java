package eu.interact.domain;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;



@Table(value = "delegated_act_event_private_by_delegated_act_id")
public class PrivateDelegatedActEventByActId {

    @Column("id") String id;
    @PrimaryKey("delegated_act_id") String deledatedActId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeledatedActId() {
        return deledatedActId;
    }

    public void setDeledatedActId(String deledatedActId) {
        this.deledatedActId = deledatedActId;
    }
}
