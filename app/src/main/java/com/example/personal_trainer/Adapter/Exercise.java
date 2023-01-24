package com.example.personal_trainer.Adapter;

public class Exercise {

    private int id;
    private String nombreEjercicio;
    private String calorias;
    private String duracion;

    public Exercise(int id, String nombreEjercicio, String duracion,String calorias) {
        this.id = id;
        this.nombreEjercicio = nombreEjercicio;
        this.calorias = calorias;
        this.duracion = duracion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }



    @Override
    public String toString() {
        return "Ejercicio{" +
                "id=" + id +
                ", nombreEjercicio='" + nombreEjercicio + '\'' +
                ", calorias=" + calorias +
                ", duracion='" + duracion + '\'' +
                '}';
    }

}
