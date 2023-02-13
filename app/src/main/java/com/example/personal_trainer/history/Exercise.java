package com.example.personal_trainer.history;

public class Exercise {

    private int id;
    private String name;
    private String calorias;
    private String duracion;

    public Exercise(int id, String name, String duracion,String calorias) {
        this.id = id;
        this.name = name;
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
        return name;
    }

    public void setNombreEjercicio(String name) {
        this.name = name;
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
                ", nombreEjercicio='" + name + '\'' +
                ", calorias=" + calorias +
                ", duracion='" + duracion + '\'' +
                '}';
    }

}
