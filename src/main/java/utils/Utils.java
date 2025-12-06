package utils;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Utils {

    /**
     * Método con el que validamos todos los IDUnicos de cada jugador
     * @param idUnico El ID unico de cada jugador
     * @return Devuelves que el ID unico del jugador es correcto
     */
    public static boolean validaIdUnico (String idUnico){
        boolean valido = false;
        if(idUnico.length() == 4){
            valido = true;
        }
        return valido;
    }

    /**
     * Método para pedir un entero por consola sin errores
     * @param mensaje Mensaje para pedir un entero
     * @param mensajeError Mensaje de error por si el usuario introduce otra cosa que no sea un entero
     * @return Devuelve el número introducido por el usuario
     */
    public static int pideEntero( String mensaje, String mensajeError){
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        boolean noHayError = true;
        do {
            System.out.println(mensaje);
            try {
                numero = sc.nextInt();
                noHayError = true;
            } catch (InputMismatchException e) {
                System.out.println(mensajeError);
                noHayError = false;
                sc.nextLine(); // Limpiar buffer
            }
        } while (!noHayError);
        return numero;
    }

    /**
     * Método para comprobar si un número esta dentro del rango indicado
     * @param valor Entero introducido por el usuario
     * @param numeroMin Valor mínimo del rango
     * @param numeroMax Valor máximo del rango
     * @return Devuelve true si está dentro del rango y false si está fuero del rango
     */
    public static boolean comprobarRango(int valor, int numeroMin, int numeroMax){;
        boolean esValido;
        if(valor < numeroMin || numeroMax > valor){
            return esValido = false;
        } else {
            return esValido = true;
        }
    }

    /**
     * Método con el que generamos un numero aleatorio dentro de un rango
     * @param numMin Valor mínimo del rango
     * @param numMax Valor máximo del rango
     * @return Devuelve el número aleatorio generado
     */
    public static int generaNumeroAleatorio (int numMin, int numMax){
        return (int)(Math.random()*(numMax-numMin+1)+numMin);
    }
}
