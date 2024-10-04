package com.loonds.StaySync.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerAPIConfig {
    @Value("${env.dev}")
    private String devUrl;

    @Value("${env.prod}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("pankajkumar.techie@gmail.com");
        contact.setName("Pankaj Kumar");
        contact.setUrl("https://techie.pahariyatri.com/");

        License mitLicense = new License().name("Copy Right").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("StaySync - Hospitality Management REST-API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage the Hospitality operations,including guest entries, orders, inventory, crew members, documents, bill generation, and overall hotel operations.").termsOfService("https://www.pahariyatri/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
