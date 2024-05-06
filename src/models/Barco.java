package models;

import java.util.Random;

public class Barco extends Thread {

    private String nombre;
    private String tipo;
    private Puerto puerto;
    private boolean aparcado;

    public Barco(String name, Puerto puerto) {
        this.nombre = name;
        this.puerto = puerto;
        this.tipo = generarTipo();
        this.aparcado = false;
    }

    private String generarTipo() {
        String t = "";
        int r = new Random().nextInt(3);
        t = switch (r) {
            case 0 -> "DEPORTIVO";
            case 1 -> "CRUCERO";
            case 2 -> "MERCANCIA";
            default -> t;
        };
        return t;
    }

    @Override
    public void run() {
        System.out.println(getNombre()+"-["+getTipo()+"]-"+"solicita entrada");
        puerto.atracar(this);
        try {
            Thread.sleep(new Random().nextInt(1000+500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        puerto.zarpar(this);
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Puerto getPuerto() {
        return puerto;
    }

    public boolean isAparcado() {
        return aparcado;
    }

    public void setAparcado(boolean aparcado) {
        this.aparcado = aparcado;
    }
}
