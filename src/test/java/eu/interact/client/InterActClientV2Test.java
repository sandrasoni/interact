package eu.interact.client;

import eu.interact.domain.PrivateDelegatedAct;
import org.junit.Test;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by martin_mac on 15/10/16.
 */
public class InterActClientV2Test {

    String serverRoot = "http://localhost:8080/v2/";
    private static PrivateDelegatedAct testAct;

    public PrivateDelegatedAct getTestAct(String title, String keywordParams) {
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
        final String uri = serverRoot + "acts/add";
        String []dummyArray  = new String[] {"act 2 - Carlos", "act 2 - Sebi", "act 2 - Martin", "act 2 - Iulian"};
        String actTypes[] = new String[] { "Directive", "Regulation", "Agreement", "Resolution"};
        String []dummyKeywords  = new String[] {"Council,Agriculture,Technology", "Commision,Prog,Java", "Parliament, Justice, law"};
        String actId = "711c7865-be37-411d-9829-9b4eb5a655df";
        for (int i = 0; i < dummyArray.length-1; i++) {
            boolean visibilty = i % 2 == 0;
            PrivateDelegatedAct act = getTestActEvent(visibilty, getActType(actTypes), actId); //dummyArray[i], dummyKeywords[i]

            RestTemplate restTemplate = new RestTemplate();
            PrivateDelegatedAct result = restTemplate.postForObject(uri, act, PrivateDelegatedAct.class);

            System.out.println(result);
        }
    }

    private String getActType(String[] actTypes) {
        int minimum = 0;
        int maximum = actTypes.length-1;
        int randomNum = minimum + (int)(Math.random() * maximum);
        return actTypes[randomNum];
    }

    private PrivateDelegatedAct getTestActEvent(boolean visibilty, String type, String id) {
        PrivateDelegatedAct act = new PrivateDelegatedAct();
        act.setVisibility(visibilty);
        act.setType(type);
        act.setId(id);
//        act.setKeywords();
        return  act;
    }
}
