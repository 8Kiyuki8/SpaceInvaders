package Lógica.Entidades;

import Lógica.Enumeraciones.Dirección;
import Lógica.Servicios.VerificadorColisiones;
import Presentación.Ventanas.VentanaAdministradora;

public class MovimientoEnemigo extends Movimiento {
  private boolean flagMoverDerecha = true;
  private static final int VELOCIDAD_ENEMIGO_POR_DEFECTO = 4;


  public void moverAbajo(Posición posición, int desplazamientoEntidad) {
    posición.establecerPosiciónY(posición.obtenerPosiciónY() + desplazamientoEntidad);
  }

  @Override
  public void actualizarMovimiento(Posición posición, Dirección dirección) {

    if ((VerificadorColisiones.verificarColisionesConLosBordes(VentanaAdministradora.obtenerTamañoEntidad(), posición)) && flagMoverDerecha) {
      moverDerecha(posición, VELOCIDAD_ENEMIGO_POR_DEFECTO);
    } else {
      moverIzquierda(posición, VELOCIDAD_ENEMIGO_POR_DEFECTO);
      flagMoverDerecha = false;
      if (!(VerificadorColisiones.verificarColisionesConLosBordes(VentanaAdministradora.obtenerTamañoEntidad(), posición)) && !flagMoverDerecha) {
        flagMoverDerecha = true;
      }
    }
  }
}
