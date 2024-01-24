package com.rainesinc.warhammer.configuration;

import com.rainesinc.warhammer.util.CorsConfigurationParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(CorsConfigurationParser.getAllowedOrigins());
        corsConfiguration.setAllowedHeaders(CorsConfigurationParser.getAllowedHeaders());
        corsConfiguration.setAllowedMethods(CorsConfigurationParser.getAllowedMethods());
        corsConfiguration.setExposedHeaders(CorsConfigurationParser.getExposedHeaders());

        var urlBasedCorsConfigurationSource =
                new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource
                .registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
