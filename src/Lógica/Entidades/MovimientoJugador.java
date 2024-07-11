package Lógica.Entidades;

import Lógica.Enumeraciones.Dirección;
import Lógica.Servicios.AdministradorEventoTeclas;
import Presentación.Ventanas.VentanaAdministradora;

public class MovimientoJugador extends Movimiento {
  private final AdministradorEventoTeclas administradorTeclas;
  private final int anchoNave = VentanaAdministradora.obtenerTamañoEntidad();
  private final int altoNave = VentanaAdministradora.obtenerTamañoEntidad();

  public MovimientoJugador(AdministradorEventoTeclas administradorEventoTeclas) {
    administradorTeclas = administradorEventoTeclas;
    establecerPosiciónX((VentanaAdministradora.obtenerAnchoVentana() / 2) - (anchoNave / 2));
    establecerPosiciónY(VentanaAdministradora.obtenerAltoVentana() - (altoNave * 2));
  }

  @Override

  public void actualizarMovimiento() {
    Dirección dirección = administradorTeclas.obtenerDirecciónMovimiento();
    if (dirección == null) {
      return;
    }

    int nuevaPosiciónX = obtenerPosiciónX();
    if (dirección == Dirección.DERECHA) {
      moverDerecha();
      nuevaPosiciónX += obtenerPosiciónX();
    } else if (dirección == Dirección.IZQUIERDA) {
      moverIzquierda();
      nuevaPosiciónX -= obtenerPosiciónX();
    }

    if (nuevaPosiciónX < 0) {
      nuevaPosiciónX = 0;
    } else if (nuevaPosiciónX + anchoNave > VentanaAdministradora.obtenerAnchoVentana()) {
      nuevaPosiciónX = VentanaAdministradora.obtenerAnchoVentana() - anchoNave;
    }

    establecerPosiciónX(nuevaPosiciónX);
  }
}
