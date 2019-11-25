/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.indracompany.prueba.logica;


import com.indracompany.prueba.datos.UsuarioRepo;
import com.indracompany.prueba.entidades.Usuario;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author cristian
 */
@Stateless
@LocalBean
public class UsuarioService {
    
    private UsuarioRepo usuarioRepo = new UsuarioRepo();
    

    public boolean encontrar(String nombre) {
        return (this.usuarioRepo.getBy(nombre) != null);
    }
    
    public Usuario getUsuarioBy(String nombre) {
        return this.usuarioRepo.getBy(nombre);
    }
}