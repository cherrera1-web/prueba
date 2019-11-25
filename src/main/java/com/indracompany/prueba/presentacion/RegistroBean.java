/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.indracompany.prueba.presentacion;

import com.indracompany.prueba.logica.RegistroService;
import com.indracompany.prueba.logica.UsuarioService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author cristian
 */
@ViewScoped
@ManagedBean
public class RegistroBean implements Serializable {
    private String usuario;
    private int limite;
    private int resultado;
    @EJB 
    private RegistroService registroService;
    

    public void calcular() {
        
        if(!this.validacionEsExitosa()) {
            this.resetearCampos();
            return;
        }
        
        this.resultado = this.registroService.sumarParesHasta(this.limite);
        this.registroService.guardar(this.resultado, this.usuario);
    }
        
    
    public boolean validacionEsExitosa() {
        String mensaje = "la validacion fue exitosa";
        
        if(!this.registroService.aceptar(limite)) {
            mensaje = "El limite elegido no es válido, debe estar entre "
            + this.registroService.getINFERIOR() + " y " 
            + this.registroService.getSUPERIOR();
        }
        
        if(!this.registroService.encontrar(usuario)) {
            mensaje = "El usuario no existe en el sistema!";    
        }
        
        if ("la validacion fue exitosa".equals(mensaje)) {
            return true;    
        }
        
        mensaje = "alert('" + mensaje + "')";
        PrimeFaces.current().executeScript(mensaje);
        return false;   
    }
    
    public void resetearCampos() {
        this.resultado = 0;
        this.limite = 0;
        this.usuario = "";
    }
    
    // getters y setters
    
    public RegistroService getRegistroService() {
        return registroService;
    }

    public void setRegistroService(RegistroService registroService) {
        this.registroService = registroService;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
    
    
    
}
