package com.app.FixIt.PAIEMENT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// public class MtnApiConfig {
    
// }
@Configuration
public class MtnApiConfig {
    @Value("${mtn.api.consumerKey}")
    private String consumerKey;

    @Value("${mtn.api.consumerSecret}")
    private String consumerSecret;

    // Autres configurations et beans

    // Exemple d'utilisation des clés d'API dans une méthode
    public void makeApiCall() { 
    // Récupérer les valeurs des clés d'API
        // String key = mtnApiConfig.getConsumerKey();
        // String secret = mtnApiConfig.getConsumerSecret();

    // Utiliser les clés d'API dans votre logique d'appel d'API MTN
    // ...
}
}
