/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pagos.jpa.sessions;

import com.mycompany.pagos.jpa.entities.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Johan
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "com.mycompany_pagos_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public List<Usuarios> searchUsers(String email) {
        Query query = em.createNamedQuery("Usuarios.searchUsers");
        query.setParameter("email", email + "%");
        return (List<Usuarios>) query.getResultList();
    }
    
    public Usuarios findByEmail(String email) {
        Query query = em.createNamedQuery("Usuarios.searchUsers");
        query.setParameter("email", email);
        try {
            return (Usuarios) query.getSingleResult();
        } catch(NonUniqueResultException ex) {
            throw ex;
        } catch(NoResultException e) {
            return null;
        }
    }
    
    public String cifrarPassword(String password) {
        Query query = em.createNativeQuery("SELECT sha2(?1, 256)");
        query.setParameter(1, password);
        return (String) query.getSingleResult();
    }
    
}
