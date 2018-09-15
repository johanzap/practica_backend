/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pagos.rest.services;

import com.mycompany.pagos.jpa.entities.Usuarios;
import com.mycompany.pagos.jpa.sessions.UsuariosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Johan
 */
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosREST {
    
    @EJB
    private UsuariosFacade usuariosEJB;
    
    @GET
    public List<Usuarios> findAll() {
        return usuariosEJB.findAll();
    }
    
    @GET
    @Path("{email}")
    public List<Usuarios> searchUsers(@PathParam("email") String email) {
        return usuariosEJB.searchUsers(email);
    }
}
