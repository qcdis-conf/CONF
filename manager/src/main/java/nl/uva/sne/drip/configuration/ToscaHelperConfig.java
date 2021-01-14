/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.sne.drip.configuration;

import nl.uva.sne.drip.commons.utils.ToscaHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 *
 * @author S. Koulouzis
 */
@Configuration
@PropertySources({
    @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
})
@ComponentScan(basePackages = {"nl.uva.sne.drip", "nl.uva.sne.drip.api", "nl.uva.sne.drip.configuration", "nl.uva.sne.drip.dao", "nl.uva.sne.drip.model", "nl.uva.sne.drip.service", "nl.uva.sne.drip.commons.utils"})
public class ToscaHelperConfig {

    @Value("${sure-tosca.base.path}")
    private String sureToscaBasePath;

    @Bean
    public ToscaHelper toscaHelper() {
        return new ToscaHelper(sureToscaBasePath);
    }

}
