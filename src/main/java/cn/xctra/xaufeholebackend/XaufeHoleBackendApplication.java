package cn.xctra.xaufeholebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
@Configuration
@EnableCaching
@EnableAsync
@EnableTransactionManagement
public class XaufeHoleBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(XaufeHoleBackendApplication.class, args);
    }

}
