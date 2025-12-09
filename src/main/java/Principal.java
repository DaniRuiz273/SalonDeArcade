import model.Jugador;
import model.MaquinaArcade;
import model.SalaRecreativa;
import utils.Utils;
import utils.Vista;
import java.util.Scanner;
public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opciones = 0;
        SalaRecreativa ElTemploDelArcade = new SalaRecreativa();

        Jugador j1 = new Jugador("Daniel", "0001", 20);
        Jugador j2 = new Jugador("Manuel", "0002", 15);
        Jugador j3 = new Jugador("María", "0003", 30);
        ElTemploDelArcade.addJugador(j1);
        ElTemploDelArcade.addJugador(j2);
        ElTemploDelArcade.addJugador(j3);

        MaquinaArcade pinball = new MaquinaArcade("Pinball", "Juego de salón", 10);
        MaquinaArcade DonkeyKong = new MaquinaArcade("DonkeyKong", "Plataformas", 20);
        ElTemploDelArcade.addMaquina(pinball);
        ElTemploDelArcade.addMaquina(DonkeyKong);

        pinball.cambiarEstado(0);
        DonkeyKong.cambiarEstado(1);

        do {
            System.out.println("----- EL TEMPLO DEL ARCADE -----");
            System.out.println("0. SALIR");
            System.out.println("1. Registrar un nuevo jugador");
            System.out.println("2. Registrar una nueva máquina arcade");
            System.out.println("3. Recargar créditos de un jugador");
            System.out.println("4. Listar jugadores");
            System.out.println("5. Listar máquinas");
            System.out.println("6. Listar máquinas activas");
            System.out.println("7. Realizar mantenimiento a una máquina (reactivarla)");
            System.out.println("8. Jugar una partida (Introduciendo ID y máquina)");
            System.out.println("9. Mostrar el jugador más activo");
            System.out.println("10. Mostrar la máquina más usada");
            System.out.println("11. Mostra el ranking de una máquina concreta");
            opciones = Utils.pideEnteroEntreValores("Introduce una opción entre 0 y 11: ", "Error, debes introducir un entero entre 0 y 11", 0, 11);
            switch (opciones){
                case 1:
                    ElTemploDelArcade.addJugador(Vista.registraJugador());
                    break;

                case 2:
                    ElTemploDelArcade.addMaquina(Vista.registraMaquina());
                    break;

                case 3: ;
                    Vista.recargarCreditos(ElTemploDelArcade);
                    break;

                case 4:
                    System.out.println(ElTemploDelArcade.listarJugadores());
                    break;

                case 5:
                    System.out.println(ElTemploDelArcade.listarMaquinas());
                    break;

                case 6:
                    System.out.println(ElTemploDelArcade.listarMaquinasActivas());
                    break;

                case 7:
                    pinball.cambiarEstado(1);
                    break;

                case 8:
                    ElTemploDelArcade.gestionarPartida(ElTemploDelArcade.buscarIDJugador("0002"), ElTemploDelArcade.buscarNombreMaquina("pinball"));
                    break;
            }

        }while (opciones != 0);
    }
}