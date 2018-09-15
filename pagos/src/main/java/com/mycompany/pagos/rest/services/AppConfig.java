/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pagos.rest.services;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Johan
 */
@ApplicationPath("api")
public class AppConfig extends ResourceConfig{

    public AppConfig() {
        packages("com.mycompany.pagos.rest.auth;com.mycompany.pagos.rest.services");
    }
    
}
