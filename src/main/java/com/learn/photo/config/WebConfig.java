package com.learn.photo.config;

import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .defaultContentType(org.springframework.http.MediaType.TEXT_HTML)
                .mediaType("html", org.springframework.http.MediaType.TEXT_HTML)
                .mediaType("json", org.springframework.http.MediaType.APPLICATION_JSON);
    }
}