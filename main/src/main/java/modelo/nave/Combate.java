package modelo.nave;

import modelo.universo.ElementoDelUniverso;

public class Combate extends Nave {
    private static final int combustibleInicial = 80;
    private static final int energiaInicial = 100;
    private static final int desgasteInicial = 0;
    
    public Combate(){
        super(combustibleInicial, energiaInicial, desgasteInicial);
    }

    @Override
    public void chocaCarguera(Nave nave) {
        this.destruirNave();
    }

    @Override
    public void chocaExploradora(Nave nave) {
        nave.destruirNave();
    }

    @Override
    public void chocaCombate(Nave nave) {
    }

    @Override
    public void choqueNave(Nave nave) {
        nave.chocaCombate(this);
    }
    
}
