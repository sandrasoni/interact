package eu.interact.web.endpoint2;


import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PrivateDelegatedActEvent;
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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/v2/pump")
public class DataPumpController {

//    @Autowired
//    PrivateDeletegatedActRepository privateActRepository;
//
//    @Autowired
//    PublicDelegatedActRepository publicActRepository;

    private static final Logger logger = LoggerFactory.getLogger(DataPumpController.class);

    private static final String COMMA = ";";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");

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
        File actFile = null;

        Resource eventResource = resourceLoader.getResource("classpath:data_2.csv");
        File eventFile = null;

        try {
            actFile = actResource.getFile();
            eventFile = eventResource.getFile();
        } catch (IOException e) {
            logger.error("Error loading actResource file", e);
        }

        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(actFile))) {
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] actData = line.split(COMMA);
                PrivateDelegatedAct act = buildAct( actData[0],actData[1], actData[2], Arrays.asList(actData[3].split(",")), true, actData[4]);
                privateActRepository.save(act);
            }

            try (BufferedReader br2 = new BufferedReader(new FileReader(eventFile))) {
                br2.readLine(); // skip header
                while ((line = br2.readLine()) != null) {
                    String[] eventData = line.split(COMMA);
                    PrivateDelegatedActEvent ev1 = buildEvent(eventData[0],eventData[1], eventData[2], Arrays.asList(eventData[3]), eventData[4], eventData[5], eventData[6].equals("public"));
                    privateDelegatedActEventRepository.save(ev1);
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

    private PrivateDelegatedActEvent buildEvent(String eventId, String actId, String originatingInstitution, List<String> destinationInstitution, String name, String date, boolean visibility) {
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
}
