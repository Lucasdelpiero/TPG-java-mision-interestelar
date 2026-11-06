package modelo.persistencia;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilidad básica de hashing SHA-256 para contraseñas de usuarios.
 */
public class SeguridadUtil {

    private SeguridadUtil() {
        // Clase utilitaria, no instanciable
    }

    /**
     * Aplica hashing SHA-256 a la contraseña plana dada y retorna la representación hexadecimal.
     *
     * @param password Contraseña en texto plano
     * @return Hash SHA-256 en formato hexadecimal, o null si la contraseña es nula
     */
    public static String hashear(String password) {
        if (password == null) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error inesperado: algoritmo SHA-256 no disponible", e);
        }
    }

    /**
     * Comprueba si una contraseña plana coincide con el hash almacenado.
     *
     * @param passwordPlana Contraseña ingresada por el usuario
     * @param hashAlmacenado Hash almacenado en la base de datos
     * @return true si coinciden, false en caso contrario
     */
    public static boolean verificar(String passwordPlana, String hashAlmacenado) {
        if (passwordPlana == null || hashAlmacenado == null) {
            return false;
        }
        String hashCalculado = hashear(passwordPlana);
        return hashCalculado.equalsIgnoreCase(hashAlmacenado);
    }
}

