package Lógica.Entidades;

import Lógica.Enumeraciones.Movimiento;
import Lógica.Servicios.AdministradorEventoTeclas;
import Presentación.Ventanas.VentanaAdministradora;

public class NaveJugador extends Nave {
  private AdministradorEventoTeclas administradorTeclas;
  private int posiciónX, posiciónY, nuevaPosiciónX;
  private final int velocidad = 4;
  private final int anchoNave = VentanaAdministradora.obtenerTamañoEntidad();
  private final int altoNave = VentanaAdministradora.obtenerTamañoEntidad();

  public NaveJugador(AdministradorEventoTeclas administradorTeclas) {
    this.administradorTeclas = administradorTeclas;
    posiciónX = (VentanaAdministradora.obtenerAnchoVentana() / 2) - (anchoNave / 2);
    posiciónY = VentanaAdministradora.obtenerAltoVentana() - (altoNave * 2);
  }

  public void actualizarMovimiento() {
    Movimiento dirección = administradorTeclas.obtenerDirecciónMovimiento();
    if (dirección == null) {
      return;
    }
    nuevaPosiciónX = posiciónX;
    if (dirección == Movimiento.DERECHA) {
      nuevaPosiciónX += velocidad;
    } else if (dirección == Movimiento.IZQUIERDA) {
      nuevaPosiciónX -= velocidad;
    }

    if (nuevaPosiciónX < 0) {
      nuevaPosiciónX = 0;
    } else if (nuevaPosiciónX + anchoNave > VentanaAdministradora.obtenerAnchoVentana()) {
      nuevaPosiciónX = VentanaAdministradora.obtenerAnchoVentana() - anchoNave;
    }

    posiciónX = nuevaPosiciónX;
  }

  public int obtenerPosiciónX() {
    return posiciónX;
  }

  public int obtenerPosiciónY() {
    return posiciónY;
  }
}
