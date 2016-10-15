package eu.interact.repository;


import eu.interact.domain.PrivateDelegatedActEvent;
import org.springframework.data.repository.CrudRepository;

public interface DelegatedActEventRepository extends CrudRepository<PrivateDelegatedActEvent, String>  {
}
