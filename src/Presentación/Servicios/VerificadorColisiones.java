package Presentación.Servicios;

import Lógica.Entidades.Posición;
import Presentación.Ventanas.VentanaJuego;

public class VerificadorColisiones {
  public static boolean verificarColisionesConLosBordes(Posición posiciónEntidad) {
    if (posiciónEntidad.obtenerPosiciónX() < 0) {
      posiciónEntidad.establecerPosiciónX(0);
      return false;
    } else if (posiciónEntidad.obtenerPosiciónX() > VentanaJuego.obtenerAnchoVentana() - VentanaJuego.obtenerTamañoEntidad()) {
      posiciónEntidad.establecerPosiciónX(VentanaJuego.obtenerAnchoVentana() - VentanaJuego.obtenerTamañoEntidad());
      return false;
    }
    return true;
  }
}
