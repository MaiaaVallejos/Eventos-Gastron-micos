package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;

public class Chef {

    private int id;
    private String nombre;
    private String especialidad;
    private List<EventoGastronomico> eventosParticipados;

    public Chef(int id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.eventosParticipados = new ArrayList<>();
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<EventoGastronomico> getEventosParticipados() {
        return eventosParticipados;
    }

    public void setEventosParticipados(List<EventoGastronomico> eventosParticipados) {
        this.eventosParticipados = eventosParticipados;
    }

    public void agregarEvento(EventoGastronomico evento) {
        eventosParticipados.add(evento);
    }
}
