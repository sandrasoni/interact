package eu.interact.web;

import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PrivateDelegatedActEvent;
import eu.interact.domain.PublicDelegatedAct;
import eu.interact.domain.PublicDelegatedActEvent;
import eu.interact.repository.PrivateDelegatedActEventRepository;
import eu.interact.repository.PrivateDeletegatedActRepository;
import eu.interact.repository.PublicDelegatedActEventRepository;
import eu.interact.repository.PrivateDeletegatedActRepository;
import eu.interact.repository.PublicDelegatedActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DelegatedActService {

    @Autowired
    PrivateDeletegatedActRepository privateActRepository;

    @Autowired
    PublicDelegatedActRepository publicActRepository;

    @Autowired
    PrivateDelegatedActEventRepository privateEventRepository;

    @Autowired
    PublicDelegatedActEventRepository publicEventRepository;

    public PrivateDelegatedAct save(PrivateDelegatedAct act) {

        PrivateDelegatedAct savedAct =  privateActRepository.save(act);
        if(savedAct.isVisibility()) { // true - public
            //TODO savein private repo
            publicActRepository.save(convert(savedAct));
        }
        return savedAct;

    }

    private PublicDelegatedAct convert(PrivateDelegatedAct privateAct) {
        PublicDelegatedAct act = new PublicDelegatedAct();
        act.setTitle(privateAct.getTitle());
        act.setCode(privateAct.getCode());
        act.setCreationDate(privateAct.getCreationDate());
        act.setKeywords(privateAct.getKeywords());
        act.setId(privateAct.getId());

        return act;
    }

    public List<PrivateDelegatedAct> getAllPrivate() {
        List<PrivateDelegatedAct> result = new ArrayList<PrivateDelegatedAct>();
        result.addAll( makeCollection(privateActRepository.findAll()));

        return result;
    }

    public PublicDelegatedAct getPublicByActId(String actId) {
        PublicDelegatedAct act = publicActRepository.findOne(actId);

        List<PublicDelegatedActEvent> result = publicEventRepository.findByActId(actId);

//        if(result != null) {
//            act.setEvents(result);
//        }

        //result.addAll( makeCollection(publicActRepository.findAll()));

        return act;
    }

    public List<PrivateDelegatedAct> getPrivateById() {
        List<PrivateDelegatedAct> result = new ArrayList<PrivateDelegatedAct>();
        result.addAll( makeCollection(privateActRepository.findAll()));

        return result;
    }

    public List<PublicDelegatedAct> getAllPublic() {
        List<PublicDelegatedAct> result = new ArrayList<PublicDelegatedAct>();
        result.addAll( makeCollection(publicActRepository.findAll()));

        return result;
    }

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    public PrivateDelegatedActEvent save(PrivateDelegatedActEvent act) {
        PrivateDelegatedActEvent privateEvent = privateEventRepository.save(act);
        if(privateEvent.isVisibility()) {
            publicEventRepository.save(convertEvent(privateEvent));
        }

        return privateEvent;
    }

    private PublicDelegatedActEvent convertEvent(PrivateDelegatedActEvent privateEvent) {
        PublicDelegatedActEvent publicEvent = new PublicDelegatedActEvent();

        return null;
    }

    //@Autowired
    //PublicDeletegatedActCrudRepository privateActRepository;
    //PrivateDelegatedAct
}
