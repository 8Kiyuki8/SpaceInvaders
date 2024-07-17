package Lógica.Servicios;

import Lógica.Entidades.MovimientoEnemigo;
import Lógica.Entidades.MovimientoJugador;
import Presentación.Ventanas.VentanaAdministradora;

public class VerificadorColisiones {
  public static void verificarColisionesJugador(int anchoNave, MovimientoJugador movimientoJugador) {
    int x = movimientoJugador.obtenerPosiciónX();

    if (x < 0) {
      movimientoJugador.establecerPosiciónX(0);
    } else if (x > VentanaAdministradora.obtenerAnchoVentana() - anchoNave - 15) {
      movimientoJugador.establecerPosiciónX(VentanaAdministradora.obtenerAnchoVentana() - anchoNave - 15);
    }
  }

  public static void verificarColisionesEnemigo(int anchoEnemigo, MovimientoEnemigo movimientoEnemigo) {
    int x = movimientoEnemigo.obtenerPosiciónX();
    if (x < 0) {
      movimientoEnemigo.establecerPosiciónX(0);
    } else if (x > VentanaAdministradora.obtenerAnchoVentana() - anchoEnemigo - 336) {
      movimientoEnemigo.establecerPosiciónX(VentanaAdministradora.obtenerAnchoVentana() - anchoEnemigo - 336);
    }
  }

}
