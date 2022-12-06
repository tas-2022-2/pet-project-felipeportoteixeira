package felipe.Controller;

import java.util.Random;

public class CaraCoroa {
    
    private Random gerador;
    
    public CaraCoroa(){

        gerador = new Random();
        

    }
    
    private int sorteio(){

        return gerador.nextInt(10);

    }
    
    public void jogar(int vezes){

        int r;
        int cara=0;
        int coroa=0;

        for (int i = 0; i < vezes; i++) {

            r = this.sorteio();

            if(r>=5)
                cara++;
            else
                coroa++;
        }

        System.out.println("Caras: "+cara+" Coroas: "+coroa);
        
    }

}
