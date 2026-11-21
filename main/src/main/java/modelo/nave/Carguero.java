package modelo.nave;

import modelo.universo.ElementoDelUniverso;

public class Carguero extends Nave {
    private static final int combustibleInicial = 100;
    private static final int energiaInicial = 60;
    private static final int desgasteInicial = 0;
    
    public Carguero(){
        super(combustibleInicial, energiaInicial, desgasteInicial);
    }

    @Override
    public void chocaCarguera(Nave nave) {        
    }

    @Override
    public void chocaExploradora(Nave nave) {
        this.destruirNave();
    }

    @Override
    public void chocaCombate(Nave nave) {
        nave.destruirNave();
    }

    @Override
    public void choqueNave(Nave nave) {
        nave.chocaCarguera(this);
    }
    
}
