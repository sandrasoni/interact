package eu.interact.repository;


import eu.interact.domain.PrivateDelegatedActEvent;
import eu.interact.domain.PublicDelegatedActEvent;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrivateDelegatedActEventRepository extends CrudRepository<PrivateDelegatedActEvent, String>  {

    @Query("SELECT * from delegated_act_event_private where delegated_act_id = (?0)")
    List<PrivateDelegatedActEvent> findByActId(String actId);
}
