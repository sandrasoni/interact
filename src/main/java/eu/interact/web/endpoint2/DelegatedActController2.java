/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.interact.web.endpoint2;

import eu.interact.domain.DelegatedAct2;
import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PrivateDelegatedActEvent;
import eu.interact.domain.PublicDelegatedAct;
import eu.interact.repository.PrivateDelegatedActEventRepository;
import eu.interact.repository.PrivateDeletegatedActRepository;
import eu.interact.repository.PublicDelegatedActEventRepository;
import eu.interact.repository.PublicDelegatedActRepository;
import eu.interact.web.DelegatedActService;
import eu.interact.web.model.PrivateDelegatedActDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.*;

/**
 *
 * @author Root
 */
@RestController
@RequestMapping(value="/v2/acts")
public class DelegatedActController2 {

    @Autowired
    private DelegatedActService actService;


    //@Autowired
    //DelegatedActRepository actsRepository;
    private List<DelegatedAct2> userActs = new ArrayList<DelegatedAct2>();
    //private static final Logger logger = LogManager.getLogger(DelegatedActController.class);
    //static final Logger LOG = LoggerFactory.getLogger(MyClassName.class)


    public void info(String message) {
        System.out.println(message);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public PrivateDelegatedAct save(@RequestBody PrivateDelegatedAct act) {
        PrivateDelegatedAct savedAct =  actService.save(act);

        return savedAct;
    }

    @RequestMapping(value = "/add/event", method = RequestMethod.POST)
    public PrivateDelegatedActEvent saveEvent(@RequestBody PrivateDelegatedActEvent act) {
        PrivateDelegatedActEvent savedActEvent =  actService.save(act);

        return savedActEvent;
    }

    @RequestMapping(value = "/private/list")
    public List<PrivateDelegatedActDTO> listPrivate() {

        List<PrivateDelegatedAct> acts = actService.getAllPrivate();
        List<PrivateDelegatedActDTO> dtos = new ArrayList<>();
        for (PrivateDelegatedAct act: acts) {
            PrivateDelegatedActDTO dto = new PrivateDelegatedActDTO(act);
            // dto.setEvents(actService.getPrivateActEvents(act.getId()));

            dtos.add(dto);
        }

        return dtos;
    }

    @RequestMapping(value = "/public/list")
    public List<PublicDelegatedAct> listPublic() {
        return actService.getAllPublic();
    }

    @RequestMapping(value="/private/search")
    public List<PrivateDelegatedAct> search(@RequestParam(defaultValue = "") String text) {
        List<PrivateDelegatedAct> result = new ArrayList<>();
        for (PrivateDelegatedAct userAct: actService.getAllPrivate()) {
            if(userAct.getKeywords().contains(text.toLowerCase())) {
                result.add(userAct);
            }
            if(userAct.getTitle().contains(text.toLowerCase())) {
                result.add(userAct);
            }
        }
        return result;
    }

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}
