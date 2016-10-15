package eu.interact.basic;

import eu.interact.domain.PrivateDelegatedAct;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.Session;

@Configuration
@EnableAutoConfiguration
@ComponentScan("eu.interact")
public class BasicConfiguration {

    @Configuration
    @EnableCassandraRepositories
    static class CassandraConfig extends AbstractCassandraConfiguration {

        @Override
        public String getKeyspaceName() {
            return "interact";
        }

        @Bean
        public CassandraTemplate cassandraTemplate(Session session) {
            return new CassandraTemplate(session);
        }

        @Override
        public String[] getEntityBasePackages() {
            return new String[] { PrivateDelegatedAct.class.getPackage().getName() };
        }

        @Override
        public SchemaAction getSchemaAction() {
            return SchemaAction.RECREATE;
        }
    }
}
