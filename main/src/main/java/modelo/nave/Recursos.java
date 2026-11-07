package modelo.nave;

public class Recursos {
    
    int combustible;
    int energia;
    int desgaste;
    int gemas;
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
        gemas = 0;
        //mantenimiento = ?
    }
    /**
     *<b>Pre:</b> Nave != nulo <br>
     *<b>Post:</b> Crea objeto recursos con la cantidad actual que guarda la nave<br>  
     * @param nave 
     */
    public Recursos(Nave nave) {
        this.combustible = nave.recursos.getCombustible();
        this.energia = nave.recursos.getEnergia();
        this.desgaste = nave.recursos.getDesgaste();
        this.gemas = nave.recursos.getGemas();
    }
    
    //-------------------------------------------
    //          ADICIONALES
    //-------------------------------------------
    
    public void imprimeRecursos(){
        System.out.println("Combustible: " + combustible);
        System.out.println("Energia: " + energia);
        System.out.println("Desgaste " + desgaste);
        System.out.println("Gemas " + gemas);
    }
    
    
    
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
    
    public int getGemas() {
        return gemas;
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
    
    public void setGemas(int gemas) {
        this.gemas = gemas;
    }
    
    public void consumeCombustible(int consumo){
        this.combustible -= consumo;
    }
    
    public void consumeEnergia(int consumo){
        this.energia -= consumo;
    }
    
    public void consumeDesgaste(int consumo){
        this.desgaste += consumo;
    }
    
    
    public void cargaCombustible(int valor){
        if (valor > 0 && valor + combustible <= 100) {
            combustible += valor;
        } else {
            System.out.println("[ERROR] Imposible cargar");
        }
    }
    
    public void cargaEnergia(int valor){
        if (valor > 0 && valor + energia <= 100){
            energia += valor;
        } else {
            System.out.println("[ERROR] Imposible cargar");
        }
    }
    
    public void cargaGemas(int valor) {
        if (valor > 0)
            gemas += valor;
        else 
            System.out.println("[ERROR] Imposible cargar");
    }
    
    public void realizaMantenimiento(){
        desgaste = 0;
    }
    
    //
    //public void cargaEnergia(int carga){
    //    this.energia += carga;
    //}
    
    
}
