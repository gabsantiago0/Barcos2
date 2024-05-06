package models;

import java.util.ArrayList;

public class Puerto {

    public static final int MAX_DEPORTIVOS = 15;
    public static final int MAX_CRUCEROS = 2;
    public static final int MAX_MERCANCIAS = 7;

    private Long id;
    private ArrayList<Barco> muelle;


    public Puerto(Long id) {
        this.id = id;
        this.muelle = new ArrayList<>();
    }

    public synchronized void atracar(Barco barco) {
        while (!barco.isAparcado()) {
            while (contarBarcos(barco) >= retornarMaximo(barco)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //Entra
            muelle.add(barco);
            System.out.println(barco.getNombre() + "-[" + barco.getTipo() + "]-" + "atraca-" + contarBarcos(barco));
            barco.setAparcado(true);
            notifyAll();
        }

    }

    public synchronized void zarpar(Barco barco) {
        muelle.remove(barco);
        System.out.println(barco.getNombre() + "-[" + barco.getTipo() + "]-" + "zarpa-" + contarBarcos(barco));
        notifyAll();
    }

    public Long getId() {
        return id;
    }

    public synchronized Integer contarBarcos(Barco barco) {
        Integer contador = 0;
        for (Barco b : muelle) {
            if (b.getTipo().equals(barco.getTipo())) {
                contador++;
            }
        }
        return contador;
    }

    public synchronized Integer retornarMaximo(Barco barco) {
        int retorno = switch (barco.getTipo()) {
            case "DEPORTIVO" -> MAX_DEPORTIVOS;
            case "CRUCERO" -> MAX_CRUCEROS;
            case "MERCANCIA" -> MAX_MERCANCIAS;
            default -> 0;
        };
        return retorno;
    }

}
