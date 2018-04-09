package by.login.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "by.login.entity")
@EnableJpaRepositories(basePackages = "by.login.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "by.login.service")
public class RepositoryConfigurationTest {
}
