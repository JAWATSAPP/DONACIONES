/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.donacion.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author FLHORIAN
 */
@Entity
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private String producto;
    private String tipo_producto;
    private Double cantidad;
    private String tipo_medida;
    private String nombre_donante;
    private String telefono_donante;
    private String direccion_donante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo_medida() {
        return tipo_medida;
    }

    public void setTipo_medida(String tipo_medida) {
        this.tipo_medida = tipo_medida;
    }

    public String getNombre_donante() {
        return nombre_donante;
    }

    public void setNombre_donante(String nombre_donante) {
        this.nombre_donante = nombre_donante;
    }

    public String getTelefono_donante() {
        return telefono_donante;
    }

    public void setTelefono_donante(String telefono_donante) {
        this.telefono_donante = telefono_donante;
    }

    public String getDireccion_donante() {
        return direccion_donante;
    }

    public void setDireccion_donante(String direccion_donante) {
        this.direccion_donante = direccion_donante;
    }

   
    

}
