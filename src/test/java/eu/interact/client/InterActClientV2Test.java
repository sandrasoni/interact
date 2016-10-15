package eu.interact.client;

import eu.interact.domain.DelegatedAct2;
import eu.interact.domain.PrivateDelegatedAct;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Created by martin_mac on 15/10/16.
 */
public class InterActClientV2Test {

    String serverRoot = "http://localhost:8080/v2/";
    private static PrivateDelegatedAct testAct;

    public PrivateDelegatedAct getTestAct(String title) {
        PrivateDelegatedAct default2 = new PrivateDelegatedAct();
        default2.setCode("1");
        default2.setTitle(title);
        default2.setId(UUID.randomUUID().toString());
        default2.setVisibility(true);// public - true, private fals

        return default2;
    }

    @Test
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

        PrivateDelegatedAct act = getTestAct("act 2 - Carlos");

        RestTemplate restTemplate = new RestTemplate();
        PrivateDelegatedAct result = restTemplate.postForObject(uri, act, PrivateDelegatedAct.class);

        System.out.println(result);
    }
}
