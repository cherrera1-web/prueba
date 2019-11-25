/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.indracompany.prueba.presentacion;

import com.indracompany.prueba.entidades.Registro;
import com.indracompany.prueba.logica.ConsultaService;
import com.indracompany.prueba.logica.RegistroService;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author cristian
 */
@ViewScoped
@ManagedBean
public class ConsultaBean implements Serializable {
    private String usuario;
    private int minimo;
    private int maximo;
    private ArrayList<Registro> registros;
    @EJB
    private ConsultaService consultaService;

    
    public void consultar() {
        
        if(!this.validacionEsExitosa()) {
            this.resetearCampos();
            return;
        }
        
        this.registros = this.consultaService.consultar(this.usuario, this.minimo, this.maximo);
        
        if (this.registros == null) {
            PrimeFaces.current().executeScript("alert('No hay registros encontrados!!')");
        }
    }
    
    
    public boolean validacionEsExitosa() {
        
        if(!this.consultaService.aceptar(this.minimo, this.maximo)){
            PrimeFaces.current().executeScript("alert('Error, El valor minimo es mayor o igual que el máximo!!')");
            return false;
        }
        return true;
    }
    
    
    public void resetearCampos() {
        this.usuario = "";
        this.minimo = 0;
        this.maximo = 0;
        this.registros = null;
    }
    
    
    
    // getters y setters
    public ConsultaService getConsultaService() {
        return consultaService;
    }

    public void setConsultaService(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }
}
