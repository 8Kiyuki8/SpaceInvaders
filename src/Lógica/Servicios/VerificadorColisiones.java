package Lógica.Servicios;

import Lógica.Entidades.Posición;
import Presentación.Ventanas.VentanaAdministradora;

public class VerificadorColisiones {
  public static boolean verificarColisionesConLosBordes(Posición posiciónEntidad) {
    if (posiciónEntidad.obtenerPosiciónX() < 0) {
      posiciónEntidad.establecerPosiciónX(0);
      return false;
    } else if (posiciónEntidad.obtenerPosiciónX() > VentanaAdministradora.obtenerAnchoVentana() - VentanaAdministradora.obtenerTamañoEntidad()) {
      posiciónEntidad.establecerPosiciónX(VentanaAdministradora.obtenerAnchoVentana() - VentanaAdministradora.obtenerTamañoEntidad());
      return false;
    }
    return true;
  }
}
