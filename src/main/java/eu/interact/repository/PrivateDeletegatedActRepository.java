package eu.interact.repository;


import eu.interact.domain.PrivateDelegatedAct;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface PrivateDeletegatedActRepository extends CrudRepository<PrivateDelegatedAct, String> {

    @Query("SELECT * from delegated_act_private where title in(?0)")
    PrivateDelegatedAct findPrivateDelegatedActBySearchQuery(String keyword);

    @Query("SELECT * from delegated_act_private where delegated_act_id = (?0)")
    PrivateDelegatedAct findByActId(String actId);
}
