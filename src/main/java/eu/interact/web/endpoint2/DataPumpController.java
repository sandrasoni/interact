package eu.interact.web.endpoint2;


import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PrivateDelegatedActEvent;
import eu.interact.domain.PublicDelegatedActEvent;
import eu.interact.repository.PrivateDelegatedActEventRepository;
import eu.interact.repository.PublicDelegatedActEventRepository;
import eu.interact.web.DelegatedActService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/v2/pump")
public class DataPumpController {

    private static final Logger logger = LoggerFactory.getLogger(DataPumpController.class);

    private static final String COMMA = ";";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
    public static final String PUBLIC = "public";

    @Autowired
    PrivateDelegatedActEventRepository privateDelegatedActEventRepository;

    @Autowired
    PublicDelegatedActEventRepository publicDelegatedActEventRepository;

    @Autowired
    DelegatedActService privateActRepository;

    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping(value = "/addRealData")
    public String saveRealData() {
        saveEvents();
        return "OK";
    }

    public void saveEvents() {

        Resource actResource = resourceLoader.getResource("classpath:data_1.csv");
        Resource eventResource = resourceLoader.getResource("classpath:data_2.csv");

        InputStream actFile = null;
        InputStream eventFile = null;
        
        try {
            actFile = actResource.getInputStream();
            eventFile = eventResource.getInputStream();
        } catch (IOException e) {
            logger.error("Error loading actResource file", e);
        }

        String line = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(actFile))) {
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] actData = line.split(COMMA);
                PrivateDelegatedAct act = buildAct( actData[0],actData[1], actData[2], Arrays.asList(actData[3].split(",")), PUBLIC.equals(actData[4]), actData[4]);
                privateActRepository.save(act);
            }

            try (BufferedReader br2 = new BufferedReader(new InputStreamReader(eventFile))) {
                br2.readLine(); // skip header
                while ((line = br2.readLine()) != null) {
                    String[] eventData = line.split(COMMA);
                    if (PUBLIC.equals(eventData[6])) {
                        PublicDelegatedActEvent publicDelegatedActEvent = buildPublicEvent(eventData[0], eventData[1], eventData[2], Arrays.asList(eventData[3]), eventData[4], eventData[5]);
                        publicDelegatedActEventRepository.save(publicDelegatedActEvent);
                    } else {
                        PrivateDelegatedActEvent privateDelegatedActEvent = buildPrivateEvent(eventData[0], eventData[1], eventData[2], Arrays.asList(eventData[3]), eventData[4], eventData[5], true);
                        privateDelegatedActEventRepository.save(privateDelegatedActEvent);
                    }
                }
            }

        } catch (IOException e) {
            logger.error("Error saving private act", e);
        }
    }

    private PrivateDelegatedAct buildAct(String id, String code, String title, List<String> keywords, boolean visibility, String type) {
        PrivateDelegatedAct privateAct = new PrivateDelegatedAct();
        privateAct.setId(id);
        privateAct.setCode(code);
        privateAct.setTitle(title);
        privateAct.setKeywords(keywords);
        privateAct.setVisibility(visibility);
        privateAct.setType(type);
        privateAct.setCreationDate(new Date());
        return privateAct;
    }

    private PrivateDelegatedActEvent buildPrivateEvent(String eventId, String actId, String originatingInstitution, List<String> destinationInstitution, String name, String date, boolean visibility) {
        PrivateDelegatedActEvent event = new PrivateDelegatedActEvent();
        event.setId(eventId);
        event.setDelegatedActId(actId);
        event.setOriginatingInstitution(originatingInstitution);
        event.setDestinationInstitutions(destinationInstitution);
        event.setName(name);
        event.setVisibility(visibility);

        Date d = new Date();
        try {
            d = sdf.parse(date);
        }
        catch (ParseException e) {
           logger.error("Could not parse date " + date, e);
        }

        event.setCreationDate(d);
        return event;
    }


    private PublicDelegatedActEvent buildPublicEvent(String eventId, String actId, String originatingInstitution, List<String> destinationInstitution, String name, String date) {
        PublicDelegatedActEvent event = new PublicDelegatedActEvent();
        event.setId(eventId);
        event.setDelegatedActId(actId);
        event.setOriginatingInstitution(originatingInstitution);
        event.setDestinationInstitutions(destinationInstitution);
        event.setName(name);

        Date d = new Date();
        try {
            d = sdf.parse(date);
        }
        catch (ParseException e) {
            logger.error("Could not parse date " + date, e);
        }

        event.setCreationDate(d);
        return event;
    }
}
