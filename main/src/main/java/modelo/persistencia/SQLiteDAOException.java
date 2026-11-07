package modelo.persistencia;

public class SQLiteDAOException extends DAOException {

    public SQLiteDAOException(String mensaje) {
        super(mensaje);
    }

    public SQLiteDAOException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
