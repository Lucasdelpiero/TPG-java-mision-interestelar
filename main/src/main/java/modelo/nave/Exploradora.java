package modelo.nave;

public class Exploradora extends Nave {
    private static final  int combustibleInicial = 60;
    private static final int energiaInicial = 80;
    private static final int desgasteInicial = 0;
    
    
    public Exploradora(){
        super(combustibleInicial, energiaInicial, desgasteInicial);
    }
    
}
