package eu.interact.repository;


import eu.interact.domain.PrivateDelegatedAct;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface PrivateDeletegatedActRepository extends CrudRepository<PrivateDelegatedAct, String> {

    @Query("SELECT * from delegated_act_private where title in(?0)")
    PrivateDelegatedAct findPrivateDelegatedActBySearchQuery(String keyword);
}
