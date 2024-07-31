package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;

public interface Movimiento {
  void mover(Posición posiciónActual, int velocidad);
}
