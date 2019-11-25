/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.indracompany.prueba.datos;

import com.indracompany.prueba.entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author cristian
 */
public class UsuarioRepo {
    
    private EntityManagerFactory factory = 
    Persistence.createEntityManagerFactory("persistencia");
    
    private EntityManager repo = factory.createEntityManager();
    
    
    public Usuario getBy(String nombre) {
        try {
            Usuario usuario = 
            (Usuario) repo.createQuery("SELECT u from Usuario u where u.nombre = :nombre")
                         .setParameter("nombre", nombre).getSingleResult();
            
            return usuario;
        }
        catch (NoResultException e) {
            return null;
        }
    }  
 }
    
    

