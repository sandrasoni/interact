package eu.interact.web.endpoint2;


import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PrivateDelegatedActEvent;
import eu.interact.repository.PrivateDelegatedActEventRepository;
import eu.interact.repository.PrivateDeletegatedActRepository;
import eu.interact.repository.PublicDelegatedActEventRepository;
import eu.interact.repository.PublicDelegatedActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/v2/pump")
public class DataPumpController {

    @Autowired
    PrivateDeletegatedActRepository privateActRepository;

    @Autowired
    PublicDelegatedActRepository publicActRepository;

    @Autowired
    PrivateDelegatedActEventRepository privateDelegatedActEventRepository;

    @Autowired
    PublicDelegatedActEventRepository publicDelegatedActEventRepository;

    @RequestMapping(value = "/addRealData")
    public String saveRealData() {
        saveEvents();
        return "OK";
    }

    public void saveEvents() {
        PrivateDelegatedAct act1 = buildAct("1", "2015/0308(COD)", "Better services for skills and qualifications (Europass)", Arrays.asList("skills", "employment", "education", "jobs"), true, "directive");
        privateActRepository.save(act1);

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
