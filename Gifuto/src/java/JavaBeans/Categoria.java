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
public class Categoria implements Serializable{
    
    private int id;
    private String nombre;
    private boolean visible;
    private int categoria_Superior;

    public Categoria(int id, String nombre, boolean visible, int categoria_Superior) {
        this.id = id;
        this.nombre = nombre;
        this.visible = visible;
        this.categoria_Superior = categoria_Superior;
    }

    public Categoria() {
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getCategoria_Superior() {
        return categoria_Superior;
    }

    public void setCategoria_Superior(int categoria_Superior) {
        this.categoria_Superior = categoria_Superior;
    }
    
    
    
    
    
    
}
