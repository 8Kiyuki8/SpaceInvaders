package Lógica.Entidades;

import Lógica.Enumeraciones.Movimiento;
import Lógica.Servicios.AdministradorEventoTeclas;
import Presentación.Ventanas.VentanaAdministradora;

public class NaveJugador extends Nave {
  private AdministradorEventoTeclas administradorTeclas;
  private int posiciónX, posiciónY;
  private final int velocidad = 4;

  public NaveJugador(AdministradorEventoTeclas administradorTeclas) {
    this.administradorTeclas = administradorTeclas;
    posiciónX = (VentanaAdministradora.obtenerAnchoVentana() / 2) - 25;
    posiciónY = VentanaAdministradora.obtenerAltoVentana() - 100;
  }

  public void actualizarMovimiento() {
    Movimiento dirección = administradorTeclas.obtenerDirecciónMovimiento();
    if (dirección == null) {
      return;
    }

    if (dirección == Movimiento.DERECHA) {
      posiciónX += velocidad;
    } else if (dirección == Movimiento.IZQUIERDA) {
      posiciónX -= velocidad;
    }
  }

  public int obtenerPosiciónX() {
    return posiciónX;
  }

  public int obtenerPosiciónY() {
    return posiciónY;
  }
}
