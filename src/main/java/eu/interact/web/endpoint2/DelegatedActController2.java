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
    DelegatedActService actService;
    //@Autowired
    //DelegatedActRepository actsRepository;
    List<DelegatedAct2> userActs = new ArrayList<DelegatedAct2>();
    //private static final Logger logger = LogManager.getLogger(DelegatedActController.class);
    //static final Logger LOG = LoggerFactory.getLogger(MyClassName.class)

    @Autowired
    PrivateDeletegatedActRepository privateActRepository;

    @Autowired
    PublicDelegatedActRepository publicActRepository;

    @Autowired
    PrivateDelegatedActEventRepository privateDelegatedActEventRepository;

    @Autowired
    PublicDelegatedActEventRepository publicDelegatedActEventRepository;

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
        PrivateDelegatedActEvent savedAct =  actService.save(act);

        return savedAct;
    }

    @RequestMapping(value = "/private/list")
    public List<PrivateDelegatedAct> listPrivate() {

        return actService.getAllPrivate();
    }

    @RequestMapping(value = "/public/list")
    public List<PublicDelegatedAct> listPublic() {
        return actService.getAllPublic();
    }

    @RequestMapping(value="/private/search")
    public List<DelegatedAct2> search(@RequestParam(defaultValue = "") String text) {
        List<DelegatedAct2> result = new ArrayList<DelegatedAct2>();
        for (DelegatedAct2 userAct: userActs) {
            if(userAct.getKeywords().contains(text.toLowerCase())) {
                result.add(userAct);
            }
            if(userAct.getTitle().contains(text.toLowerCase())) {
                result.add(userAct);
            }
        }
        return userActs;
    }

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    @RequestMapping(value = "/addRealData")
    public String saveRealData() {
        saveEvents();
        return "OK";
    }

    public void saveEvents() {
        PrivateDelegatedAct act1 = buildAct("1", "2015/0308(COD)", "Better services for skills and qualifications (Europass)", Arrays.asList("skills", "employment", "education", "jobs"), true, "directive");
        actService.save(act1);

        PrivateDelegatedAct act2 = buildAct("2", "2016/0407(COD)", "Reform of e-Privacy", Arrays.asList("telecommunication", "privacy", "IT", "online service"), true, "directive");
        privateActRepository.save(act2);

        PrivateDelegatedAct act3 = buildAct("3", "2016/1234(COD)", "Roaming legislation", Arrays.asList("telecommunication", "roaming", "single market", "consumers"), true, "directive");
        privateActRepository.save(act3);

        PrivateDelegatedAct act4 = buildAct("4", "2016/1234(COD)", "Roaming legislation", Arrays.asList("telecommunication", "roaming", "single market", "consumers"), true, "regulation");
        privateActRepository.save(act4);

        PrivateDelegatedAct act5 = buildAct("5", "2015/0903(COD)", "Copyright in the single market", Arrays.asList("single market", "copyright"), true, "directive");
        privateActRepository.save(act5);

        PrivateDelegatedAct act6  = buildAct("6", "2016/3504(RSP)", "Salary increase of EU officials taking part in innovative events", Arrays.asList("salary", "innovation", "EU staff"), true, "agreement");
        privateActRepository.save(act6);

        PrivateDelegatedAct act7  = buildAct("7", "2016/2607(RSP)", "Resolution on freedom of expression in Kazakhstan", Arrays.asList("freedom of expression", "human rights", "Kazakhstan"), true, "resolution");
        privateActRepository.save(act7);

        PrivateDelegatedActEvent ev1 = buildEvent("1","1", "Commission", Arrays.asList("Commission"), "Internal consultation by expert group", true);
        privateDelegatedActEventRepository.save(ev1);

        PrivateDelegatedActEvent ev2 = buildEvent("2","1", "Commission", Arrays.asList("Commission"), "Legislative proposal published", false);
        privateDelegatedActEventRepository.save(ev2);

        PrivateDelegatedActEvent ev3 = buildEvent("3","1", "Commission", Arrays.asList("Parliament"), "Submitted to Parliament", true);
        privateDelegatedActEventRepository.save(ev3);

        PrivateDelegatedActEvent ev4 = buildEvent("4","1", "Commission", Arrays.asList("Council"), "Submitted to Council", true);
        privateDelegatedActEventRepository.save(ev4);
    }

    private PrivateDelegatedAct buildAct(String id, String code, String title, List<String> keywords, boolean visibility, String type) {
        PrivateDelegatedAct privateAct = new PrivateDelegatedAct();
        privateAct.setId(id);
        privateAct.setCode(code);
        privateAct.setTitle(title);
        privateAct.setKeywords(keywords);
        privateAct.setVisibility(visibility);
        privateAct.setType(type);
        return privateAct;
    }

    private PrivateDelegatedActEvent buildEvent(String eventId, String actId, String originatingInstitution, List<String> destinationInstitution, String name, boolean visibility) {
        PrivateDelegatedActEvent event = new PrivateDelegatedActEvent();
        event.setId(eventId);
        event.setDelegatedActId(actId);
        event.setOriginatingInstitution(originatingInstitution);
        event.setDestinationInstitutions(destinationInstitution);
        event.setName(name);
        event.setVisibility(visibility);
        event.setCreationDate(new Date());
        return event;
    }

}
