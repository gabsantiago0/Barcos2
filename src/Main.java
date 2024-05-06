import models.Barco;
import models.Puerto;

public class Main {

    public static void main(String[] args) {

        Puerto puerto = new Puerto(1L);

        for(int i = 0; i <= 50;i++){
            Barco barco = new Barco("Barco_"+i,puerto);
            barco.start();
        }



    }


}
