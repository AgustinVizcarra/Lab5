package com.example.lab5;

public class Actividad {
    String fechaFin;
    String horaFin;
    String fechaInicio;
    String horaInicio;
    String titulo;
    String descripcio;

    public Actividad(String fechaFin, String horaFin, String fechaInicio, String horaInicio, String titulo, String descripcio) {
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.titulo = titulo;
        this.descripcio = descripcio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
}
