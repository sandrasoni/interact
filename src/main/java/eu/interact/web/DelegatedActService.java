package eu.interact.web;

import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PublicDelegatedAct;
import eu.interact.repository.PrivateDeletegatedActCrudRepository;
import eu.interact.repository.PublicDelegatedActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by martin_mac on 15/10/16.
 */
@Service
public class DelegatedActService {

    @Autowired
    PrivateDeletegatedActCrudRepository privateActRepository;

    @Autowired
    PublicDelegatedActRepository publicActRepository;

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

    //@Autowired
    //PublicDeletegatedActCrudRepository privateActRepository;
    //PrivateDelegatedAct
}
