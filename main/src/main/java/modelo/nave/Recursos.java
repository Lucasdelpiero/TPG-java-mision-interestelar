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
    
    /**
     * <b>PRE</b>
     * - consumo mayor o igual a 0.
     * <b>POST</b>
     * - Disminuye el combustible en la cantidad indicada.
     */
    public void consumeCombustible(int consumo){
        this.combustible -= consumo;
    }
    
    /**
     * <b>PRE</b>
     * - consumo mayor o igual a 0.
     * <b>POST</b>
     * - Disminuye la energía en la cantidad indicada.
     */
    public void consumeEnergia(int consumo){
        this.energia -= consumo;
    }
    
    /**
     * <b>PRE</b>
     * - consumo mayor o igual a 0.
     * <b>POST</b>
     * - Incrementa el desgaste en la cantidad indicada.
     */
    public void consumeDesgaste(int consumo){
        this.desgaste += consumo;
    }
    
    /**
     * <b>POST</b>
     * - Incrementa el combustible en la cantidad indicada si es positivo y no supera el máximo de 100.
     */
    public void cargaCombustible(int valor){
        if (valor > 0 && valor + combustible <= 100) {
            combustible += valor;
        } else {
            System.out.println("[ERROR] Imposible cargar");
        }
    }
    
    /**
     * <b>POST</b>
     * - Incrementa la energía en la cantidad indicada si es positivo y no supera el máximo de 100.
     */
    public void cargaEnergia(int valor){
        if (valor > 0 && valor + energia <= 100){
            energia += valor;
        } else {
            System.out.println("[ERROR] Imposible cargar");
        }
    }
    
    /**
     * <b>POST</b>
     * - Incrementa las gemas recolectadas si el valor es positivo.
     */
    public void cargaGemas(int valor) {
        if (valor > 0)
            gemas += valor;
        else 
            System.out.println("[ERROR] Imposible cargar");
    }
    
    /**
     * <b>POST</b>
     * - Restablece el desgaste mecánico de la nave a 0.
     */
    public void realizaMantenimiento(){
        desgaste = 0;
    }
    
    //
    //public void cargaEnergia(int carga){
    //    this.energia += carga;
    //}
    
    
}
