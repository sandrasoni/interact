package eu.interact.repository;

import eu.interact.domain.PrivateDelegatedActEvent;
import eu.interact.domain.PrivateDelegatedActEventByActId;
import org.springframework.data.repository.CrudRepository;

public interface PrivateDelegatedActEventByActIdRepository extends CrudRepository<PrivateDelegatedActEventByActId, String> {

}
