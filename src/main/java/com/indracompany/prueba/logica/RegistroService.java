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
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author cristian
 */
@Stateless
@LocalBean
public class RegistroService {
    
    final private int INFERIOR = 5;
    final private int SUPERIOR = 500;
    @EJB
    private UsuarioService usuarioService;
    private RegistroRepo registroRepo = new RegistroRepo();
    
    
    // Metodos de logica de negocio
    
    public boolean aceptar(int limite) {
        return limite >= INFERIOR && limite <= SUPERIOR;
    }
    
    public String getINFERIOR() {
        return Integer.toString(this.INFERIOR);
    }
    
    public String getSUPERIOR() {
        return Integer.toString(this.SUPERIOR);
    }
    
    
    public int sumarParesHasta(int limite) {
        int resultado = 0;
        int i;
        
        for(i = 0; i <= limite; i+=2){
            resultado+=i;
        }
        return resultado;
    }
    
    
    // Metodos de acceso a la BD
    
    public boolean encontrar(String usuario) {
        return this.usuarioService.encontrar(usuario);
    }
    
    
    public void guardar(int resultado, String nombre){
        
        Date fechaEjecucion = new Date();
        Usuario usuario = this.usuarioService.getUsuarioBy(nombre);
        this.registroRepo.guardar(new Registro(usuario, resultado, fechaEjecucion));
    }

}