package com.hegde.survey.api.consumer.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by girish hegde on 9/17/17.
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {
    private SpringSwaggerConfig springSwaggerConfig;
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(
                apiInfo()).includePatterns("/survey/.*");
    }
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Survey-Consumer API", "API for Survey-Consumer",
                "Survey-Consumer API terms of service", "hegdegirishb@gmail.com",
                "Survey-Consumer API Licence Type", "Survey-Consumer API License URL");
        return apiInfo;
    }
}
