package eu.interact.repository;

import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PublicDelegatedActEvent;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicDelegatedActEventRepository extends CrudRepository<PublicDelegatedActEvent, String> {


    @Query("SELECT * from delegated_act_public_event where delegated_act_id = (?0)")
    List<PublicDelegatedActEvent> findByActId(String actId);

    //
}
