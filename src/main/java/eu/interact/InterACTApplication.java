package eu.interact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraEntityClassScanner;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class InterACTApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterACTApplication.class, args);
    }

    @Bean
    public CassandraMappingContext mappingContext() throws Exception {
        BasicCassandraMappingContext bean = new BasicCassandraMappingContext();
        bean.setInitialEntitySet(CassandraEntityClassScanner.scan(("eu.interact")));

        return bean;
    }
}
