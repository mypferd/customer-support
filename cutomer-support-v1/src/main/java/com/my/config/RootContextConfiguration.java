package com.my.config;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
@EnableLoadTimeWeaving
@EnableAsync(
        mode = AdviceMode.PROXY, proxyTargetClass = true,
        order = 1
)
@EnableTransactionManagement(
        mode = AdviceMode.PROXY, proxyTargetClass = true,
        order = 2
)
@EnableJpaRepositories(
        basePackages = "com.my.site.repositories",
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "jpaTransactionManager"
)
@ComponentScan(
        basePackages = "com.my.site",
        excludeFilters =
        @ComponentScan.Filter({Controller.class, ControllerAdvice.class})
)
@ImportResource({ "classpath:com/my/config/securityConfiguration.xml" })
public class RootContextConfiguration implements AsyncConfigurer, SchedulingConfigurer {

    @Bean
    public DataSource customerSupportDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/customersupport");
        dataSource.setUsername("root");
        dataSource.setPassword("maye0916");
        return dataSource;
    }

    @Override
    public Executor getAsyncExecutor() {
        return null;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

    }
}
