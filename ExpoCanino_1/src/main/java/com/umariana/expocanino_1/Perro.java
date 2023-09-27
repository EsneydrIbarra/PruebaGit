/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.expocanino_1;


import java.io.Serializable;
/**
 *
 * @author esney
 */
//Implementando la interffaz seirializable para que permita la serializacion
public class Perro implements Serializable {
    
    //creamos los atributos
    private String nombre;
    private String raza;
    private String foto;
    private int puntos;
    private int edad;

    //creacion del constructor vacio
    public Perro() {
    }
    //creacion del metodo constructor con todo los parametros
    /**
     * 
     * @param nombre
     * @param raza
     * @param foto
     * @param puntos
     * @param edad 
     */
    public Perro(String nombre, String raza, String foto, int puntos, int edad) {
        this.nombre = nombre;
        this.raza = raza;
        this.foto = foto;
        this.puntos = puntos;
        this.edad = edad;
    }
    //creacion de los getters and setters, de los atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }    
    
    
}

