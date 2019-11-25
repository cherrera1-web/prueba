/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.indracompany.prueba.datos;

import com.indracompany.prueba.entidades.Registro;
import com.indracompany.prueba.entidades.Usuario;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author cristian
 */
public class RegistroRepo {
    
    private EntityManagerFactory factory = 
    Persistence.createEntityManagerFactory("persistencia");
    
    private EntityManager repo = factory.createEntityManager();
    
    
    public void guardar(Registro registro) {
        this.repo.getTransaction().begin();
        this.repo.persist(registro);
        this.repo.getTransaction().commit();
    }
    
    public ArrayList<Registro> getBy(Usuario usuario, int minimo, int maximo) {
       try {
            ArrayList<Registro> registros = 
            (ArrayList<Registro>) repo.
            createQuery("SELECT r from Registro r where r.usuario = :usuario and r.resultado > :minimo and r.resultado < :maximo")
                         .setParameter("usuario", usuario)
                         .setParameter("minimo", minimo)
                         .setParameter("maximo", maximo).getResultList();
            
            return registros;
        }
        catch (NoResultException e) {
            return null;
        }
    }
    
}
