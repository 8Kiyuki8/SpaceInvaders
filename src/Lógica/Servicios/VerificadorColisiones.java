package Lógica.Servicios;

import Lógica.Entidades.Posición;
import Presentación.Ventanas.VentanaAdministradora;

public class VerificadorColisiones {
  public static boolean verificarColisionesConLosBordes(int anchoEntidad, Posición posiciónEntidad) {

    if (posiciónEntidad.obtenerPosiciónX() < 0) {
      posiciónEntidad.establecerPosiciónX(0);
      return false;
    } else if (posiciónEntidad.obtenerPosiciónX() > VentanaAdministradora.obtenerAnchoVentana() - anchoEntidad) {
      posiciónEntidad.establecerPosiciónX(VentanaAdministradora.obtenerAnchoVentana() - anchoEntidad);
      return false;
    }
    return true;
  }
}
