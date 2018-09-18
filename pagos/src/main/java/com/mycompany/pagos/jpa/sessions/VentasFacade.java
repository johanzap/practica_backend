/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pagos.jpa.sessions;

import com.mycompany.pagos.jpa.entities.Usuarios;
import com.mycompany.pagos.jpa.entities.Ventas;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Johan
 */
@Stateless
public class VentasFacade extends AbstractFacade<Ventas> {

    @PersistenceContext(unitName = "com.mycompany_pagos_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentasFacade() {
        super(Ventas.class);
    }
    
    public List<Ventas> findByUserByDay (String fechaInicio, String fechaFin, Integer idUser) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateInicio = sdf.parse(fechaInicio);
            Date dateFin = sdf.parse(fechaFin);
            Query query = em.createNamedQuery("Ventas.search");
        query.setParameter("fechaInicio", dateInicio);
        query.setParameter("fechaFin", dateFin);
            System.out.println("ASDF " + dateInicio + " " + dateFin);
        query.setParameter("usuario", new Usuarios(idUser));
        return (List<Ventas>) query.getResultList();
        } catch (Exception e) {
            return (List<Ventas>) new ArrayList<Ventas>();
        }
        
        
    }
    
}
