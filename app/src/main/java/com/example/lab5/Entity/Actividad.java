package com.example.lab5.Entity;

import java.io.Serializable;

public class Actividad implements Serializable {
    private String fechaFin;
    private String horaFin;
    private String fechaInicio;
    private String horaInicio;
    private String titulo;
    private String descripcion;
    private String filename;
    private String key;

    public Actividad() {
    }

    public Actividad(String fechaFin, String horaFin, String fechaInicio, String horaInicio, String titulo, String descripcion) {
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }



    public Actividad(String fechaFin, String horaFin, String fechaInicio, String horaInicio, String titulo, String descripcion, String filename, String key) {
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.filename = filename;
        this.key = key;
    }

    public String getDetalle(){
        return "Fecha de inicio: "+this.fechaInicio+"\n"+
                "Hora de inicio: "+this.horaInicio+"\n"+
                "Fecha de fin: "+this.fechaFin+"\n"+
                "Hora de fin: "+this.horaFin+"\n";
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
