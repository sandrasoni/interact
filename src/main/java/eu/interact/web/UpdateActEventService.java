package eu.interact.web;

import eu.interact.domain.PrivateDelegatedActEvent;
import eu.interact.domain.PublicDelegatedActEvent;
import eu.interact.repository.PrivateDelegatedActEventRepository;
import eu.interact.repository.PublicDelegatedActEventRepository;
import eu.interact.repository.PublicDelegatedActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

/**
 * Created by martin_mac on 15/10/16.
 */
@Service
public class UpdateActEventService {

    @Autowired
    PrivateDelegatedActEventRepository privateEventRepository;

    @Autowired
    PublicDelegatedActEventRepository publicEventRepository;

    public boolean processActEvent(PrivateDelegatedActEvent actEvent) {
        PrivateDelegatedActEvent result = privateEventRepository.save(actEvent);
        if(actEvent.isVisibility()) { // true = public
            publicEventRepository.save(convert(actEvent));
        }

        return true;
    }

    private PublicDelegatedActEvent convert(PrivateDelegatedActEvent privateActEvent) {
        PublicDelegatedActEvent publicEvent = new PublicDelegatedActEvent();

        publicEvent.setId(privateActEvent.getId());
        publicEvent.setKeywords(privateActEvent.getKeywords());
        publicEvent.setDelegatedActId(privateActEvent.getDelegatedActId());
        publicEvent.setDestinationInstitutions(privateActEvent.getDestinationInstitutions());
        publicEvent.setName(privateActEvent.getName());
        publicEvent.setOriginatingInstitution(privateActEvent.getOriginatingInstitution());
        publicEvent.setCreationDate(privateActEvent.getCreationDate());

        return publicEvent;
    }

}
