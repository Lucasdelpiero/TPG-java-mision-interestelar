package modelo.mision;
import modelo.universo.Universo;

public class FactoryMision {
   
    public Mision crearMision(String texto,Universo universo) {
        Mision.tipoMision tipo = Mision.tipoMision.valueOf(texto.trim().toUpperCase());
        switch (tipo) {
        case INTERCEPCION:
            return new MisionIntercepcionAsistencia(universo);

        case RECOLECCION:
            return new MisionRecoleccion(universo);

        case RETORNO_SEGURO:
            return new MisionRetornoSeguro(universo);

        default:
            throw new IllegalArgumentException("Tipo de misión inválido");
        }
    }
}
