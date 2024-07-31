package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;

public class MovimientoArriba implements Movimiento {

  @Override
  public void mover(Posición posiciónActual) {
    posiciónActual.establecerPosiciónY(posiciónActual.obtenerPosiciónY() - velocidadMovimiento);
  }
}
