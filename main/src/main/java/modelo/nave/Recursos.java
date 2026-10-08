package modelo.nave;

public class Recursos {
    
    int combustible;
    int energia;
    int desgaste;
    //int mantenimiento;
    
    //-------------------------------------------
    //          CONSTRUCTOR
    //-------------------------------------------
    
    /**
    *   PRE:
    *       Combustible: Valor entero mayor a 0
    *       Energia: Valor entero mayor a 0
    *       Desgaste: Valor entero igual a 0
    */
    public Recursos(int combustible, int energia, int desgaste){
        this.combustible = combustible;
        this.energia = energia;
        this.desgaste = desgaste;
        //mantenimiento = ?
    }
    
    //-------------------------------------------
    //          ADICIONALES
    //-------------------------------------------

    
    
    
    //-------------------------------------------
    //          GETTERS / SETTERS
    //-------------------------------------------

    public int getCombustible() {
        return combustible;
    }

    public int getEnergia() {
        return energia;
    }

    public int getDesgaste() {
        return desgaste;
    }

    
    //-----------------------------------
    
    
    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setDesgaste(int desgaste) {
        this.desgaste = desgaste;
    }
    
    
    
}
