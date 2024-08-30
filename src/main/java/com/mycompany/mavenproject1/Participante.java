package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;

public class Participante {

    private int id;
    private String nombre;
    private String apellido;
    private List<String> interesesCulinarios;
    private List<EventoGastronomico> historialEventos;

    public Participante(int id, String nombre, String apellido, List<String> interesesCulinarios) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.interesesCulinarios = interesesCulinarios;
        this.historialEventos = new ArrayList<>();
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<String> getInteresesCulinarios() {
        return interesesCulinarios;
    }

    public void setInteresesCulinarios(List<String> interesesCulinarios) {
        this.interesesCulinarios = interesesCulinarios;
    }

    public List<EventoGastronomico> getHistorialEventos() {
        return historialEventos;
    }

    public void setHistorialEventos(List<EventoGastronomico> historialEventos) {
        this.historialEventos = historialEventos;
    }

    public void agregarEventoAlHistorial(EventoGastronomico evento) {
        historialEventos.add(evento);
    }
}
