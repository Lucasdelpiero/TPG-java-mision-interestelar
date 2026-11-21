package modelo.nave;

import modelo.universo.ElementoDelUniverso;

public class Exploradora extends Nave {
    private static final  int combustibleInicial = 60;
    private static final int energiaInicial = 80;
    private static final int desgasteInicial = 0;
    
    
    public Exploradora(){
        super(combustibleInicial, energiaInicial, desgasteInicial);
    }

    @Override
    public void chocaCarguera(Nave nave) {
        nave.destruirNave();
    }

    @Override
    public void chocaExploradora(Nave nave) {
    }

    @Override
    public void chocaCombate(Nave nave) {
        this.destruirNave();
    }

    @Override
    public void choqueNave(Nave nave) {
        nave.chocaExploradora(this);
    }
    
}
