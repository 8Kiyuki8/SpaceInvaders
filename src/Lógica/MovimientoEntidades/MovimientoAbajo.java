package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;

public class MovimientoAbajo implements Movimiento {

  @Override
  public void mover(Posición posiciónActual, int velocidad) {
    posiciónActual.establecerPosiciónY(posiciónActual.obtenerPosiciónY() + velocidad);
  }
}
