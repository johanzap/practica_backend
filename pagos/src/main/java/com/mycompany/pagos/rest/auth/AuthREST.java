/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pagos.rest.auth;

import com.mycompany.pagos.jpa.entities.Usuarios;
import com.mycompany.pagos.jpa.sessions.UsuariosFacade;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Johan
 */
@Path("auth")
public class AuthREST {
    
    @EJB
    private UsuariosFacade usuariosEJB;
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(Usuarios usuario) {
        final Usuarios usuarioEncontrado;
        usuarioEncontrado = usuariosEJB.findByEmail(usuario.getEmail());
        if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(usuariosEJB.cifrarPassword(usuario.getPassword()))) {
            String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "practica_senasoft")
                    .setSubject(usuarioEncontrado.getId().toString())
                    .claim("usuario", usuarioEncontrado.getNombre() + " " + usuarioEncontrado.getApellido())
                    .claim("roles", Arrays.toString(usuarioEncontrado.getRolesList().toArray()))
                    .compact();
            JsonObject json = Json.createObjectBuilder().add("token", jwt).build();
            return Response.status(Response.Status.CREATED).entity(json).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
