package Lógica.MovimientoEntidades;

import Lógica.Entidades.Posición;
import Lógica.Interfaces.Movimiento;

import java.io.Serializable;

public class MovimientoIzquierda implements Movimiento, Serializable {

  @Override
  public void mover(Posición posiciónActual, int velocidad) {
    posiciónActual.establecerPosiciónX(posiciónActual.obtenerPosiciónX() - velocidad);
  }
}
