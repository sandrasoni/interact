package eu.interact.client;

import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PrivateDelegatedActEvent;
import org.junit.Test;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class InterActClientV2Test {

    private String serverRoot = "http://localhost:8080/v2/";
    private static PrivateDelegatedAct testAct;

    //test data
    private String[] eventDestinations = new String[] {"Parliament", "Council", "Commission"};

    private PrivateDelegatedAct getTestAct(String title, String keywordParams) {
        PrivateDelegatedAct default2 = new PrivateDelegatedAct();
        default2.setCode("1");
        default2.setTitle(title);
        default2.setType("directive");
        default2.setCreationDate(new Date());
        List<String> keywords = new ArrayList<>(StringUtils.commaDelimitedListToSet(keywordParams));
        default2.setKeywords(keywords);
        default2.setId(UUID.randomUUID().toString());
        default2.setVisibility(true);// public - true, private fals

        return default2;
    }

//    @Test
    public void getListOfActs()
    {
        final String uri = serverRoot + "acts/private/list";

        RestTemplate restTemplate = new RestTemplate();
        List<?> result = restTemplate.getForObject(uri, List.class);

        System.out.println(result);


    }

    @Test
    public void createAct() {
        final String uri = serverRoot + "acts/add";
        String []dummyArray  = new String[] {"act 2 - Carlos", "act 2 - Sebi", "act 2 - Martin", "act 2 - Iulian"};
        String []dummyKeywords  = new String[] {"Council,Agriculture,Technology", "Commision,Prog,Java", "Parliament, Justice, law"};
        for (int i = 0; i < dummyArray.length-1; i++) {

            PrivateDelegatedAct act = getTestAct(dummyArray[i], dummyKeywords[i]);

            RestTemplate restTemplate = new RestTemplate();
            PrivateDelegatedAct result = restTemplate.postForObject(uri, act, PrivateDelegatedAct.class);

            System.out.println(result);
        }
    }

    @Test
    public void createEvent() {
        final String uri = serverRoot + "acts/add/event";
        String []dummyArray  = new String[] {"act 2 - Carlos", "act 2 - Sebi", "act 2 - Martin", "act 2 - Iulian"};
        //String actTypes[] = new String[] { "Directive", "Regulation", "Agreement", "Resolution"};
        String []dummyKeywords  = new String[] {"Council,Agriculture,Technology", "Commision,Prog,Java", "Parliament, Justice, law"};
        String actId = "2";
        //int i= 0;
        for (int i = 0; i < dummyArray.length-1; i++) {
            boolean visibilty = i % 2 == 0;
            PrivateDelegatedActEvent act = getTestActEvent(visibilty, "DECIDE event " + i, actId, dummyKeywords[i], dummyArray[i]); //dummyArray[i], dummyKeywords[i]

            RestTemplate restTemplate = new RestTemplate();
            PrivateDelegatedActEvent result = restTemplate.postForObject(uri, act, PrivateDelegatedActEvent.class);

            System.out.println(result);
        }
    }

    private String getActType(String[] actTypes) {

        return actTypes[getRand(0, actTypes.length-1)];
    }

    private int getRand(int min, int max) {
        int randomNum = min + (int)(Math.random() * max);

        return  randomNum;
    }

    private PrivateDelegatedActEvent getTestActEvent(boolean visibilty, String name, String actId, String keywordParams, String title) {
        PrivateDelegatedActEvent actEvent = new PrivateDelegatedActEvent();
        actEvent.setVisibility(visibilty);
        //act.setType(type);
        actEvent.setId(UUID.randomUUID().toString());
        actEvent.setDelegatedActId(actId);
        List<String> keywords = new ArrayList<>(StringUtils.commaDelimitedListToSet(keywordParams));
        actEvent.setKeywords(keywords);
        actEvent.setName(name);
        actEvent.setCreationDate(new Date());
        actEvent.setDestinationInstitutions(Collections.singletonList(eventDestinations[getRand(0, 3)]));
        return  actEvent;
    }
}
