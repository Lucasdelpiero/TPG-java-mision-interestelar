package modelo.nave;

public class Combate extends Nave {
    private static final int combustibleInicial = 80;
    private static final int energiaInicial = 100;
    private static final int desgasteInicial = 0;
    
    public Combate(){
        super(combustibleInicial, energiaInicial, desgasteInicial);
    }
    
}
