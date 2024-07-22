package Lógica.Entidades;

import Lógica.Enumeraciones.Dirección;
import Lógica.Servicios.VerificadorColisiones;
import Presentación.Ventanas.VentanaAdministradora;

public class MovimientoJugador extends Movimiento {
  private static final int VELOCIDAD_JUGADOR_POR_DEFECTO = 5;

  @Override
  public void actualizarMovimiento(Posición posición, Dirección dirección) {

    if (dirección == Dirección.DERECHA) {
      moverDerecha(posición, VELOCIDAD_JUGADOR_POR_DEFECTO);
    } else if (dirección == Dirección.IZQUIERDA) {
      moverIzquierda(posición, VELOCIDAD_JUGADOR_POR_DEFECTO);
    }

    VerificadorColisiones.verificarColisionesConLosBordes(VentanaAdministradora.obtenerTamañoEntidad(), posición);
  }

}
