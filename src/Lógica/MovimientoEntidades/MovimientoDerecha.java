package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;
import Lógica.Servicios.VerificadorColisiones;

public class MovimientoDerecha implements Movimiento {
  @Override
  public void mover(Posición posiciónActual) {
    posiciónActual.establecerPosiciónX(posiciónActual.obtenerPosiciónX() + velocidadMovimiento);
    VerificadorColisiones.verificarColisionesConLosBordes(posiciónActual);
  }
}
