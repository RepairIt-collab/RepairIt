package com.app.FixIt.SSL;

// public class HttpsConfig {
    
// }

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpsConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainerCustomizer() {
        return (container -> {
            Ssl ssl = new Ssl();
            ssl.setKeyStoreType("PKCS12");
            ssl.setKeyAlias("alias_repairit");
            ssl.setKeyStorePassword("annaelleorlane");
            ssl.setKeyStore("src/main/resources/keystore.p12");
            container.setSsl(ssl);
            container.setPort(9001);
        });
    }
}

