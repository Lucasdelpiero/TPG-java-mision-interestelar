package modelo.motorwarp;

public class MotorTransicionException extends Throwable{
    public String descripcion;
    public MotorTransicionException(String desc) {
        descripcion = desc;
    }
    
}
