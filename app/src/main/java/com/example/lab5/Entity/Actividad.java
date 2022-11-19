package com.example.lab5.Entity;

public class Actividad {
    private String fechaFin;
    private String horaFin;
    private String fechaInicio;
    private String horaInicio;
    private String titulo;
    private String descripcion;

    public Actividad(String fechaFin, String horaFin, String fechaInicio, String horaInicio, String titulo, String descripcion) {
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
