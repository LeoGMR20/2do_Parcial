package com.example.a2doparcial_leandromoralesrada;

import java.io.Serializable;

public class Cliente implements Serializable {

    //Atributos

    private String nombre;
    private String apellido;
    private int edad;
    private String ci;
    private String pais;
    private boolean interesado;

    //Constructor

    public Cliente(String nombre, String apellido, int edad, String ci, String pais, boolean interesado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ci = ci;
        this.pais = pais;
        this.interesado = interesado;
    }

    //Geters


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getCi() {
        return ci;
    }

    public String getPais() {
        return pais;
    }

    public boolean isInteresado() {
        return interesado;
    }

    @Override
    public String toString() {
        return "Cliente" +
                "\nNombre: " + nombre +
                "\nApellido: " + apellido +
                "\nEdad: " + edad +
                "\nCI: " + ci +
                "\nPais: " + pais +
                "\nInteresado: " + interesado;
    }
}
