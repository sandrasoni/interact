package eu.interact.repository;

import eu.interact.basic.BasicConfiguration;
import eu.interact.domain.PrivateDelegatedAct;
import eu.interact.domain.PrivateDelegatedActEvent;
import eu.interact.domain.PublicDelegatedAct;
import eu.interact.domain.PublicDelegatedActEvent;
import eu.interact.util.RequiresCassandraKeyspace;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Version;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasicConfiguration.class)
public class DelegatedActCrudRepositoryTests {

    public final static Version CASSANDRA_2_2_8 = Version.parse("2.2.8");

    @ClassRule
    public final static RequiresCassandraKeyspace CASSANDRA_KEYSPACE = RequiresCassandraKeyspace.onLocalhost();

    @Autowired
    PrivateDeletegatedActCrudRepository privateDeletegatedActCrudRepository;
    @Autowired
    PrivateDelegatedActEventRepository privateDelegatedActEventRepository;

    @Autowired
    PublicDelegatedActRepository publicDelegatedActRepository;
    @Autowired
    PublicDelegatedActEventRepository publicDelegatedActEventRepository;

    // @Autowired Session session;



    @Test
    public void testSavePrivate() {

        PrivateDelegatedAct da1 = new PrivateDelegatedAct();
        da1.setId("id1");
        da1.setCode("C1");
        da1.setTitle("Title1");
        da1.setType("directive");
        da1.setKeywords(Arrays.asList("key1", "key2"));
        da1.setCreationDate(new Date());
        da1.setVisibility(true);

        PrivateDelegatedActEvent de1 = new PrivateDelegatedActEvent();
        de1.setId("id1");
        de1.setName("E1");
        de1.setDelegatedActId("id1");
        de1.setCreationDate(new Date());
        de1.setOriginatingInstitution("EU");
        de1.setDestinationInstitutions(Arrays.asList("EU1", "EU2"));
        de1.setVisibility(true);



        PrivateDelegatedAct act1 = privateDeletegatedActCrudRepository.save(da1);
        PrivateDelegatedAct dbAct1 = privateDeletegatedActCrudRepository.findOne(act1.getId());
        Assert.assertNotNull(dbAct1);

        PrivateDelegatedActEvent evt1 = privateDelegatedActEventRepository.save(de1);
        PrivateDelegatedActEvent dbEvt1 = privateDelegatedActEventRepository.findOne(evt1.getId());
        Assert.assertNotNull(dbEvt1);
    }

    @Test
    public void testSavePublic() {

        PublicDelegatedAct da1 = new PublicDelegatedAct();
        da1.setId("id1");
        da1.setCode("C1");
        da1.setTitle("Title1");
        da1.setType("directive");
        da1.setKeywords(Arrays.asList("key1", "key2"));
        da1.setCreationDate(new Date());

        PublicDelegatedActEvent de1 = new PublicDelegatedActEvent();
        de1.setId("id1");
        de1.setName("E1");
        de1.setDelegatedActId("id1");
        de1.setCreationDate(new Date());
        de1.setOriginatingInstitution("EU");
        de1.setDestinationInstitutions(Arrays.asList("EU1", "EU2"));


        PublicDelegatedAct act1 = publicDelegatedActRepository.save(da1);
        PublicDelegatedAct dbAct1 = publicDelegatedActRepository.findOne(da1.getId());
        Assert.assertNotNull(dbAct1);

        PublicDelegatedActEvent evt1 = publicDelegatedActEventRepository.save(de1);
        PublicDelegatedActEvent dbEvt1 = publicDelegatedActEventRepository.findOne(evt1.getId());
        Assert.assertNotNull(dbEvt1);
    }
}
