package eu.interact.client;

import eu.interact.domain.DelegatedAct2;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Created by martin_mac on 15/10/16.
 */
public class InterActClientV1Test {

    String serverRoot = "http://localhost:8080/v1/";
    private static DelegatedAct2 testAct;

    public DelegatedAct2 getTestAct() {
        DelegatedAct2 default2 = new DelegatedAct2();
        default2.setCode("1");
        default2.setTitle("act 2 - from junit");
        default2.setId(UUID.randomUUID().getMostSignificantBits());
        default2.setVisibility(true);// public - true, private fals

        return default2;
    }

    //@Test
    public void getListOfActs()
    {
        final String uri = serverRoot + "acts/private/list";

        RestTemplate restTemplate = new RestTemplate();
        List<?> result = restTemplate.getForObject(uri, List.class);

        System.out.println(result);


    }

    //@Test
    public void createAct() {
        final String uri = serverRoot + "acts/add";

        DelegatedAct2 act = getTestAct();

        RestTemplate restTemplate = new RestTemplate();
        DelegatedAct2 result = restTemplate.postForObject(uri, act, DelegatedAct2.class);

        System.out.println(result);
    }
}
