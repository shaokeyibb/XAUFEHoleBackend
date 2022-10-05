package cn.xctra.xaufeholebackend.configurations;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfigure {

    @Bean("xmlMapper")
    public XmlMapper xmlMapper() {
        return new Jackson2ObjectMapperBuilder().createXmlMapper(true).build();
    }

}
