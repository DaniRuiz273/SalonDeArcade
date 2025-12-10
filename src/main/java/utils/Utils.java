package utils;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Utils {

    /**
     * Método con el que pedimos un entero entre dos valores
     * @param mensaje Mensaje donde indica que introduzcas un entero entre los dos valores
     * @param mensajeError Si introduces otra cosa que no sea un entero entre los dos valores
     * @param numeroMin Valor mínimo del intervalo
     * @param numeroMax Valor máximo del intervalo
     * @return Devuelve el entero
     */
    public static int pideEnteroEntreValores(String mensaje, String mensajeError, int numeroMin, int numeroMax){
        int numero = 0;
        do {
            numero = pideEntero(mensaje, mensajeError);
            if(numero<numeroMin||numero>numeroMax){
                System.out.println(mensajeError);
            }
        } while(numero<numeroMin||numero>numeroMax);
        return numero;
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
     * Método con el que comprobamos que un entero este dentro de un rango
     * @param valor Entero que queremos comprobar
     * @param numeroMin Valor minimo del intervalo
     * @param numeroMax Valor maximo del intervalo
     * @return Devuelve true si está dentro del rango y false si no lo está
     */
    public static boolean comprobarRango(int valor, int numeroMin, int numeroMax) {
        return valor >= numeroMin && valor <= numeroMax;
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

    public static String pideCadena (String msn, String msnError){
        Scanner sc = new Scanner(System.in);
        String cadena = "";
        boolean esValido = false;

        while (!esValido){
            System.out.println(msn);
            cadena = sc.nextLine().trim();

            if(!cadena.isEmpty()){
                esValido = true;
            } else {
                System.out.println(msnError);
            }
        }
        return cadena;
    }
}
