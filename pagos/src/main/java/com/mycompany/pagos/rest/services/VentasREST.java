/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pagos.rest.services;

import com.mycompany.pagos.jpa.entities.Ventas;
import com.mycompany.pagos.jpa.sessions.VentasFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Johan
 */
@Path("ventas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VentasREST {
    @EJB
    private VentasFacade ventasEJB;
    
    @GET
    public Date findByUserByDay(@QueryParam("fechaInicio") Date fechaInicio,
            @QueryParam("fechaFin") Date fechaFin,
            @QueryParam("fechaInicio") Integer idUser) {
        return fechaInicio;
    }
}
