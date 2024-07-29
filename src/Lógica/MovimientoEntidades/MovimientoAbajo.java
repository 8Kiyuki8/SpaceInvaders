package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;

public class MovimientoAbajo implements Movimiento {

  @Override
  public void mover(Posición posiciónActual) {
    posiciónActual.establecerPosiciónY(posiciónActual.obtenerPosiciónX() + velocidadMovimiento);
  }
}
