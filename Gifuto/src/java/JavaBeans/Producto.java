/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

import java.io.Serializable;

/**
 *
 * @author Noe
 */
public class Producto implements Serializable{
    private int categoria;
    private int id;
    private String nombre;
    private String descripcion;
    private boolean nuevo;
    private boolean visible;
    private boolean recomendado;
    private int existencia;
    private double costo;
    private double costoNuevo;
    private String foto;

    //Regitrar

    public Producto() {
    }
    
    

    public Producto(int categoria, String nombre, String descripcion, boolean visible, boolean recomendado, int existencia, double costo, String foto) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.visible = visible;
        this.recomendado = recomendado;
        this.existencia = existencia;
        this.costo = costo;
        this.foto = foto;
    }
   
     
   //consultar-actualizar

    
    
    //eliminar-visible
    
    
    
    //gettes and setters

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isRecomendado() {
        return recomendado;
    }

    public void setRecomendado(boolean recomendado) {
        this.recomendado = recomendado;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCostoNuevo() {
        return costoNuevo;
    }

    public void setCostoNuevo(double costoNuevo) {
        this.costoNuevo = costoNuevo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
}
