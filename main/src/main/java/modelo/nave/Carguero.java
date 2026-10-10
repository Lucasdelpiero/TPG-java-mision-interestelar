package modelo.nave;

public class Carguero extends Nave {
    private static final int combustibleInicial = 100;
    private static final int energiaInicial = 60;
    private static final int desgasteInicial = 0;
    
    public Carguero(){
        super(combustibleInicial, energiaInicial, desgasteInicial);
    }
    
}
