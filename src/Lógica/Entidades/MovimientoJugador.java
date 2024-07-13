package Lógica.Entidades;

import Lógica.Enumeraciones.Dirección;
import Lógica.Servicios.AdministradorEventoTeclas;
import Lógica.Servicios.VerificadorColisiones;
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

    if (dirección == Dirección.DERECHA) {
      moverDerecha();
    } else if (dirección == Dirección.IZQUIERDA) {
      moverIzquierda();
    }

    VerificadorColisiones.verificarColisionesJugador(anchoNave, this);
  }
}
