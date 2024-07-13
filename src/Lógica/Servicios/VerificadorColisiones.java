package Lógica.Servicios;

import Lógica.Entidades.MovimientoJugador;
import Lógica.Entidades.NaveJugador;
import Presentación.Ventanas.VentanaAdministradora;

public class VerificadorColisiones {
  public static void verificarColisionesJugador(int x, int y, int anchoNave, int altoNave, MovimientoJugador movimientoJugador) {
    if (x < 0) {
      movimientoJugador.establecerPosiciónX(0);
    } else if (x > VentanaAdministradora.obtenerAnchoVentana() - anchoNave) {
      movimientoJugador.establecerPosiciónX(VentanaAdministradora.obtenerAnchoVentana() - anchoNave);
    }

    if (y < 0) {
      movimientoJugador.establecerPosiciónY(0);
    } else if (y > VentanaAdministradora.obtenerAltoVentana() - altoNave) {
      movimientoJugador.establecerPosiciónY(VentanaAdministradora.obtenerAltoVentana() - altoNave);
    }
  }
}

