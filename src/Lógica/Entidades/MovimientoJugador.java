package Lógica.Entidades;

import Lógica.Enumeraciones.Dirección;
import Lógica.Servicios.AdministradorEventoTeclas;
import Presentación.Ventanas.VentanaAdministradora;

public class MovimientoJugador extends Movimiento {
  private final AdministradorEventoTeclas administradorTeclas;

  public MovimientoJugador(AdministradorEventoTeclas administradorEventoTeclas) {
    administradorTeclas = administradorEventoTeclas;
    establecerPosiciónX((VentanaAdministradora.obtenerAnchoVentana() / 2) -
      (VentanaAdministradora.obtenerTamañoEntidad() / 2));
    establecerPosiciónY(VentanaAdministradora.obtenerAltoVentana() -
      (VentanaAdministradora.obtenerTamañoEntidad() * 2));
  }

  @Override
  public void actualizarMovimiento() {
    Dirección dirección = administradorTeclas.obtenerDirecciónMovimiento();
    if (dirección == null) {
      return;
    }

    if (dirección == Dirección.DERECHA) {
      moverDerecha();
    } else if (dirección == Dirección.IZQUIERDA) {
      moverIzquierda();
    }
  }
}
