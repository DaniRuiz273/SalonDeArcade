import model.MaquinaArcade;
import utils.Utils;
public class Principal {
    public static void main(String[] args) {

        MaquinaArcade pingball = new MaquinaArcade("Pingball", "Juegos de salón", 20);
        MaquinaArcade DonkeyKong = new MaquinaArcade("Donkey Kong", "Plataforma", 15);

        pingball.cambiarEstado(1);
        pingball.imprimirEstado();

        DonkeyKong.cambiarEstado(0);
        DonkeyKong.imprimirEstado();

        System.out.println(pingball.nuevaPartida());
        System.out.println(pingball.nuevaPartida());
        System.out.println(pingball.nuevaPartida());

        System.out.println(pingball);

    }
}