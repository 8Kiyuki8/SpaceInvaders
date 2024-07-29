package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;

public interface Movimiento {
  int velocidadMovimiento = 10;

  void mover(Posición posiciónActual);
}
