/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.indracompany.prueba.logica;

import com.indracompany.prueba.datos.RegistroRepo;
import com.indracompany.prueba.entidades.Registro;
import com.indracompany.prueba.entidades.Usuario;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author cristian
 */
@Stateless
@LocalBean
public class ConsultaService {
    @EJB
    private UsuarioService usuarioService;
    private RegistroRepo registroRepo = new RegistroRepo();
    
    
    // metodos logica de negocio
    
    public boolean aceptar(int minimo, int maximo) {
        return maximo > minimo;
    }
    
    // metodos de acceso a la BD
    
    public ArrayList<Registro> consultar(String nombre, int minimo, int maximo) { 
        
        if(!this.usuarioService.encontrar(nombre)){
            return null;
        }
     
        Usuario usuario = this.usuarioService.getUsuarioBy(nombre);
        ArrayList<Registro> registros = this.registroRepo.getBy(usuario, minimo, maximo);
        
        if(registros.isEmpty()) {
            return null;
        }
  
        return registros;
    }
    
    
    
    
    
}
