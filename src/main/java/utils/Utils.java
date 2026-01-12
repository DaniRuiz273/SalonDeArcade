package utils;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Utils {

    /**
     * Método con el que validamos que los créditos siempre acaben en 0 o en 5
     * @param precio Los créditos que introduce el usuario
     * @return Devuelve True si el precio introducido es válido y False si no lo es
     */
    public static boolean precioValido (int precio){
        return precio > 0 && (precio % 10 == 0 || precio % 10 == 5); // Comprobamos que el precio sea mayor que 0 y que el precio acabe en 0 o en 5
    }

    /**
     * Método con el que comprobamos que en una cadena no se haya introducido numeros
     * @param cadena Cadena que queremos validar
     * @return Devuelve True si se ha validado correctamente y False si no se puede validar
     */
    public static boolean cadenaValida(String cadena) {
        if (cadena == null || cadena.trim().isEmpty()) { // Evitamos nombre vacíos o solo con espacios
            return false;
        }
        for (int i = 0; i < cadena.length(); i++) { // Recorremos el nombre letra por letra
            char c = cadena.charAt(i); // Cogemos el carácter en la posición i
            if (!Character.isLetter(c) && c != ' ') { // Aquí devuelve True si es una letra. También permite los espacio entre nombres
                return false;
            }
        }
        return true;
    }

    /**
     * Método con el que pedimos un entero entre dos valores
     * @param mensaje Mensaje donde indica que introduzcas un entero entre los dos valores
     * @param mensajeError Si introduces otra cosa que no sea un entero entre los dos valores
     * @param numeroMin Valor mínimo del intervalo
     * @param numeroMax Valor máximo del intervalo
     * @return Devuelve el entero
     */
    public static int pideEnteroEntreValores(String mensaje, String mensajeError, int numeroMin, int numeroMax){
        int numero;
        do {
            numero = pideEntero(mensaje, mensajeError); // Usamos el pide entero anterior para que ahora sea el número
            if(numero<numeroMin||numero>numeroMax){ // Comprobamos que el número introducido no sea menor ni mayor que el rango introducido
                System.out.println(mensajeError); // Imprimimos el mensaje de error por pantalla
            }
        } while(numero<numeroMin||numero>numeroMax); // Seguimos en el bucle mientras el número sea menor o mayor que el rango establecido
        return numero; // Devolvemos el número ya comprobado
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
        boolean noHayError;
        do {
            System.out.println(mensaje); // Imprimimos por pantalla el mensaje que queremos indicar
            try {
                numero = sc.nextInt(); // El número es igual al scanner
                noHayError = true; // Devolvemos true
            } catch (InputMismatchException e) { // Cogemos el error que nos produce
                System.out.println(mensajeError); // Imprimimos por pantalla el mensaje de error
                noHayError = false; // Devolvemos false
                sc.nextLine(); // Limpiar buffer
            }
        } while (!noHayError); // Seguimos en el bucle mientras que noHayError no sea true
        return numero; // Devolvemos el número que hemos introducido
    }

    /**
     * Método con el que comprobamos que un entero este dentro de un rango
     * @param valor Entero que queremos comprobar
     * @param numeroMin Valor minimo del intervalo
     * @param numeroMax Valor maximo del intervalo
     * @return Devuelve true si está dentro del rango y false si no lo está
     */
    public static boolean comprobarRango(int valor, int numeroMin, int numeroMax) {
        return valor >= numeroMin && valor <= numeroMax; // Comprobamos que el valor introducido esté entre los valores establecidos
    }

    /**
     * Método con el que generamos un numero aleatorio dentro de un rango
     * @param numMin Valor mínimo del rango
     * @param numMax Valor máximo del rango
     * @return Devuelve el número aleatorio generado
     */
    public static int generaNumeroAleatorio (int numMin, int numMax){
        return (int)(Math.random()*(numMax-numMin+1)+numMin); // Generamos un número aleatorio entre un rango de valores establecido
    }

    /**
     * Método con el que comprobamos que lo introducido por el usuario es una cadena
     * @param msn Mensaje
     * @param msnError Mensaje de error por si no se ha introducido una cadena
     * @return Devuelve la cadena ya validada
     */
    public static String pideCadena (String msn, String msnError){
        Scanner sc = new Scanner(System.in);
        String cadena = "";
        boolean esValido = false;

        while (!esValido){
            System.out.println(msn); // Imprimimos por pantalla un mensaje con lo que queramos pedir
            cadena = sc.nextLine().trim(); // Hacemos que la cadena sea igual al scanner, por lo que lee lo que haya escrito el usuario por pantalla

            if(!cadena.isEmpty()){ // Si la cadena es distinto de 0
                esValido = true; // Devolvemos true
            } else {
                System.out.println(msnError); // Si no es distinto de 0, imprimimos por pantalla un mensaje de error
            }
        }
        return cadena; // Devolvemos la cadena ya comprobada
    }
}